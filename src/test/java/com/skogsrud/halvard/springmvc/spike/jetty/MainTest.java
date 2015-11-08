package com.skogsrud.halvard.springmvc.spike.jetty;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MainTest {
    @Test
    public void testPortDefaultsIfEnvironmentVariableNotSet() throws Exception {
        assertThat(Main.getPort(new StubEnvironment()), equalTo(Main.DEFAULT_PORT));
    }

    @Test
    public void testPortUsesEnvironmentVariableIfSet() throws Exception {
        Map<String, String> variables = new HashMap<>();
        int port = new Random().nextInt();
        variables.put("PORT", String.valueOf(port));
        assertThat(Main.getPort(new StubEnvironment(variables)), equalTo(port));
    }
}
