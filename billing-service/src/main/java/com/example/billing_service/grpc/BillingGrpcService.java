package com.example.billing_service.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    // stream observer is a gRPC object that allows you to send and receive messages asynchronously
    // can send multiple response until REST API sending a single response and getting single request
    @Override 
    public void createBillingAccount(billing.BillingRequest billingRequest,
        StreamObserver <billing.BillingResponse> responseObserver) {           
            
            log.info("createBillingAccount request received {}", billingRequest.toString());

            // business logic - eg. to save to database, perform calculations, etc.

            BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

            responseObserver.onNext(response);  // Send the response to the client
            responseObserver.onCompleted(); // because can send multiple response, complete the RPC call
        }
}
