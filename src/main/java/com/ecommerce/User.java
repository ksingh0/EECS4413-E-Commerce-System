package com.ecommerce;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
private Long id;
private String username;
private String password;
private String firstName;
private String lastName;
private String streetName;
private String streetNumber;
private String city;
private String postalCode;
private String country;
private boolean isSeller;

public User(){}
public User(Long id, String username, String password, String firstName, String lastName,
        String streetName, String streetNumber, String city, String postalCode, String country, boolean isSeller) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.city = city;
    this.postalCode = postalCode;
    this.country = country;
    this.isSeller = isSeller;
}

public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
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
public String getStreetName() {
    return streetName;
}
public void setStreetName(String streetName) {
    this.streetName = streetName;
}
public String getStreetNumber() {
    return streetNumber;
}
public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
}
public String getCity() {
    return city;
}
public void setCity(String city) {
    this.city = city;
}
public String getPostalCode() {
    return postalCode;
}
public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
}
public String getCountry() {
    return country;
}
public void setCountry(String country) {
    this.country = country;
} 
public boolean isSeller() {
    return isSeller;
}
public void setSeller(boolean isSeller) {
    this.isSeller = isSeller;
} 

@Override
public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
            + ", lastName=" + lastName + ", streetName=" + streetName + ", streetNumber=" + streetNumber + ", city="
            + city + ", postalCode=" + postalCode + ", country=" + country + ", isSeller=" + isSeller + "]";
}
}

