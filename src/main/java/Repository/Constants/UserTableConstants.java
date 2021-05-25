package Repository.Constants;

public enum UserTableConstants {
    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    USER_TYPE("userType");

    private String fieldName;

    private UserTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
