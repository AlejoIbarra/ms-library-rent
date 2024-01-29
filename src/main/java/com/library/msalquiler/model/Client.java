package com.library.msalquiler.model;


import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    private LocalDate birthdate;

    private String mail;

    private String address;

    private String phone;

    @Column(unique = true)
    private String identity;

    private int type_identity;

    @OneToMany(mappedBy = "client")
    private List<Rent> rents;

    public Client() {
    }

    public Client(Long id, String first_name, String last_name, LocalDate birthdate, String mail, String address, String phone, String identity, int type_identity) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
        this.type_identity = type_identity;
    }

    public Client(String first_name, String last_name, LocalDate birthdate, String mail, String address, String phone, String identity, int type_identity) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
        this.type_identity = type_identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
