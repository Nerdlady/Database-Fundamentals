package entities;

import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.util.Date;


@Entity(name = "users")
public class User {
    @Id
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "last_login_time")
    private Date lastLoginInTime;

    @Column(name = "is_active")
    private boolean isActive;


    public User() {
    }

    public User(String username, String password, int age, Date registrationDate, Date lastLognInTime, boolean idActive) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.registrationDate = registrationDate;
        this.lastLoginInTime = lastLognInTime;
        this.isActive = idActive;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginInTime() {
        return lastLoginInTime;
    }

    public void setLastLoginInTime(Date lastLoginInTime) {
        this.lastLoginInTime = lastLoginInTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
