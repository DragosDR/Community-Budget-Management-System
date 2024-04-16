package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "currencies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String currencyName;


    //Relatia OneToOne cu USER
    @JsonIgnoreProperties({"account","currency"})
    @OneToOne(mappedBy = "currency")
    private residentUser residentUser;


    public Currency() {
    }

    public Currency(String id, String currencyName, residentUser residentUser) {
        this.id = id;
        this.currencyName = currencyName;
        this.residentUser = residentUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public residentUser getResidentUser() {
        return residentUser;
    }

    public void setResidentUser(residentUser residentUser) {
        this.residentUser = residentUser;
    }




}
