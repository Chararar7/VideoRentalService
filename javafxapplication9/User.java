/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;

/**
 *
 * @author ALI CHARARA
 */
public class User {
    private final int id;
    private final String username;
    private final String password;
    private final String email;
    private final String creditCardNumber;
    private final int type;

    // Constructor
    public User(int id, String username, String password, String email, String creditCardNumber,int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.type=type;
    }

    public int getType() {
        return type;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
}
