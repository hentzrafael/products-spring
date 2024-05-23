package com.example.springboot.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    private String fullName;
    private String dateOfBirth;
    private String cpf;
    private String email;
    private String password;
    private String passwordSalt;

    //Relations
    @OneToMany(mappedBy = "user")
    private List<AddressModel> addresses;

    @OneToMany(mappedBy = "user")
    private List<CommentModel> comments;

    @OneToMany(mappedBy = "user")
    private List<OrderModel> orders;

}
