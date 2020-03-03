package com.psa.qualif.controllers;

import com.psa.qualif.JMS.Receiver;
import com.psa.qualif.JMS.Sender;
import com.psa.qualif.services.ApiService;
import com.psa.qualif.services.JMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    /**
     * ActiveMQ documentation with e.g for post / received message from JMS
     * https://activemq.apache.org/rest
     */

    @Autowired
    JMSService jmsService;
    @Autowired
    ApiService apiService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);


    @GetMapping("/")
    public String home () {
        //jmsService.sendMessage("qualif.q", "Ships _");
        return "home";
    }

    /**
     * $ curl -XGET http://admin:admin@localhost:8161/api/message?destination=queue://qualif.q
     * @param destination topic or queue
     * @return
     */
    @PostMapping("/api/message/send")
    public ResponseEntity apiMessageSend (@RequestParam String destination, @RequestBody String message) {
        LOGGER.info("Destination => " + destination);
        return jmsService.sendMessageWithParams(destination, message);
    }


}
