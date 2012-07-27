package com.tesco.spike;

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

    public ClockResult parse(String clockResult) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(clockResult.getBytes()));
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        XPath xpath = XPathFactory.newInstance().newXPath();
        //datetime, branch, cashier, till, recieptno
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

        expression = "/ClockResult/Basket/BasketHeader/ClubcardNo";
        String loyaltyCardNo = getNodeValue(doc, xpath, expression);

        String transactionNo = dateTime + "-" + branch + "-" + cashier + "-" + tillNo + "-" + receiptNo;

        System.out.println("transactionNo: " + transactionNo);
        System.out.println("loyaltyCardNo: " + loyaltyCardNo);

        return new ClockResult(loyaltyCardNo, transactionNo, clockResult);

    }

    private String getNodeValue(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        return ((Node) xpath.evaluate(expression, doc, XPathConstants.NODE)).getTextContent();
    }

}
