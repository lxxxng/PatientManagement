package com.example.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.patient_service.dto.PatientRequestDTO;
import com.example.patient_service.dto.PatientResponseDTO;
import com.example.patient_service.exception.EmailAlreadyExistsException;
import com.example.patient_service.exception.PatientNotFoundException;
import com.example.patient_service.grpc.BillingServiceGrpcClient;
import com.example.patient_service.mapper.PatientMapper;
import com.example.patient_service.model.Patient;
import com.example.patient_service.repository.PatientRepository;

@Service
public class PatientService{
    private PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List <PatientResponseDTO> getPatients() {

        List <Patient> patients = patientRepository.findAll();
        List <PatientResponseDTO> patientResponseDTOs = patients.stream()
                                                        .map(PatientMapper::toDTO)
                                                        .toList();
        return patientResponseDTOs;
    }

    // create a new patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + 
                                                                        patientRequestDTO.getEmail());
        }

        // create a new patient and save it to the database
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(), 
                                                    newPatient.getName(), 
                                                    newPatient.getEmail());

        // then return the created patient as a DTO response 
        return PatientMapper.toDTO(newPatient);
    }

    // update an existing patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        
        Patient patient = patientRepository.findById(id).
            orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + 
                                                                        patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);       // jpa will update because there is a primary key id
        return PatientMapper.toDTO(updatedPatient);
    }

    // delete a patient
    public void deletePatient(UUID id)
    {
        patientRepository.deleteById(id);
    }
}
