/**
 * FreshVend - Smart IoT Vending Machine Firmware
 * Hardware Platform: ESP32 NodeMCU-32S
 * Sensors: DHT22 (Cold chain), IR Drop Sensor (Jam detection), Microswitch (Door)
 * Actuators: SG90 / MG996R Servos (Columns 1 to 4)
 */

#include <WiFi.h>
#include <PubSubClient.h>
#include <DHT.h>
#include <ESP32Servo.h>
#include <ArduinoJson.h>

// 1. PIN CONFIGURATION
#define DHTPIN 4
#define DHTTYPE DHT22
#define DOOR_SENSOR_PIN 15
#define DROP_SENSOR_PIN 18 // Infrared sensor detecting falling fruit bowl
#define STATUS_LED_PIN 2

const int SERVO_PINS[4] = {13, 12, 14, 27}; // Columns 1, 2, 3, 4
Servo columnServos[4];

// 2. NETWORK CREDENTIALS
const char* ssid = "FreshVend_WiFi";
const char* password = "FreshVendSecretPass";
const char* mqtt_server = "192.168.1.100"; // Broker or Backend IP
const int mqtt_port = 1883;
const char* machine_code = "VN-HCM-Q1-001";

WiFiClient espClient;
PubSubClient client(espClient);
DHT dht(DHTPIN, DHTTYPE);

unsigned long lastTelemetryTime = 0;
const long telemetryInterval = 5000; // 5 seconds per temperature log

void setup() {
  Serial.begin(115200);
  pinMode(DOOR_SENSOR_PIN, INPUT_PULLUP);
  pinMode(DROP_SENSOR_PIN, INPUT);
  pinMode(STATUS_LED_PIN, OUTPUT);

  dht.begin();

  // Attach servos
  for (int i = 0; i < 4; i++) {
    columnServos[i].attach(SERVO_PINS[i]);
    columnServos[i].write(0); // Rest position
  }

  setupWiFi();
  client.setServer(mqtt_server, mqtt_port);
  client.setCallback(mqttCallback);
}

void setupWiFi() {
  delay(10);
  Serial.print("Connecting to WiFi: ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);

  int tries = 0;
  while (WiFi.status() != WL_CONNECTED && tries < 20) {
    delay(500);
    Serial.print(".");
    tries++;
  }
  if (WiFi.status() == WL_CONNECTED) {
    Serial.println("\nWiFi Connected! IP: ");
    Serial.println(WiFi.localIP());
  } else {
    Serial.println("\nWiFi Offline - Running in Local Fallback Mode");
  }
}

void reconnectMQTT() {
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    if (client.connect(machine_code)) {
      Serial.println("connected!");
      // Subscribe to dispense commands: machines/{machine_code}/dispense
      String subTopic = String("machines/") + machine_code + "/dispense";
      client.subscribe(subTopic.c_str());
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" retry in 3 seconds");
      delay(3000);
    }
  }
}

// 3. DISPENSING WITH JAM DETECTION & AUTOMATIC RETRY LOGIC
bool dispenseProduct(int columnNumber) {
  int colIndex = columnNumber - 1;
  if (colIndex < 0 || colIndex >= 4) return false;

  const int MAX_RETRIES = 2;
  for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
    Serial.printf("[Column %d] Dispensing attempt %d...\n", columnNumber, attempt);

    // Rotate servo to drop one fruit bowl
    columnServos[colIndex].write(180);
    delay(1200);
    columnServos[colIndex].write(0);
    delay(800);

    // Check IR drop sensor within 3 seconds
    unsigned long startWait = millis();
    bool itemDetected = false;
    while (millis() - startWait < 3000) {
      if (digitalRead(DROP_SENSOR_PIN) == LOW) { // Item passed optical beam
        itemDetected = true;
        break;
      }
      delay(50);
    }

    if (itemDetected) {
      Serial.println("Dispense SUCCESS! Item delivered.");
      publishDispenseResult(columnNumber, "SUCCESS", attempt);
      return true;
    } else {
      Serial.printf("Attempt %d failed: Jam or misfeed detected!\n", attempt);
    }
  }

  // If failed after max retries
  Serial.println("ERROR: Column jammed! Triggering alert.");
  publishDispenseResult(columnNumber, "JAMMED", MAX_RETRIES);
  return false;
}

void publishDispenseResult(int column, const char* status, int attempts) {
  StaticJsonDocument<200> doc;
  doc["machine"] = machine_code;
  doc["column"] = column;
  doc["status"] = status;
  doc["attempts"] = attempts;

  char buffer[256];
  serializeJson(doc, buffer);
  String topic = String("machines/") + machine_code + "/dispense_ack";
  client.publish(topic.c_str(), buffer);
}

// 4. MQTT CALLBACK FOR CLOUD COMMANDS
void mqttCallback(char* topic, byte* payload, unsigned int length) {
  StaticJsonDocument<256> doc;
  deserializeJson(doc, payload, length);

  int column = doc["column"];
  Serial.printf("Received command to dispense column %d\n", column);
  dispenseProduct(column);
}

// 5. COLD CHAIN TELEMETRY
void sendTelemetry() {
  float temp = dht.readTemperature();
  float hum = dht.readHumidity();
  bool doorOpen = (digitalRead(DOOR_SENSOR_PIN) == HIGH);

  if (isnan(temp)) temp = 4.5; // fallback
  if (isnan(hum)) hum = 65.0;

  StaticJsonDocument<256> doc;
  doc["machine"] = machine_code;
  doc["temperature"] = temp;
  doc["humidity"] = hum;
  doc["door_open"] = doorOpen;
  doc["alert"] = (temp < 2.0 || temp > 8.0);

  char buffer[256];
  serializeJson(doc, buffer);
  String topic = String("machines/") + machine_code + "/telemetry";
  client.publish(topic.c_str(), buffer);
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    if (!client.connected()) reconnectMQTT();
    client.loop();
  }

  if (millis() - lastTelemetryTime > telemetryInterval) {
    lastTelemetryTime = millis();
    sendTelemetry();
  }
}
