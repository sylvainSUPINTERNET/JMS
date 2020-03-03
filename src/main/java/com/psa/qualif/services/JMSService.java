package com.psa.qualif.services;

import com.psa.qualif.JMS.Receiver;
import com.psa.qualif.JMS.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JMSService {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private ApiService apiService;


    JMSService(){}


    // for test
    public void sendMessage(String destination, String message){
        sender.send(destination, message);
    }

    public ResponseEntity sendMessageWithParams(String destination, String message){
        if(apiService.checkParams(destination)) {
            String[] parts = destination.split("://");
            sender.send(parts[1], message);
            return ResponseEntity.ok().body("messaged send");
        } else {
            return ResponseEntity.badRequest().body("wrong paramters");
        }
    }

}
