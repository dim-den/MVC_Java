package Builders;

public class BuilderFactory {
    private static final String USER = "user";
    private static final String SUBJECTS = "subjects";
    private static final String ERROR_MESSAGE = "Unknown Builder name!";

    public static IBuilder create(String builderName) {
        switch (builderName) {
            case USER:
                return new UserBuilder();
            case SUBJECTS:
                return new SubjectBuilder();
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
