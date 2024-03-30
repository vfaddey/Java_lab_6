package common.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    private final String zipCode; //Поле не может быть null
    private final Location town; //Поле не может быть null

    public Address(String zipCode, Location town) {
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getTown() {
        return town;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", town=" + town +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Address address = (Address) object;
        return Objects.equals(zipCode, address.zipCode) && Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, town);
    }
}
