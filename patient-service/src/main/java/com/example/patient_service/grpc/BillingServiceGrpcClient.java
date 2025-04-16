package com.example.patient_service.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class BillingServiceGrpcClient {
    
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

    // stub is a client-side representation of the service, it is used to make calls to the server
    // blocking stub for synchronous calls
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub; 

    // LOCALHOST:9001/BillingService/CreatePatientAccount
    // aws.grpc:123123/BillingService/CreatePatientAccount
    public BillingServiceGrpcClient
    (
        @Value("${billing.service.address:localhost}") String serverAddress,
        @Value("${billing.service.grpc.port:9001}") int serverPort
    )
    {
        log.info("Connecting to Billing Service GRPC service at {}:{}", serverAddress, serverPort);

        // useplaintext() is used for development purposes only, it disables encryption, in production use SSL/TLS
        // managedChannel is a channel that is used to communicate with the server, it is a long-lived object
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                                                                        .usePlaintext().build();

        // create a stub for the service, this is a synchronous stub, it will block until the response is received                                                                
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email)
    {
        BillingRequest request = BillingRequest.newBuilder()
                                                .setPatientId(patientId)
                                                .setName(name)
                                                .setEmail(email)
                                                .build();       
        
        BillingResponse response = blockingStub.createBillingAccount(request);

        log.info("Received response from Billing Service: {}", response);
        return response;
    }

}
