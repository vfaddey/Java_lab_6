package common.responses;

import common.model.Organization;

import java.util.LinkedList;

public class ShowResponse extends Response {
    private LinkedList<Organization> organizations;

    public ShowResponse(String commandName, String message) {
        super(commandName, message);
    }

    public LinkedList<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(LinkedList<Organization> organizations) {
        this.organizations = organizations;
        StringBuilder stringBuilder = new StringBuilder();
        for (Organization organization : this.organizations) {
            stringBuilder.append(organization).append("\n");
        }
        this.message = stringBuilder.toString();
    }

    @Override
    public String toString() {
        return this.message;
    }
}
