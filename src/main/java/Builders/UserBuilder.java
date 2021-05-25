package Builders;

import Exceptions.RepositoryException;
import Models.User;
import Models.UserType;
import Repository.Constants.UserTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements IBuilder<User> {

    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(UserTableConstants.ID.getFieldName());
            String login = resultSet.getString(UserTableConstants.LOGIN.getFieldName());
            String password = resultSet.getString(UserTableConstants.PASSWORD.getFieldName());
            UserType userType = UserType.valueOf(resultSet.getString(UserTableConstants.USER_TYPE.getFieldName()));

            return new User(id, login, password, userType);
        }
        catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }
}
