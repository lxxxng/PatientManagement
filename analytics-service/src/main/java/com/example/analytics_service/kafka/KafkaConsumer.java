package com.example.analytics_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import patient_events.PatientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer 
{
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service-test")
    public void consumeEvent(byte[] event)
    {
        try
        {
            PatientEvent patientEvent =  PatientEvent.parseFrom(event);
            // perform any business related to analytics here
            log.info("Received event:[PatientId={}, PatientName={}, PatientEmail={}]", 
                patientEvent.getPatientId(), 
                patientEvent.getName(), 
                patientEvent.getEmail());
        }
        catch (InvalidProtocolBufferException e)
        {
            log.error("Error deserializing event: {}", e.getMessage());
        }
        

    } 
}
