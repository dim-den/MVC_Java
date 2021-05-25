package Repository;

import Builders.UserBuilder;
import Exceptions.RepositoryException;
import Models.User;
import Repository.Constants.UserTableConstants;
import Repository.ParamSpecification.Parameter;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> {
    UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return SQLHelper.USER_TABLE;
    }

    @Override
    protected Map<String, Object> getFields(User user) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(UserTableConstants.LOGIN.getFieldName(), user.getLogin());
        fields.put(UserTableConstants.PASSWORD.getFieldName(), user.getPassword());
        return fields;
    }

    @Override
    public List<User> query(String sqlString, Parameter parameter) throws RepositoryException {
        List<User> users = executeQuery(sqlString,new UserBuilder(), parameter.getParameters());
        return users;
    }

    @Override
    public Optional<User> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException {
        List<User> user = query(sqlString, parameter);
        return user.size() == 1 ?
                Optional.of(user.get(0)) :
                Optional.empty();
    }
}
