package org.apache.camel.learn;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.learn.models.User;
import org.apache.camel.learn.processors.UserProcessor;

public class UserRouteBuilder extends RouteBuilder {
    public void configure() {
        from("jetty:http://0.0.0.0:8080/public")
                .routeId("public")
                .unmarshal().json(User.class)
                .process(new UserProcessor());

        from("jetty:http://0.0.0.0:8080/protected?filters=#authFilter")
                .routeId("protected")
                .unmarshal().json(User.class)
                .process(new UserProcessor());

    }

}
