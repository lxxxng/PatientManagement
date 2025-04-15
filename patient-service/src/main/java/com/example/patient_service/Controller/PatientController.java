package com.example.patient_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient_service.dto.PatientResponseDTO;
import com.example.patient_service.service.PatientService;

@RestController
@RequestMapping("/patients")       // all request from http://localhost:4000/patients
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping // handle get requests
    public ResponseEntity <List <PatientResponseDTO>> getPatients() {
        List <PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }
}
