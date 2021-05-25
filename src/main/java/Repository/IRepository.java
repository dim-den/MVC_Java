package Repository;

import Exceptions.RepositoryException;
import Repository.ParamSpecification.Parameter;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface IRepository<T> {
    List<T> query(String sqlString, Parameter parameter) throws RepositoryException;
    Optional<T> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException;
    List<T> findAll() throws RepositoryException;
    Integer save(T object) throws SQLException;
    void deleteByName(String string);
    void deleteByID(String id);
}
