package com.tesco.spike.service;

import org.json.JSONException;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {

    public String fromXml(String xml) throws JSONException {
        return XML.toJSONObject(xml).toString();
    }
}
