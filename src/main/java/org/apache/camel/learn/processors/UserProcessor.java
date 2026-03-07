package org.apache.camel.learn.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.learn.models.User;

public class UserProcessor implements Processor {



    @Override
    public void process(Exchange exchange) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        User user = exchange.getMessage().getBody(User.class);
        org.apache.camel.learn.models.soap.User soapUser =
                new org.apache.camel.learn.models.soap.User();

        soapUser.setUsername(user.getUsername());
        soapUser.setPassword(user.getPassword().replaceAll(".", "*"));

        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(soapUser);
        exchange.getMessage().setBody(xml);
    }
}
