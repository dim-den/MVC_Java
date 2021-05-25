package Repository.Constants;

public enum SubjectsTableConstants {
    ID("id"),
    NAME("name"),
    TEACHER("teacher"),
    FACULTY("faculty");

    private String fieldName;

    private SubjectsTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
