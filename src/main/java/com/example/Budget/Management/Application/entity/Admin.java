package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "admins",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "phoneNumber", "isActive" })})

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;



    @NotNull
    @NotBlank
    @Size(min=3, max=15)
    private String firstName;


    @NotNull
    @NotBlank
    @Size(min=3, max=15)
    private String lastName;


    @NotNull
    @NotBlank
    @Column(unique = true, length = 10)
    @Pattern(regexp = "^07[0-9]{8}$")
    private String phoneNumber;

    // OneToOne - Relation- adminaccount
    @JsonIgnoreProperties("admin")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "admin_account", referencedColumnName = "id")
    private adminAccount adminAccount;


    //OneToOne - Relation- currency,
    @JsonIgnoreProperties("admin")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "default_currency_id", referencedColumnName = "id")
    private defCurrency defCurrency;



    public Admin(){

    }

    public Admin(String id, String firstName, String lastName, String phoneNumber,adminAccount adminAccount, defCurrency defCurrency) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.adminAccount = adminAccount;
        this.defCurrency = defCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public adminAccount getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(adminAccount adminAccount) {
        this.adminAccount = adminAccount;
    }

    public defCurrency getDefCurrency() {
        return defCurrency;
    }

    public void setDefCurrency(defCurrency defCurrency) {
        this.defCurrency = defCurrency;
    }





}
