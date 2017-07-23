package route;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.TestAppConfig;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.sender.CandidateSender;

@ContextConfiguration(classes = TestAppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTest extends AbstractJUnit4SpringContextTests {

    public static final Logger LOG = LoggerFactory.getLogger(SenderTest.class);

    @Autowired
    private CandidateSender candidateSender;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRoute() throws Exception {
        LOG.debug("testing");
        CandidatePayload candidatePayload = new CandidatePayload();
        candidatePayload.setName("Ashfraf Wajidi");
        candidatePayload.setMatricNo("A177900");
        candidatePayload.setCohortCode("ABC");
        candidatePayload.setFacultyCode("ABC");
        candidatePayload.setProgramCode("ABC");
        candidatePayload.setEmail("ashraf@umk.edu.my");
        candidatePayload.setMobile("012920011");
        candidatePayload.setFax("1212");
        candidateSender.send("candidateQueue", candidatePayload);
    }
}
