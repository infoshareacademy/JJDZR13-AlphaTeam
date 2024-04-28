package pl.isa.alphateam.customer;

public class Address {
    String country;
    String city;
    String street;
    int streetNo;

    public Address() {
    }

    public Address(String country, String city, String street, int streetNo) {
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

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNo=" + streetNo +
                '}';
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }
}
