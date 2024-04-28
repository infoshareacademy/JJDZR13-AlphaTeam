package pl.isa.alphateam.customer;

import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;

import java.time.LocalDate;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .birthdayDate(LocalDate.parse(customer.getBirthdayDate()))
                .phoneNumber(customer.getPhoneNumber())
                .country(customer.getAddress().country)
                .city(customer.getAddress().city)
                .street(customer.getAddress().street)
                .streetNo(customer.getAddress().streetNo)
                .password(customer.getPassword())
                .patentNo(customer.getPatentNo())
                .emailAddress(customer.getEmailAddress())
                .build();
    }


    public static Customer mapToCustomer(CustomerDto customerDto){
        Address address = new Address(customerDto.getCountry(),
                customerDto.getCity(),
                customerDto.getStreet(),
                customerDto.getStreetNo());
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .birthdayDate(String.valueOf(customerDto.getBirthdayDate()))
                .phoneNumber(customerDto.getPhoneNumber())
                .patentNo(customerDto.getPatentNo())
                .emailAddress(customerDto.getEmailAddress())
                .password(customerDto.getPassword())
                .address(address)
                .build();
    }
}
