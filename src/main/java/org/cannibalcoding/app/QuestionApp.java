package org.cannibalcoding.app;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Hello world!
 *
 */
public class QuestionApp extends ResourceConfig {
    
    public QuestionApp() {
	register(new QuestionAppBinder());
	packages(true, "org.cannibalcoding.services");
    }
}
