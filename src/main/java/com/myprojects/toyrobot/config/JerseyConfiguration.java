package com.myprojects.toyrobot.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        packages("com.myprojects.toyrobot.api");
    }
}
