package com.library.msalquiler.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.library.msalquiler.model.Rent;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String mail;

    private String address;

    private String phone;

    @Column(unique = true)
    private String identity;

    private int type_identity;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Rent> rents;


    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate birthdate, String mail, String address, String phone, String identity, int type_identity, List<Rent> rents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
        this.type_identity = type_identity;
        this.rents = rents;
    }

    public Client(Long id, String firstName, String lastName, LocalDate birthdate, String mail, String address, String phone, String identity, int type_identity, List<Rent> rents) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
        this.type_identity = type_identity;
        this.rents = rents;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getType_identity() {
        return type_identity;
    }

    public void setType_identity(int type_identity) {
        this.type_identity = type_identity;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Rent> getRents() {
        return rents;

    }
}
