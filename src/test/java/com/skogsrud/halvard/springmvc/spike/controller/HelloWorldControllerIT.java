package com.skogsrud.halvard.springmvc.spike.controller;

import com.skogsrud.halvard.springmvc.spike.jetty.Main;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloWorldControllerIT {
    private static Main server;
    private static int port;

    @BeforeClass
    public static void beforeClass() throws Exception {
        port = findRandomOpenPort();
        server = new Main(port);
        server.start();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stop();
    }

    @Test
    public void testHelloWorld() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://localhost:" + port + "/app/hello");
            try (CloseableHttpResponse response = client.execute(request)) {
                assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
                assertThat(IOUtils.toString(response.getEntity().getContent()), equalTo("Hello world"));
            }
        }
    }

    private static int findRandomOpenPort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }
}
