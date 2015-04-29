/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author jmaj
 */
public class TO_User implements Serializable {

    private String mail;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private String passwordReap;
    private TO_Permission permission;

    public String getPasswordReap() {
        return passwordReap;
    }

    public void setPasswordReap(String passwordReap) {
        this.passwordReap = passwordReap;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TO_Permission getPermission() {
        return permission;
    }

    public void setPermission(TO_Permission permission) {
        this.permission = permission;
    }
}
