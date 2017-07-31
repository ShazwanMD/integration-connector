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

@Component
public class StaffSyncProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(StaffSyncProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.info("processing");
//        final ProgramCode[] programCodes = exchange.getIn().getBody(ProgramCode[].class);
//        for (ProgramCode programCode : programCodes) {
//            LOG.info("programCode: " + programCode);
//        }
        // use REST API here



    }
}
