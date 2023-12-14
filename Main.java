package finalproject.HyperMarketProject;

import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Admin[] Admins= new Admin[100];
        SellerEmployee[] Sellers = new SellerEmployee[100];
        MarketingEmployee[] MarketingEmployees = new MarketingEmployee[100];
        InventoryEmployee[] InventoryEmployees = new InventoryEmployee[100];
        ////////////////////////////////////////////////////////////////////
        //here is to get admin info in objects
        String filePath = "C:\\Users\\LENOVO\\Documents\\JavaProjects\\HyperMarketProject\\Project\\src\\main\\java\\finalproject\\HyperMarketProject\\Users_Info\\Admin.txt";

        Scanner scanner = null;
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: ");
        } else {
            scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String adminInfo = scanner.nextLine();
                String[] info = adminInfo.split(",");
                Admins[i] = Admin.get(info[1].trim(), info[2].trim());
                i++;
            }
        }

        filePath = "C:\\Users\\LENOVO\\Documents\\JavaProjects\\HyperMarketProject\\Project\\src\\main\\java\\finalproject\\HyperMarketProject\\Users_Info\\MarketingEmployees.txt";

        File file2 = new File(filePath);
        if (!file2.exists()) {
            System.out.println("File not found: ");
        } else {
            scanner = new Scanner(file2);
            int i = 0;
            while (scanner.hasNextLine()) {
                String Seller = scanner.nextLine();
                String[] info = Seller.split(",");
                Sellers[i] = SellerEmployee.add(info[1].trim(), info[2].trim());
                i++;
            }
        }

        filePath = "C:\\Users\\LENOVO\\Documents\\JavaProjects\\HyperMarketProject\\Project\\src\\main\\java\\finalproject\\HyperMarketProject\\Users_Info\\MarketingEmployees.txt";

        File file3 = new File(filePath);
        if (!file3.exists()) {
            System.out.println("File not found: ");
        } else {
            scanner = new Scanner(file3);
            int i = 0;
            while (scanner.hasNextLine()) {
                String MarketingInfo= scanner.nextLine();
                String[] info = MarketingInfo.split(",");
                MarketingEmployees[i] = MarketingEmployee.add(info[1].trim(), info[2].trim());
                i++;
            }
        }

        filePath = "C:\\Users\\LENOVO\\Documents\\JavaProjects\\HyperMarketProject\\Project\\src\\main\\java\\finalproject\\HyperMarketProject\\Users_Info\\InventoryEmployee.txt";

        File file4 = new File(filePath);
        if (!file4.exists()) {
            System.out.println("File not found: ");
        } else {
            scanner = new Scanner(file4);
            int i = 0;
            while (scanner.hasNextLine()) {
                String InventoryInfo= scanner.nextLine();
                String[] info = InventoryInfo.split(",");
                InventoryEmployees[i] = InventoryEmployee.add(info[1], info[2]);
                i++;
            }
        }


        while (true) {
            int userTypeMenu;
            String userType = "";
            boolean isLogged = false;

            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to Code cart market System");
            while (true) {
                System.out.println("Enter number to choose your role to login : ");
                System.out.print("""
                        1- Admin
                        2- Marketing employee
                        3- Inventory employee
                        4- Seller
                        """);
                userTypeMenu = input.nextInt();

                input.nextLine();

                switch (userTypeMenu) {
                    case 1: {
                        userType = "Admin";
                        break;
                    }
                    case 2: {
                        userType = "MarketingEmployees";
                        break;
                    }
                    case 3: {
                        userType = "InventoryEmployee";
                        break;
                    }
                    case 4: {
                        userType = "sellers";
                        break;
                    }
                    default: {
                        userType = "error";
                        System.out.println("Invalid choice");
                    }
                }
                if (!(userType.equals("error"))) {
                    break;
                }
            }

            String username, password;
            do {
                System.out.print("""
                        ---------------------------------------------
                                            Login
                        ---------------------------------------------
                        """);
                System.out.print(" Username: ");
                username = input.nextLine().trim();
                System.out.print(" Password: ");
                password = input.nextLine().trim();
                isLogged = Login(Admins, Sellers, MarketingEmployees, InventoryEmployees,username, password, userType );
                if (!isLogged) {
                    System.out.println("Invalid username or password");
                }
            } while (!isLogged);
            switch (userTypeMenu) {
                case 1: {
                    while(true) {
                        System.out.println("Welcome " + username);
                        System.out.println("""
                                1- Add Marketing employee
                                2- Add Inventory employee
                                3- Add Seller employee
                                4- Update Marketing employee
                                5- Update Seller employee
                                6- Update Inventory employee
                                7- Delete Marketing employee
                                8- Delete Inventory employee
                                9- Delete Seller employee
                                8- List all employees
                                9- Update My Information
                                10-Check Reports
                                """);
                        int operation = input.nextInt();
                        switch (operation)
                        {
                            case 1 :
                            {
                                Admin.add(MarketingEmployees);
                                break;
                            }
                            case 2:
                            {
                                Admin.add(InventoryEmployees);
                                break;
                            }
                            case 3 :
                            {
                                Admin.add(Sellers);
                                break;

                            }

                        }
                        }
                }
                default:
                    break;
                }

                }
            }



    public static boolean Login(Admin[] admins, SellerEmployee[] sellers,
                                MarketingEmployee[] marketingEmployees, InventoryEmployee[] inventoryEmployees, String username, String password,String type) throws IOException {

        String filePath = "C:\\Users\\LENOVO\\Documents\\JavaProjects\\HyperMarketProject\\Project\\src\\main\\java\\finalproject\\HyperMarketProject\\Users_Info\\/" + type + ".txt";
        Scanner scanner = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found: " + filePath);
                return false;
            }

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String employeeInfo = scanner.nextLine();
                String[] info = employeeInfo.split(",\\s*");

                if (type.equals("Admin")) {
                    for (Admin admin : admins) {
                        if(admin == null)
                            return false;
                        if (admin.getUsername().equals(username) && admin.getPassword().equals(password))
                            return true;
                    }
                } else if (type.equals("seller employee")) {
                    for (SellerEmployee seller : sellers) {
                        if(seller == null)
                            return false;
                        if (seller.getUsername().equals(username) && seller.getPassword().equals(password))
                            return true;
                    }
                } else if (type.equals("Inventory employee")) {
                    for (InventoryEmployee Inventoryemp : inventoryEmployees )
                    {
                        if(Inventoryemp == null)
                            return false;
                        if (Inventoryemp.getUsername().equals(username)&& Inventoryemp.getPassword().equals(password))
                            return true;
                    }
                }
                else
                {
                    for (MarketingEmployee marketingEmp : marketingEmployees )
                    {
                        if(marketingEmp == null)
                            return false;
                        if (marketingEmp.getUsername().equals(username) && marketingEmp.getPassword().equals(password))
                            return true;

                    }
                }

                return false;
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return false;
    }
}



