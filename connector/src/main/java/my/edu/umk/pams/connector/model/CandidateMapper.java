package my.edu.umk.pams.connector.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.edu.umk.pams.connector.payload.CandidatePayload;

/**
 */
@Component
public class CandidateMapper {
    private static final Logger LOG = LoggerFactory.getLogger(CandidateMapper.class);

    public CandidateMapper() {
    }

    public CandidatePayload map(String result) {
        LOG.info("candidate mapper");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(result, CandidatePayload.class);
    }
}
