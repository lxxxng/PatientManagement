﻿# PatientManagement
# 🏥 Microservices-based Patient Management System

A robust and scalable **Java Spring Boot** project designed around **microservices architecture** to handle patient data management, authentication, billing, and analytics. This system leverages **gRPC**, **Kafka**, **Docker**, **Spring Cloud Gateway**, and **AWS** for seamless deployment, real-time data flow, and secure communication.

---

## 📋 Table of Contents

- [🛠️ Tech Stack](#-tech-stack)
- [🧱 Architecture Overview](#-architecture-overview)
- [🚀 Features](#-features)
- [🧪 Testing](#-testing)
- [☁️ Cloud Deployment](#-cloud-deployment)

---

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot, Spring Security, Spring Cloud Gateway
- **Data:** PostgreSQL, In-Memory DB for local testing (h2)
- **Communication:** gRPC, Kafka (MSK), REST
- **Auth:** JWT-based Authentication
- **Containerization:** Docker, Docker Compose
- **Infrastructure as Code:** AWS CloudFormation
- **CI/CD & Orchestration:** Github, AWS CLI, LocalStack
- **API Documentation:** OpenAPI (Swagger)
- **Testing:** Rest Assured, Integration Testing, JUnit

---

## 🧱 Architecture Overview

This system is designed using **Domain-driven Design (DDD)** and **Microservices** principles:

- **Patient Service** — CRUD operations on patient data.
- **Billing Service** — Receives data via gRPC; handles billing events.
- **Analytics Service** — Consumes Kafka events and generates reports.
- **Auth Service** — User login, registration, and token validation.
- **API Gateway** — Routes requests to internal services, handles authentication via JWT.

---

## 🚀 Features

✅ gRPC-based inter-service communication  
✅ Kafka Producer/Consumer implementation  
✅ Dockerized multi-service setup with seamless orchestration  
✅ JWT-secured endpoints with custom validation filters  
✅ OpenAPI documentation routed via Gateway  
✅ Integration testing for key APIs  
✅ AWS CloudFormation for IaC with VPC, RDS, ECS, and MSK  
✅ Complete local deployment with LocalStack and AWS CLI

## 🧪 Testing

- **Unit Tests:** Use JUnit 
- **Integration Tests:** Implemented with **Rest Assured** in a Spring Boot Test context  
- **Kafka & gRPC Testing:** Simulated event and RPC call test coverage  
- **Auth Tests:** Login, Token Validation, and Filter logic fully tested

---

## ☁️ Cloud Deployment

The full infrastructure is deployed via **AWS CloudFormation** with:

- VPC
- PostgreSQL RDS
- MSK (Kafka)
- ECS Cluster with Docker images
- Load Balanced API Gateway
- S3 and Secrets Management (optional)


