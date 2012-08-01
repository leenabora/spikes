package com.tesco.spike.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonConverter {

    public String fromXml(String xml) throws JSONException {
        return XML.toJSONObject(xml).toString();
    }

    public String fromList(List<String> jsonList) {
        return new JSONArray(jsonList).toString();
    }
}
