package com.tesco.spike.service;

import com.tesco.spike.vo.ClockResult;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;

@Component
public class ClockResultParser {


    private final DocumentBuilderFactory dbFactory;
    private final XPath xpath;

    public ClockResultParser() {
        dbFactory = DocumentBuilderFactory.newInstance();
        xpath = XPathFactory.newInstance().newXPath();
    }

    public ClockResult parse(String clockResult) throws Exception {
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(clockResult.getBytes()));
        doc.getDocumentElement().normalize();

        String expression = "/ClockResult/Basket/BasketHeader/ClubcardNo";
        String loyaltyCardNo = getNodeValue(doc, xpath, expression);

        return new ClockResult(loyaltyCardNo, formTransactionNo(doc), clockResult);
    }

    private String formTransactionNo(Document doc) throws XPathExpressionException {
        String expression = "/ClockResult/Basket/BasketHeader/Date";
        String dateTime = getNodeValue(doc, xpath, expression);

        expression = "/ClockResult/Basket/BasketHeader/Branch";
        String branch = getNodeValue(doc, xpath, expression);

        expression = "/ClockResult/Basket/BasketHeader/Cashier";
        String cashier = getNodeValue(doc, xpath, expression);

        expression = "/ClockResult/Basket/BasketHeader/TillNo";
        String tillNo = getNodeValue(doc, xpath, expression);

        expression = "/ClockResult/Basket/BasketHeader/ReceiptNo";
        String receiptNo = getNodeValue(doc, xpath, expression);

        return dateTime + "-" + branch + "-" + cashier + "-" + tillNo + "-" + receiptNo;
    }

    private String getNodeValue(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        return ((Node) xpath.evaluate(expression, doc, XPathConstants.NODE)).getTextContent();
    }

}
