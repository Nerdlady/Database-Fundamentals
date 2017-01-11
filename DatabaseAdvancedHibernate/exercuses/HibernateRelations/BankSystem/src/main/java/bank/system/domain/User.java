package bank.system.domain;

import bank.system.annotations.Email;
import bank.system.annotations.Password;
import bank.system.annotations.Username;

import javax.persistence.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<BasicAccount> accounts;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 3) {
            throw new IllegalArgumentException("Invalid username");
        }
        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(username);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;
    }

    @Basic
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+@([A-Za-z-]+)\\.([A-Za-z]+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    @Basic
    @Password(containsSpecialSymbol = false, minLength = 6)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid password");
        }


        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Invalid password");
        }


        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Invalid password");
        }


        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Invalid password");
        }


        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    public Set<BasicAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<BasicAccount> accounts) {
        this.accounts = accounts;
    }
}
