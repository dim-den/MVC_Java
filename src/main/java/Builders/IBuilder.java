package Builders;

import Exceptions.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IBuilder<T> {
    T build(ResultSet resultSet) throws RepositoryException;
}
