package org.apache.camel.learn;

import org.apache.camel.learn.filters.AuthFilter;

/**
 * A Camel Application
 */
public class Main {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        org.apache.camel.main.Main main = new org.apache.camel.main.Main();
        main.configure().addRoutesBuilder(new UserRouteBuilder());
        main.bind("authFilter", new AuthFilter());
        main.run(args);
    }

}

