/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author RobertM
 */
public class TO_Customer implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    String getNameS() {
        if (this.name == null) {
            return "";
        }
        return name;
    }

    String getSurnameS() {
        if (this.surname == null) {
            return "";
        }
        return surname;
    }

    String getPhoneS() {
        if (this.phone == null) {
            return "";
        }
        return phone;
    }

}
