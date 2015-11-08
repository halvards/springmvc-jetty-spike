package com.skogsrud.halvard.springmvc.spike.jetty;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.io.File;
import java.io.IOException;

public class Main {
    static final int DEFAULT_PORT = 8081;

    private final Server server;

    public static void main(String[] args) throws Exception {
        new Main(new Environment()).run();
    }

    private Main(Environment environment) throws Exception {
        this(getPort(environment));
    }

    public Main(int port) throws Exception {
        server = new Server(port);
        server.setHandler(createApplicationContext());
    }

    /**
     * Start server and block, waiting for it to stop.
     */
    public void run() throws Exception {
        server.start();
        server.join();
    }

    /**
     * Start server and return when server has started.
     */
    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private WebAppContext createApplicationContext() {
        WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
        File gradleClassesDirectory = new File("build/classes");
        File mavenClassesDirectory = new File("target/classes");
        if (gradleClassesDirectory.exists()) {
            // Running without a packaged JAR file, using Gradle
            context.getMetaData().addContainerResource(new PathResource(gradleClassesDirectory));
        } else if (mavenClassesDirectory.exists()) {
            // Running without a packaged JAR file, using Maven
            context.getMetaData().addContainerResource(new PathResource(mavenClassesDirectory));
        } else {
            // Running as JAR file
            context.getMetaData().addContainerResource(new PathResource(new File(".")));
        }
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebInfConfiguration()});
        context.setContextPath("/app");
        return context;
    }

    static int getPort(Environment environment) throws IOException {
        return environment.getVariable("PORT") == null ? DEFAULT_PORT : Integer.parseInt(environment.getVariable("PORT"));
    }
}
