package Exceptions;

import java.sql.SQLException;

public class RepositoryException extends Throwable {
    public RepositoryException(String message, SQLException exception) {
    }
}
