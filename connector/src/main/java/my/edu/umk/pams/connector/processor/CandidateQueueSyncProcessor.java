package my.edu.umk.pams.connector.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import my.edu.umk.pams.connector.payload.CandidatePayload;

@Component
public class CandidateQueueSyncProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateQueueSyncProcessor.class);

    @Autowired
    @Qualifier(value = "intakeDataSource")
    private DataSource intakeDataSource;

    @Autowired
    @Qualifier(value = "intakeJdbcTemplate")
    private JdbcTemplate intakeJdbcTemplate;

    @Autowired
    @Qualifier(value = "academicDataSource")
    private DataSource academicDataSource;

    @Autowired
    @Qualifier(value = "academicJdbcTemplate")
    private JdbcTemplate academicJdbcTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.info("processing");
        final CandidatePayload candidate = exchange.getIn().getBody(CandidatePayload.class);
        LOG.info("candidate: " + candidate);
//        for (CandidatePayload candidate : candidates) {
//            LOG.info("candidate: " + candidate);
//        }
        // use REST API here
    }
}
