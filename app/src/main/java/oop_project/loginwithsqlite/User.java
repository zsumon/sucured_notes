package oop_project.loginwithsqlite;

import java.io.Serializable;

/**
 * Created by Sumon on 7/23/2017.
 */

public class User implements Serializable {

    private String userName;
    private String password;

    private String name;
    private String email;
    private String securityQuestion;

    private String securityAnswer;

    public User(String userName, String password, String name, String email, String securityQuestion) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.securityQuestion = securityQuestion;
        this.email = email;
    }

    public User(String userName, String password, String name, String email, String securityQuestion, String securityAnswer) {
        this(userName, password, name, email, securityQuestion);
        this.securityAnswer = securityAnswer;
    }


    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }


    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}


