package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotNull
    private Double main_account;


//Relatia OneToOne cu USER

    @JsonIgnoreProperties({"account","currency"})
    @OneToOne(mappedBy = "account")
    private residentUser residentUser;

    public Account(){

    }

    public Account(String id, Double main_account, residentUser residentUser) {
        this.id = id;
        this.main_account = main_account;
        this.residentUser = residentUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMain_account() {
        return main_account;
    }

    public void setMain_account(Double main_account) {
        this.main_account = main_account;
    }

    public residentUser getResidentUser() {
        return residentUser;
    }

    public void setResidentUser(residentUser residentUser) {
        this.residentUser = residentUser;
    }

}