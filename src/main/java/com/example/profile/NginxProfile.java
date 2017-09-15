package com.example.profile;

import java.util.HashMap;
import java.util.Map;

public class NginxProfile {

    @SuppressWarnings("rawtypes")
    private Map<String, Comparable> values = new HashMap<String, Comparable>();

    public void setClientId(String clientId) {
       values.put("clientId", clientId);
    }

    public void setAppPort(int appPort) {
        values.put("app.port",  appPort);
    }

    public void setContextPath(String contextPath) {
        values.put("context.path",  contextPath);
    }

    @SuppressWarnings("rawtypes")
    public Map<String, Comparable> getValues() {
        return values;
    }

}
