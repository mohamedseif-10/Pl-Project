package finalproject.HyperMarketProject;

import java.util.Scanner;
import java.io.*;

public class Admin extends Person {

    MarketingEmployee [] marketingEmployees = new MarketingEmployee[100];
    Admin(String Username, String Password, String role) {
        super(Username, Password, "Admin");
    }
    
    public static Admin get(String username, String password)
    {
            Admin admin = new Admin(username, password, "Admin");
            return admin;
    }

    public static void add(SellerEmployee []sellers, String username, String password) {
        SellerEmployee emp = new SellerEmployee(username ,password, "Seller" );
        try (FileWriter fw = new FileWriter("C://Users//LENOVO//Documents//JavaProjects//HyperMarketProject//Project//src//main//java//finalproject//HyperMarketProject//Users_Info//sellers.txt"))
        {
            for (SellerEmployee seller : sellers) {
                if (seller == null)
                    break;
                fw.append(Integer.toString(seller.getID()) + ", ");
                fw.append(seller.getUsername() + ", ");
                fw.append(seller.getPassword() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void add(InventoryEmployee []inventoryEmployees, String username, String password) {
        InventoryEmployee emp = new InventoryEmployee(username ,password, "Seller" );
        try (FileWriter fw = new FileWriter("C://Users//LENOVO//Documents//JavaProjects//HyperMarketProject//Project//src//main//java//finalproject//HyperMarketProject//Users_Info//sellers.txt", true))
        {
            for (InventoryEmployee inventory : inventoryEmployees) {
                if (inventory == null)
                    break;
                fw.append(Integer.toString(inventory.getID()) + ", ");
                fw.append(inventory.getUsername() + ", ");
                fw.append(inventory.getPassword() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(MarketingEmployee []Marketing, String username, String password) {
        MarketingEmployee emp = new MarketingEmployee(username ,password, "Seller" );
        try (FileWriter fw = new FileWriter("C://Users//LENOVO//Documents//JavaProjects//HyperMarketProject//Project//src//main//java//finalproject//HyperMarketProject//Users_Info//sellers.txt", true))
        {
            for (MarketingEmployee Marketingemp : Marketing) {
                if (Marketingemp == null)
                    break;
                fw.append(Integer.toString(Marketingemp.getID()) + ", ");
                fw.append(Marketingemp.getUsername() + ", ");
                fw.append(Marketingemp.getPassword() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

