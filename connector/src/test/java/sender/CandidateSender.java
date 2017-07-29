package sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.connector.payload.CandidatePayload;

@Component
public class CandidateSender {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, CandidatePayload payload) {
        jmsTemplate.convertAndSend(destination, payload);
        LOG.debug("candidate sent");
    }
}

