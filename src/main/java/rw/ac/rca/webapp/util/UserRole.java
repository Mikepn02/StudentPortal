package rw.ac.rca.webapp.util;

public enum UserRole {
    ADMINISTRATOR("System Administrator"), EMPLOYEE(
            "Employee"), GUEST(
            "Guest"),NONE("");
    private String roleDescription;

    UserRole(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
