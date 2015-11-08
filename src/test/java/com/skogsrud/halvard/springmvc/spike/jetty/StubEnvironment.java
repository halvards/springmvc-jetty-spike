package com.skogsrud.halvard.springmvc.spike.jetty;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StubEnvironment extends Environment {
    private final Map<String, String> variables;

    public StubEnvironment() {
        this(new HashMap<>());
    }

    public StubEnvironment(Map<String, String> variables) {
        this.variables = Collections.unmodifiableMap(variables);
    }

    @Override
    public String getVariable(String name) {
        return variables.get(name);
    }
}
