package sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;

@Component
public class ProgramCodeSender {

    private static final Logger LOG = LoggerFactory.getLogger(ProgramCodeSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, ProgramCodePayload payload) {
        jmsTemplate.convertAndSend(destination, payload);
        LOG.debug("program code sent");
    }
}

