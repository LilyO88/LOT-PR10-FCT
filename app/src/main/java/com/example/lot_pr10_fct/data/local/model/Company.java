package com.example.lot_pr10_fct.data.local.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "companies",
        indices = {@Index(value = {"name"},
                unique = true)
        })
public class Company {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String cif;
    private String address;
    private String phone;
    private String email;
    private String contactName;

    public Company(String name, String cif, String address, String phone, String email, String contactName) {
        this.name = name;
        this.cif = cif;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.contactName = contactName;
    }

    public Company() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
