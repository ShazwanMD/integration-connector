package my.edu.umk.pams.connector.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.connector.payload.CandidatePayload;

@Component
public class CandidateReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateReceiver.class);
    public static final String CANDIDATE_QUEUE = "candidateQueue";

//    @JmsListener(destination = CANDIDATE_QUEUE)
    public void receive(CandidatePayload candidate) {
        LOG.info("received");
        LOG.info("received: " + candidate.getName());
    }
}
