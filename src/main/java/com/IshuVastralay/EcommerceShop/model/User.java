package com.IshuVastralay.EcommerceShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private String mobile;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> addressList=new ArrayList<>();
    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information",joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation>paymentInformationList=new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating>ratingList=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Review>reviewList=new ArrayList<>();

    private LocalDateTime localDateTIme;

    public User(){}

    public User(Long id, String firstName, String lastName, String password, String email, String role, String mobile, List<Address> addressList, List<PaymentInformation> paymentInformationList, List<Rating> ratingList, List<Review> reviewList, LocalDateTime localDateTIme) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.mobile = mobile;
        this.addressList = addressList;
        this.paymentInformationList = paymentInformationList;
        this.ratingList = ratingList;
        this.reviewList = reviewList;
        this.localDateTIme = localDateTIme;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<PaymentInformation> getPaymentInformationList() {
        return paymentInformationList;
    }

    public void setPaymentInformationList(List<PaymentInformation> paymentInformationList) {
        this.paymentInformationList = paymentInformationList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public LocalDateTime getLocalDateTIme() {
        return localDateTIme;
    }

    public void setLocalDateTIme(LocalDateTime localDateTIme) {
        this.localDateTIme = localDateTIme;
    }
}
