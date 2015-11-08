package com.skogsrud.halvard.springmvc.spike.jetty;

public class Environment {
    public String getVariable(String name) {
        return System.getenv(name);
    }
}
