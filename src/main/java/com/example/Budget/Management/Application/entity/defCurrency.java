package com.example.Budget.Management.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "default_currencies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class defCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String default_Currency;

    //    Relatia OneToOne cu admin
    @JsonIgnoreProperties({"adminAccount","defCurrency"})
    @OneToOne(mappedBy = "defCurrency")
    private Admin admin;


    public defCurrency() {
    }

    public defCurrency(String id, String default_Currency, Admin admin) {
        this.id = id;
        this.default_Currency = default_Currency;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefault_Currency() {
        return default_Currency;
    }

    public void setDefault_Currency(String default_Currency) {
        this.default_Currency = default_Currency;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
