package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "phoneNumber", "isActive" })})

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class residentUser {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @NotNull
    @NotBlank
    @Size(min = 3, max = 15)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 15)
    private String lastName;

    @NotNull
    @NotBlank
    @Column(unique = true, length = 10)
    @Pattern(regexp = "^07[0-9]{8}$")
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String blockNumber;


    @NotNull
    private Integer apartmentNumber;

    @NotNull
    private Double lockedBalance;


    //OneToOne - Relation-account
    @JsonIgnoreProperties("residentUser")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_balance_id" , referencedColumnName = "id")
    private Account account;


    //OneToOne - Relation- currency
    @JsonIgnoreProperties("residentUser")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "currency_id", referencedColumnName = "id")
    private Currency currency;




    public residentUser(){

    }

    public residentUser(Long id, String firstName, String lastName,
                        String phoneNumber, String blockNumber,
                        Integer apartmentNumber,
                        Double lockedBalance, Account account, Currency currency ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.blockNumber = blockNumber;
        this.apartmentNumber = apartmentNumber;
        this.lockedBalance = lockedBalance;
        this.account = account;
        this.currency = currency;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Double getLockedBalance() {
        return lockedBalance;
    }

    public void setLockedBalance(Double lockedBalance) {
        this.lockedBalance = lockedBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


}
