package com.example.springboot.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "stores")
public class StoreModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idStore;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String password;
    private String passwordSalt;

    //Relations
    @OneToMany(mappedBy = "store")
    private List<ProductModel> products;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;

    public UUID getIdStore() {
        return idStore;
    }

    public void setIdStore(UUID idStore) {
        this.idStore = idStore;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
