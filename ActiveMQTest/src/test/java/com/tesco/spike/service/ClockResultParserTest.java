package com.tesco.spike.service;

import com.tesco.spike.vo.ClockResult;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertThat;

public class ClockResultParserTest {

    private ClockResultParser clockResultParser;

    @Before
    public void before() {
        clockResultParser = new ClockResultParser();
    }

    @Test
    public void shouldAbleToGetTransactionNoFromClockResultXml() throws Exception {
        ClockResult result = clockResultParser.parse(getData());

        assertThat(result.getTransactionNo(), Is.is("2008-03-29T07:19:45-3-stri3-5-6"));
    }

    @Test
    public void shouldAbleToGetLoyaltyCardNoFromClockResultXml() throws Exception {
        ClockResult result = clockResultParser.parse(getData());

        assertThat(result.getLoyaltyCardNo(), Is.is("loyalty-card-no"));
    }

    private String getData() {
        StringBuilder str = new StringBuilder();
        try {

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ClockResult.xml");
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
