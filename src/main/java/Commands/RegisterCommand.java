package Commands;

import Exceptions.LoginAlreadtExistsException;
import Exceptions.LoginDoesNotExistsException;
import Exceptions.ServiceException;
import Exceptions.WrongPasswordException;
import Models.User;
import Models.UserType;
import Pages.Page;
import Services.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static Commands.Constants.AuthenticationConstants.*;
import static java.util.Optional.of;

public class RegisterCommand implements ICommand {
    private static Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationService authenticationService = new AuthenticationService();

        Optional<String> login = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(LOGIN));
        Optional<String> password = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(PASSWORD));

        User user = null;
        try {
            user = authenticationService.register(login.get(), password.get());
        } catch (LoginAlreadtExistsException e) {
            request.setAttribute(ERROR_MESSAGE, "Login already exists!");
            return new CommandResult(Page.REGISTER_PAGE.getPage(), false);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE, "An error occurred!");
            return new CommandResult(Page.REGISTER_PAGE.getPage(), false);
        }
        request.getSession().setAttribute("userType", user.getRole());


        LOGGER.info("user has been authorized: login: " + login);

        return new CommandResult(COMMAND_WELCOME, true);
    }
}
