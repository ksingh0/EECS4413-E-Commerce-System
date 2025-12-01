package com.ecommerce;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.ecommerce");
        //disable warning in console
        property("jersey.config.server.wadl.disableWadl", true);

    }
    
}
