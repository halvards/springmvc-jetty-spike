package com.skogsrud.halvard.springmvcjetty.spike.jetty;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.io.File;

public class Launch {
    private static final int PORT = 8080;
    private final Server server;

    public Launch() {
        server = new Server(PORT);
        server.setHandler(createApplicationContext());
    }

    public static void main(String[] args) throws Exception {
        new Launch().start();
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private WebAppContext createApplicationContext() {
        WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
          File mavenClassesDirectory = new File("target/classes");
        if (mavenClassesDirectory.exists()) {
            // Running without a packaged JAR file
            context.getMetaData().addContainerResource(new PathResource(mavenClassesDirectory));
        } else {
            // Running as JAR file
            context.getMetaData().addContainerResource(new PathResource(new File(".")));
        }
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration()});
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        return context;
    }
}
