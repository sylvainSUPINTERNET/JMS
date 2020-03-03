package com.psa.qualif.services;


import org.springframework.stereotype.Service;

@Service
public class ApiService {

    ApiService(){}

    public boolean checkParams(String destination){
        String[] parts = destination.split("://");
        return parts[0].equals("topic") || parts[0].equals("queue");
    }
}
