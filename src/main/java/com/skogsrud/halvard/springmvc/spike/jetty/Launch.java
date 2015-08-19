package com.skogsrud.halvard.springmvc.spike.jetty;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Launch {
    private static final int DEFAULT_PORT = 8080;
    private final Server server;

    public static void main(String[] args) throws Exception {
        new Launch().start();
    }

    public Launch() throws Exception {
        int port = getPort();
        server = new Server(port);
        server.setHandler(createApplicationContext());
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
        context.setContextPath("/app");
        return context;
    }

    private int getPort() throws IOException {
        String portEnv = System.getenv("PORT");
        if ("0".equals(portEnv)) {
            return findRandomOpenPort();
        }
        if ((portEnv != null)) {
            return Integer.parseInt(portEnv);
        }
        return DEFAULT_PORT;
    }

    private int findRandomOpenPort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }
}
