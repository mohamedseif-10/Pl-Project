package finalproject.HyperMarketProject;

public class InventoryEmployee extends Person{

    public InventoryEmployee(String Username, String Password, String role) {
        super(Username, Password, role);
    }

    public static InventoryEmployee add(String username, String password)
    {
        InventoryEmployee InventoryEmp = new InventoryEmployee(username, password, "Inventory employee");
        return InventoryEmp;
    }
}
