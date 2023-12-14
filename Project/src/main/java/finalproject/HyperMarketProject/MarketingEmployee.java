package finalproject.HyperMarketProject;

public class MarketingEmployee extends Person{

    MarketingEmployee(String Username, String Password, String role) {
        super(Username, Password, "MarketingEmployee");
    }

    public static MarketingEmployee add(String username, String password)
    {
        MarketingEmployee marketingEmp = new MarketingEmployee(username, password, "Marketing employee");
        return marketingEmp;
    }

}
