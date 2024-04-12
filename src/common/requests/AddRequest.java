package common.requests;

import common.model.Address;
import common.model.Coordinates;
import common.model.OrganizationType;

public class AddRequest extends Request {
    private String name;
    private Coordinates coordinates;
    private long annualTurnover;
    private int employeesCount;
    private OrganizationType organizationType;
    private Address address;

    public AddRequest(String commandName) {
        super(commandName);
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getAnnualTurnover() {
        return annualTurnover;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setAnnualTurnover(long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
