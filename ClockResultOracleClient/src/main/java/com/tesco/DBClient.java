package com.tesco;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBClient {

    public static void main(String[] args) {
        try {

            if (args == null) {
                usage();
                System.exit(0);
            }

            if (args.length < 2) {
                System.out.println("Insufficient arguments...");
                usage();
                System.exit(0);
            }

            System.out.println("");
            System.out.println("---------------------------------------------------------------");
            String dbUrl = args[0];
            int basketCount = Integer.parseInt(args[1]);

            System.out.println("Database Url: " + dbUrl);
            System.out.println("basketCount: " + basketCount);
            System.out.println("");

            DBClient dbClient = new DBClient();
            dbClient.createAndSendData(dbUrl, basketCount);

            System.out.println("");

            System.out.println("Done.");
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void usage() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("");
        System.out.println("Usage: ");
        System.out.println("java  -jar  dbclient-1.0-jar-with-dependencies.jar   <oracle_url>   <basket_count>");
        System.out.println("");
        System.out.println("Example: ");
        System.out.println("java  -jar dbclient-1.0-jar-with-dependencies.jar  jdbc:oracle:thin:test/test@localhost:1521:xe    10");
        System.out.println("This will insert 10 baskets in the database. ");
        System.out.println("As per above url Database Details: ");
        System.out.println("Host:localhost, port:1521, username:test, password:test, sid:xe ");
        System.out.println("");
        System.out.println("");
        System.out.println("---------------------------------------------------------------");
    }

    private void createAndSendData(String dbUrl, int basketCount) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            System.out.println("started....will insert " + basketCount + " baskets......");
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");


            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbUrl);

            stmt = conn.prepareStatement("INSERT INTO clockresult(transaction_no, loyalty_card_no, clock_result) VALUES (?,?,?)");

            String transactionNo = "transNo-" + System.currentTimeMillis();
            String loyaltyCardNo = "loyaltyCardNo-" + System.currentTimeMillis();

            String msg = getData();
            String cardNo = null;
            for (int i = 0; i < basketCount; i++) {
                if (msg != null) {
                    stmt.setString(1, i + "-" + transactionNo);

                    if (i > 100 && basketCount % 100 == 0) {
                        cardNo = i + "-" + loyaltyCardNo;
                        System.out.println(i + " baskets are inserted uptil now.");
                    } else {
                        cardNo = loyaltyCardNo;
                    }
                    stmt.setString(2, cardNo);

                    String message = msg.replace("3", Integer.toString(i));
                    Reader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
                    stmt.setString(3, message);

                    stmt.executeUpdate();
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
                conn.close();
            }
        }
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
