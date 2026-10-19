# FreshVend demo1 scaffold

Starter structure inside the existing Spring Boot application, aligned with the revised Mermaid diagrams and MVP requirements.

## Package map

- `domain/entity`, `domain/enums`: JPA entities and lifecycle/status enums.
- `repository`: Spring Data JPA repositories.
- `service`: business service contracts for product, inventory/FIFO, orders, simulated payments, IoT, operations, alerts, reporting, and auth.
- `controller`: endpoint ownership placeholders. They are intentionally not Spring MVC beans yet, so the application does not require unimplemented service beans.
- `dto/request`, `dto/response`: API boundary records. Do not return JPA entities directly from controllers.

## Suggested team split

1. Product and inventory: `Product`, `RestockHistory`, `StockReservation`, repositories and `ProductService`/`InventoryService`.
2. Ordering and payment: `Order`, `OrderItem`, `Payment`, `DispensingLog`, repositories and service contracts.
3. IoT and alerts: `IoTDevice`, `SensorData`, `Alert`, telemetry DTOs and `IoTService`/`AlertService`.
4. Operations: `Incident`, `OperationsService`, restocking and incident endpoints.
5. Admin and integration: `User`, `SystemConfig`, auth/reporting, controller wiring and API documentation.

## MVP rules represented

- Customer checkout may be anonymous (`Order.customer` is nullable).
- Stock is aggregated per product. Restock rows act as FIFO lots; the MVP does not model physical columns.
- `StockReservation` holds quantities while payment/dispensing is pending. Consume after dispense; release after failure/cancellation/refund.
- Payment/refund is simulated. Telemetry distinguishes `REAL` and `SIMULATED`.
- This is scaffolding only: service contracts and controllers do not implement business workflows. Add validation, transactional implementations, authorization, exception handling, migrations, and API docs as modules are completed.

Lombok is used for entity accessors; the existing Maven project already declares Lombok.

The Java classes are in [the demo1 package](../src/main/java/com/freshvend/demo1).

## Reference material

- [ERD](../diagrams/diagram_04_erd.mmd) and [class diagram](../diagrams/diagram_10_class.mmd) define the entity shape.
- [Order sequence](../diagrams/diagram_05_seq_order.mmd), [order state](../diagrams/diagram_07_state_order.mmd), and [payment state](../diagrams/diagram_08_state_payment.mmd) define checkout transitions.
- [IoT sequence](../diagrams/diagram_06_seq_iot.mmd) defines telemetry and monitoring flow.
- The SRS sources are [requirements.docx](requirements.docx) and [requirements_complete.docx](requirements_complete.docx); where their MVP scope differs, follow the team's approved version and update the model together.
