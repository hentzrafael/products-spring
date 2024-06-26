package com.example.springboot.models;

import com.example.springboot.user.models.UserModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "addresses")
public class AddressModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAddress;

    private String street;
    private String district;
    private String houseNumber;
    private String city;
    private String state;
    private String country;

    //Relations
    @OneToOne(mappedBy = "address")
    private StoreModel store;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "address")
    private List<OrderModel> orders;
}
