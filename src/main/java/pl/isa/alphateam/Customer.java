package pl.isa.alphateam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Calendar;

public class Customer {
    private String firstName;
    private String lastName;
    private String birthdayDate;
    private String phoneNumber;
    private String patentNo;
    private Address address;
    private String emailAddress;
    private String password;
    public Customer(String firstName, String lastName, String birthdayDate, String phoneNumber, String patentNo, String emailAddress, String password) {
    }

    public Customer(String firstName, String lastName, String birthdayDate, String phoneNumber, String patentNo, Address address, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        setBirthdayDate(birthdayDate);
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
    public void setBirthdayDate(String birthdayDate) { //setter przeprowadza walidację wprowadzanych danych
        int age = calculateAge(birthdayDate);
        if (age < 18) {
            System.out.println("Musisz być pełnletni/a aby wynająć łódź!");
            System.exit(0);
        } else{
                this.birthdayDate = birthdayDate;
        }
    }
    private int calculateAge(String birthdayDate) {
        try {
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDate); //parsuje - zmienia format na Date
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            Calendar today = Calendar.getInstance(); //obiekt Calendar reprezentujący dzisiejszą datę
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR); //oblcza różnicę między rokiem urodzenia a dzisiejszym dniem i przypisuje do zmiennej age
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--; //sprawdza czy ?
            }
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
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
}
