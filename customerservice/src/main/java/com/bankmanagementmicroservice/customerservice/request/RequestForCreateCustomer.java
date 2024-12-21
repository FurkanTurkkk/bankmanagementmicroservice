package com.bankmanagementmicroservice.customerservice.request;

import com.bankmanagementmicroservice.customerservice.validation.ValidName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestForCreateCustomer {

    @NotEmpty(message = "İsim boş olamaz.")
    @ValidName
    private String firstName;

    @NotEmpty(message = "Soyisim boş olamaz.")
    @ValidName
    private String lastName;

    @Size(min = 11,max = 11,message = "TC kimlik numarası 11 haneyi geçemez veya eksiz olamaz.")
    @Pattern(regexp = "\\d+",message = "TC kimlik numarası sadece rakamlardan oluşmalıdır.")
    private String tc;

    private Long addressId;
    private LocalDate birthDay;

    @Size(min = 11,max = 11,message = "Telefon numarası 11 haneyi geçemez veya eksiz olamaz.")
    @Pattern(regexp = "\\d+",message = "Telefon numarası sadece rakamlardan oluşmalıdır.")
    private String phoneNumber;

    public RequestForCreateCustomer() {
    }

    public RequestForCreateCustomer(String firstName,
                                    String lastName,
                                    String tc,
                                    LocalDate birthDay,
                                    Long addressId,
                                    String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc = tc;
        this.birthDay = birthDay;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTc() {
        return tc;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
