package pl.isa.alphateam.customer;

import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
@Data
@Builder
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String birthdayDate;
    private String phoneNumber;
    private String patentNo;
    private Address address;
    private String emailAddress;
    private String password;

    public Customer() {
    }

    public Customer(int id,String firstName, String lastName, String birthdayDate, String phoneNumber, String patentNo, Address address, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.phoneNumber = phoneNumber;
        this.patentNo = patentNo;
        this.address = address;
        this.emailAddress = emailAddress;
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

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate() {
        this.birthdayDate = birthdayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                ", phoneNumber= '" + phoneNumber + '\'' +
                ", patentNo='" + patentNo + '\'' +
                ", address=" + address +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(birthdayDate, customer.birthdayDate) && Objects.equals(patentNo, customer.patentNo) && Objects.equals(address, customer.address) && Objects.equals(emailAddress, customer.emailAddress) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdayDate, patentNo, address, emailAddress, password);
    }
}
