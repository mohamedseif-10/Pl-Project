package finalproject.HyperMarketProject;

import java.util.Scanner;
import  java.io.*;

public class SellerEmployee extends Person{
    SellerEmployee(String Username, String Password, String role) {
        super(Username, Password, role);}
    public static SellerEmployee add(String username, String password)
    {
        SellerEmployee seller = new SellerEmployee(username, password, "Seller employee");
        return seller;
    }
}

    abstract class FileHandler {
        public static void appendToFile(String fileName, String data) throws IOException {
            try (FileWriter fw = new FileWriter(fileName, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(data);
            }
        }

        public static void deleteFromFile(String fileName, int orderId) throws IOException {
            File tempFile = new File("temp.txt");
            File file = new File(fileName);

            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] orderData = currentLine.split(",");
                    if (Integer.parseInt(orderData[0]) != orderId) {
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                }

            } catch (IOException e) {

                System.out.println("An IO exception occurred");

            }


            if (!file.delete()) {
                throw new IOException("Error deleting file");
            }

            if (!tempFile.renameTo(file)) {
                throw new IOException("Error renaming file");
            }
        }

        abstract void searchInFile(String fileName, int productId) throws IOException;

        abstract void displayAllFromFile(String fileName) throws IOException;
    }

    class OrderFileHandler extends FileHandler {
        @Override
        void searchInFile(String fileName, int productId) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String currentLine;
                boolean found = false;

                while ((currentLine = reader.readLine()) != null) {
                    String[] orderData = currentLine.split(",");
                    if (Integer.parseInt(orderData[1]) == productId) {
                        System.out.println("Product found in the orders: " + currentLine);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Product not found in the orders.");
                }
            }
        }

        @Override
        void displayAllFromFile(String fileName) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String currentLine;

                System.out.println("All products in order file:");
                while ((currentLine = reader.readLine()) != null) {
                    System.out.println(currentLine);
                }
            }
        }
    }

    class Order {
        private static int orderIdCounter = 0;
        private final int orderId;
        private final int productId;
        private final String orderProductName;
        private final int orderProductQuantity;

        public Order(int productId, String orderProductName, int orderProductQuantity) {
            orderIdCounter++;
            this.orderId = orderIdCounter;
            this.productId = productId;
            this.orderProductName = orderProductName;
            this.orderProductQuantity = orderProductQuantity;
        }

        public int getOrderId() {
            return orderId;
        }

        public int getProductId() {
            return productId;
        }

        public String getOrderProductName() {
            return orderProductName;
        }

        public int getOrderProductQuantity() {
            return orderProductQuantity;
        }
    }

    class Seller {
        private final int sellerId;

        public  Seller(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getSeller() {
            return sellerId;
        }

        public void makeOrder(Order order) throws IOException {
            int sId = this.sellerId;
            int orderId = order.getOrderId();
            int productId = order.getProductId();
            String productName = order.getOrderProductName();
            int productQuantity = order.getOrderProductQuantity();

            String orderData = orderId + "," + productId + "," + productName + "," + productQuantity + "," + sId;

            FileHandler.appendToFile("./Users_info/orders.txt", orderData);
            System.out.println("Order added successfully.");
        }



        public void cancelOrder(int orderId) throws IOException {
            FileHandler.deleteFromFile("./Users_info/orders.txt", orderId);
            System.out.println("Order canceled successfully.");
        }

        public void searchProduct(OrderFileHandler orderFileHandler, int productId) throws IOException {
            orderFileHandler.searchInFile("./Users_info/products.txt", productId);
        }

        public void listAllProducts(OrderFileHandler orderFileHandler) throws IOException {
            orderFileHandler.displayAllFromFile("./Users_info/products.txt");
        }
    }



