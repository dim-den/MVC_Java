package Services;

import Exceptions.*;
import Models.User;
import Models.UserType;
import Repository.ParamSpecification.UserByLogin;
import Repository.ParamSpecification.UserByLoginPassword;
import Repository.RepositoryCreator;
import Repository.SQLHelper;
import Repository.UserRepository;

import java.util.Optional;

public class AuthenticationService {
    private final HashingService hashingService = new HashingService();

    public User login(String login, String password) throws LoginDoesNotExistsException, ServiceException, WrongPasswordException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLogin params = new UserByLogin(login);
            Optional<User> user = userRepository.queryForSingleResult(SQLHelper.SQL_GET_USER_BY_LOGIN, params);

            if(user.isPresent() == false) {
                throw new LoginDoesNotExistsException(login);
            }

            if ( hashingService.authenticate(password, user.get().getPassword())) {
                return user.get();
            }
            throw new WrongPasswordException();

        } catch (RepositoryException | Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User register(String login, String password) throws ServiceException, LoginAlreadtExistsException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLogin params = new UserByLogin(login);

            if(userRepository.queryForSingleResult(SQLHelper.SQL_GET_USER_BY_LOGIN, params).isPresent()) {
                throw new LoginAlreadtExistsException(login);
            }

            String hashedPassword = hashingService.hash(password);
            User user = new User(login, hashedPassword);
            userRepository.save(user);

            return user;
        } catch (RepositoryException | Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
