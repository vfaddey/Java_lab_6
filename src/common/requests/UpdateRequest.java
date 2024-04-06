package common.requests;

import common.exceptions.WrongParameterException;
import common.model.Address;
import common.model.Coordinates;
import common.model.OrganizationType;
import client.managers.Validator;

public class UpdateRequest extends Request implements RequestWithParameters {
    private long id;
    private String name;
    private Coordinates coordinates;
    private long annualTurnover;
    private int employeesCount;
    private OrganizationType organizationType;
    private Address address;

    public UpdateRequest(String commandName) {
        super(commandName);
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

    @Override
    public void setParameters(String... parameters) throws WrongParameterException {
        if (!parameters[0].isEmpty()) {
            if (Validator.isCorrectNumber(parameters[0], Long.class)) {
                this.id = Long.parseLong(parameters[0]);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
        } else {
            throw new WrongParameterException("Параметр пуст.");
        }
    }

    public long getId() {
        return id;
    }
}
