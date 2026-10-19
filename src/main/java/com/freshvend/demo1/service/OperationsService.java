package com.freshvend.demo1.service;
public interface OperationsService { void startIncident(Long incidentId, Long operatorId); void resolveIncident(Long incidentId, Long operatorId, String resolutionNote); void closeIncident(Long incidentId, Long adminId); }
