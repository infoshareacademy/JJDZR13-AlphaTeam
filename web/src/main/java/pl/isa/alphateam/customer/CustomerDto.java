package pl.isa.alphateam.customer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Please enter birth date")
    @Past(message = "Birth date should be less than current date!!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdayDate;
    @NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "^(\\d){10}$", message = "Mobile number is invalid!!")//^(\d){9}$
    private String phoneNumber;
    @NotBlank(message = "Patent no cannot be empty")
    private String patentNo;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email is invalid!!")
    private String emailAddress;
    @NotBlank(message = "Password no cannot be empty")
    private String password;
    //address ----------------------------
    @NotBlank(message = "Country no cannot be empty")
    private String country;
    @NotBlank(message = "City no cannot be empty")
    private String city;
    @NotBlank(message = "Street no cannot be empty")
    private String street;
    @Min(value = 1, message = "Street number can not be negative")
    private int streetNo;


}
