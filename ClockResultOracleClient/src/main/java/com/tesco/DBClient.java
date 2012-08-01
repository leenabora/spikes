package com.tesco;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBClient {

    public static void main(String[] args) {
        try {

            if (args == null) {
                System.out.println("Usage: ");
                System.exit(0);
            }

            if (args.length < 2) {
                System.out.println("Insufficient arguments...");
                System.exit(0);
            }

            String dbUrl = args[0];

            int basketCount = Integer.parseInt(args[1]);

            DBClient dbClient = new DBClient();
            dbClient.createAndSendData(dbUrl, basketCount);

            System.out.println("Done.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void usage() {
        System.out.println("---------------------------------------------------------------");
    }

    private void createAndSendData(String dbUrl, int basketCount) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            System.out.println("started....");
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");


            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbUrl);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement("INSERT INTO clockresult(transaction_no, loyalty_card_no, clock_result) VALUES (?,?,?)");

            String transactionNo = "transNo";
            String loyaltyCardNo = "loyaltyCardNo";

            String msg = getData();
            String cardNo = null;
            for (int i = 0; i < basketCount; i++) {
                if (msg != null) {
                    stmt.setString(1, transactionNo + "-" + i);

                    if (basketCount % 100 == 0) {
                        cardNo = loyaltyCardNo + "-" + i;
                    }
                    stmt.setString(2, cardNo);

                    String message = msg.replace("3", Integer.toString(i));
                    Reader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
                    stmt.setClob(3, reader);

                    stmt.executeUpdate();
                }
            }
        } finally {
            if (stmt != null){
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
