package common.requests;

import common.model.Address;
import common.model.Coordinates;
import common.model.OrganizationType;

public class AddRequest extends Request {
    public String name;
    public Coordinates coordinates;
    public long annualTurnover;
    public int employeesCount;
    public OrganizationType organizationType;
    public Address address;

    public AddRequest(String name, Coordinates coordinates, long annualTurnover, int employeesCount, OrganizationType organizationType, Address address) {
        super("add");
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.organizationType = organizationType;
        this.address = address;
    }
}
