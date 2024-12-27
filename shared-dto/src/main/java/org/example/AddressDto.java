package org.example;

public class AddressDto {

    private String country;
    private String city;
    private String town;
    private String street;
    private Long apartmentNo;
    private Long doorNumber;

    public AddressDto() {
    }

    public AddressDto(String country,
                      String city,
                      String town,
                      String street,
                      Long apartmentNo,
                      Long doorNumber) {
        this.country=country;
        this.city = city;
        this.town=town;
        this.street = street;
        this.apartmentNo = apartmentNo;
        this.doorNumber = doorNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Long getApartmentNo() {
        return apartmentNo;
    }

    public Long getDoorNumber() {
        return doorNumber;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }
}
