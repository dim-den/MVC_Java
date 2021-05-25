package Commands;

import Exceptions.LoginDoesNotExistsException;
import Exceptions.ServiceException;
import Exceptions.WrongPasswordException;
import Models.User;
import Pages.Page;
import Services.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static Commands.Constants.AuthenticationConstants.*;
import static java.util.Optional.of;

public class LoginCommand implements ICommand {
    private static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthenticationService authenticationService = new AuthenticationService();

        Optional<String> login = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(LOGIN));
        Optional<String> password = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter(PASSWORD));

        try {
            User user = authenticationService.login(login.get(), password.get());
            request.getSession().setAttribute("userType", user.getRole());

        } catch (LoginDoesNotExistsException e) {
            request.setAttribute(ERROR_MESSAGE, "Wrong login!");
            return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE, "An error occurred!");
            return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
        } catch (WrongPasswordException e) {
            request.setAttribute(ERROR_MESSAGE, "Wrong password!");
            return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
        }

        NotifyJmsListner("user has been authorized, login: " + login.get());
        LOGGER.info("user has been authorized, login: " + login);

        return new CommandResult(COMMAND_WELCOME, true);
    }

    private void NotifyJmsListner(String message) {
        try {
            ConnectionFactory factory;
            factory = new ConnectionFactory();
            try (JMSContext context = factory.createContext("admin", "admin")) {

                factory.setProperty(ConnectionConfiguration.imqAddressList,
                        "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

                JMSProducer producer = context.createProducer();
                Destination Topic = context.createTopic("topicDestination");
                producer.send(Topic, message);
            } catch (Exception e) {
                LOGGER.error("Exception while sending jms message");
            }
        } catch (Exception exception) {
            LOGGER.error("Exception while sending jms message");
        }
    }
}
