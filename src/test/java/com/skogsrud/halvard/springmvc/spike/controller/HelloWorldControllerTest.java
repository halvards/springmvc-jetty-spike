package com.skogsrud.halvard.springmvc.spike.controller;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloWorldControllerTest {
    @Test
    public void testHelloWorld() throws Exception {
        assertThat(new HelloWorldController().hello(), equalTo("Hello world"));
    }
}
