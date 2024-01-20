package pl.isa.alphateam;

public class Address {
    String country;
    String city;
    String street;
    String streetNo;

    public Address(String country, String city, String street, String streetNo) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNo = streetNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }
}
