package my.edu.umk.pams.connector.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.component.quartz.QuartzComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class BeanConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory activeMQConncetionFactory = new ActiveMQConnectionFactory();
        activeMQConncetionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConncetionFactory.setUseAsyncSend(true);
        activeMQConncetionFactory.setClientID("pams-camel");
        return activeMQConncetionFactory;
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public BrokerService brokerService() throws Exception {
//        BrokerService brokerService = new BrokerService();
//        brokerService.setPersistent(false);
//        brokerService.setUseJmx(false);
//        brokerService.addConnector("tcp://localhost:61616");
//        brokerService.setBrokerName("broker");
//        brokerService.setUseShutdownHook(false);
//        brokerService.connection
//        return brokerService;
//    }

    @Bean
    public ActiveMQQueue defaultDestination(){
        return new ActiveMQQueue("student");
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(defaultDestination());
        return jmsTemplate;
    }
    @Bean
    public QuartzComponent quartz() {
        QuartzComponent quartz = new QuartzComponent();
        quartz.setStartDelayedSeconds(5);
        return quartz;

    }
}