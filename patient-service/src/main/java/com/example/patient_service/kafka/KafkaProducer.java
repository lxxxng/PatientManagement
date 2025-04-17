package com.example.patient_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.patient_service.model.Patient;

import patient_events.PatientEvent;

@Service
public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(
      KafkaProducer.class);
  private final KafkaTemplate<String, byte[]> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEvent(Patient patient) {
    // Build the PatientEvent
    PatientEvent event = PatientEvent.newBuilder()
            .setPatientId(patient.getId().toString())
            .setName(patient.getName())
            .setEmail(patient.getEmail())
            .setEventType("PATIENT_CREATED")
            .build();

    try {
        // Convert the event to byte array
        byte[] eventBytes = event.toByteArray();

        log.info("Sending event after TOBYTE(): {}", eventBytes);

        // Log the byte array as a Base64 string for readability
        String base64EncodedEvent = java.util.Base64.getEncoder().encodeToString(eventBytes);
        log.info("Sending event after base64Encoded: {}", base64EncodedEvent);

        // Send the byte array to Kafka
        kafkaTemplate.send("patient", eventBytes);  // "patient" is the topic

        } catch (Exception e) {
            log.error("Error sending PatientCreated event: {}", event);
        }
    }

}