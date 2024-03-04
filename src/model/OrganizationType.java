package model;

public enum OrganizationType {
    COMMERCIAL("Коммерческая"),
    PUBLIC("Публичная"),
    GOVERNMENT("Государственная"),
    PRIVATE_LIMITED_COMPANY("ООО"),
    OPEN_JOINT_STOCK_COMPANY("ОАО");

    private final String translation;

    OrganizationType(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "OrganizationType{"
                + translation + '}';
    }
}
