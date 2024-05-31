package com.example.springboot.models;

import com.example.springboot.user.models.UserModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
public class OrderModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;

    private String createdAt;
    private String status;
    private Double totalAmount;

    //Relations
    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "idOrder"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "idProduct"))
    private List<ProductModel> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;
}
