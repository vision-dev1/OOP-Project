/**
 * Abstract class representing a generic User in the system.
 */
public abstract class User {
    private final int userID;
    private String name;
    private String email;
    private String role;
    private String password;

    public User(int userID, String name, String email, String role, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.err.println("Warning: Invalid email format assigned.");
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        System.out.println("\n✅ " + name + " logged in as " + role + ".");
    }

    public void logout() {
        System.out.println("\n👋 " + name + " has been logged out.");
    }

    public abstract void displayRole();
}
