package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.model.Address;

public class AddressSupplier {

    private static final String TEST_COUNTRY = "UK";
    private static final String TEST_CITY = "London";
    private static final String TEST_ZIP_CODE = "198303";
    private static final String TEST_STREET = "Spring street";
    private static final String TEST_BUILDING = "10";
    private static final String TEST_FLAT = "5";

    public static Address getAddress() {
        Address address = new Address();
        address.setAddressId(1);
        address.setCountry(TEST_COUNTRY);
        address.setZipCode(TEST_ZIP_CODE);
        address.setCity(TEST_CITY);
        address.setStreet(TEST_STREET);
        address.setBuilding(TEST_BUILDING);
        address.setFlat(TEST_FLAT);

        return address;
    }

    public static AddressDTO getAddressDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(1);
        addressDTO.setCountry(TEST_COUNTRY);
        addressDTO.setZipCode(TEST_ZIP_CODE);
        addressDTO.setCity(TEST_CITY);
        addressDTO.setStreet(TEST_STREET);
        addressDTO.setBuilding(TEST_BUILDING);
        addressDTO.setFlat(TEST_FLAT);

        return addressDTO;
    }

}
