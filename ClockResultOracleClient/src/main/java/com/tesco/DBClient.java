package com.tesco;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            if (args.length < 4) {
                System.out.println("Insufficient arguments...");
                usage();
                System.exit(0);
            }

            System.out.println("");
            System.out.println("---------------------------------------------------------------");
            String dbUrl = args[0];
            int basketCount = Integer.parseInt(args[1]);
            int batchSize = Integer.parseInt(args[2]);
            String transPrefix = args[3];

            System.out.println("Database Url: " + dbUrl);
            System.out.println("basketCount: " + basketCount);
            System.out.println("batch Size: " + batchSize);
            System.out.println("");

            DBClient dbClient = new DBClient();
            dbClient.createAndSendData(dbUrl, basketCount, batchSize, transPrefix);

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
        System.out.println("java  -jar  dbclient-1.0-jar-with-dependencies.jar   <oracle_url>   <basket_count>  <batch_size>  <transaction_prefix>");
        System.out.println("");
        System.out.println("Example: ");
        System.out.println("java  -jar dbclient-1.0-jar-with-dependencies.jar  jdbc:oracle:thin:test/test@localhost:1521:xe    100  10 TR-1");
        System.out.println("");
        System.out.println(" <basket_count> :");
        System.out.println("This will insert 100 baskets in the database. ");
        System.out.println("");
        System.out.println(" <batch_size> :");
        System.out.println("For basket count 100, it will make 10 batches and commit will be performed after each batch.");
        System.out.println("");
        System.out.println(" <transaction_prefix> :");
        System.out.println("This is to maintain uniqueness of transaction_no, While running from different terminal, use different prefix. You can use any string..");
        System.out.println("");
        System.out.println("<oracle_url>: As per above url Database Details: ");
        System.out.println("Host:localhost, port:1521, username:test, password:test, sid:xe ");
        System.out.println("");
        System.out.println("");
        System.out.println("---------------------------------------------------------------");
    }

    private void createAndSendData(String dbUrl, int basketCount, int batchSize, String transPrefix) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            System.out.println("started....will insert " + basketCount + " baskets......");
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");


            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbUrl);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO clockresult(transaction_no, loyalty_card_no, clock_result) VALUES (?,?,?)");

            String transactionNo = transPrefix + "-" + System.currentTimeMillis();
            String loyaltyCardNo = "loyaltyCardNo-" + System.currentTimeMillis();

            String msg = getData();
            String cardNo = null;
            int currentBatch = 0;
            for (int i = 1; i < basketCount + 1; i++) {
                if (msg != null) {
                    stmt.setString(1, i + "-" + transactionNo);

                    if (i > 100 && basketCount % 100 == 0) {
                        cardNo = i + "-" + loyaltyCardNo;
                    } else {
                        cardNo = loyaltyCardNo;
                    }
                    stmt.setString(2, cardNo);

                    String message = msg.replace("3", Integer.toString(i));
                    stmt.setString(3, message);

                    stmt.addBatch();
                    if (i % batchSize == 0 || i == basketCount) {
                        try {
                            stmt.executeBatch();
                            conn.commit();
                            currentBatch++;
                            System.out.println(currentBatch + " batch is inserted.... " + ". Baskets inserted up till now: " + i);
                            stmt.clearBatch();
                        } catch (Exception e) {
                            System.out.println("Failed to insert " + currentBatch + " ,reason: " + e.getMessage());
                        }
                    }
                }

            }

        } finally

        {
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
