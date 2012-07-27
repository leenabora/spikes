package com.activemq.spike;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class XmlUtil {

    public static void main(String[] args) throws JSONException {
        JSONObject jsonObj = XML.toJSONObject(getData());
        String json = jsonObj.toString();
        System.out.println(json);
    }


    private static String getData() {
        StringBuilder str = new StringBuilder();
        try {

            InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream("ClockResult.xml");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line

            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                str.append(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
