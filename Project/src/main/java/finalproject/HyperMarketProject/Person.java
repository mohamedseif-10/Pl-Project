package finalproject.HyperMarketProject;

public abstract class Person {

    private static int ID_COUNTER = 0;
    private int ID ;
    private String type;
    private String username;
    private String password;

    Person(String Username, String Password, String role) {
        ID = ++ID_COUNTER;
        this.type = role;
        this.username = Username;
        this.password = Password;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static int getNumberOfUsers() {
        return ID_COUNTER;
    }
}
