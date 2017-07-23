package my.edu.umk.pams.connector.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.connector.payload.CandidatePayload;

@Component
public class CandidateSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, CandidatePayload payload) {
        jmsTemplate.convertAndSend(destination, payload);
    }
}

