package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admins_account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class adminAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotNull
    private Double collected_funds;

    //Relatia OneToOne cu admin
    @JsonIgnoreProperties({"adminAccount","defCurrency"})
    @OneToOne(mappedBy = "adminAccount")
    private Admin admin;



    public adminAccount() {
    }

    public adminAccount(String id, Double collected_funds, Admin admin) {
        this.id = id;
        this.collected_funds = collected_funds;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCollected_funds() {
        return collected_funds;
    }

    public void setCollected_funds(Double collected_funds) {
        this.collected_funds = collected_funds;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
