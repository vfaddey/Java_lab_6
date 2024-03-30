package common.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Organization implements Comparable<Organization>, Serializable {

    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private final Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private final OrganizationType type; //Поле не может быть null
    private final Address officialAddress; //Поле может быть null

    public Organization(Long id, String name, Coordinates coordinates, LocalDate creationDate, Long annualTurnover, Integer employeesCount, OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", employeesCount=" + employeesCount +
                ", type=" + type +
                ", officialAddress=" + officialAddress +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Organization that = (Organization) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(annualTurnover, that.annualTurnover) && Objects.equals(employeesCount, that.employeesCount) && type == that.type && Objects.equals(officialAddress, that.officialAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, annualTurnover, employeesCount, type, officialAddress);
    }

    @Override
    public int compareTo(Organization o) {
        int compareById = this.id.compareTo(o.getId());
        if (compareById != 0) {
            return compareById;
        }

        int compareByName = this.name.compareTo(o.getName());
        if (compareByName != 0) {
            return compareByName;
        }

        int compareByAnnualTurnover = this.annualTurnover.compareTo(o.getAnnualTurnover());
        if (compareByAnnualTurnover != 0) {
            return compareByAnnualTurnover;
        }

        return this.employeesCount.compareTo(o.getEmployeesCount());
    }
}
