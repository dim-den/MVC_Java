package Commands.Factory;

import Commands.*;

public class CommandFactory {
    public static ICommand createCommand(String command) throws IllegalAccessException {
        command = command.toUpperCase();
        CommandType commandType = CommandType.valueOf(command);
        ICommand result = null;

        switch (commandType) {
            case LOGIN:
                result = new LoginCommand();
                break;
            case REGISTER:
                result = new RegisterCommand();
                break;
            case LOGIN_PAGE:
                result = new LoginPageCommand();
                break;
            case REGISTER_PAGE:
                result = new RegisterPageCommand();
                break;
            case WELCOME:
                result = new WelcomeCommand();
                break;
            case ADD_SUBJECT:
                result = new AddNewSubjectCommand();
                break;
            case DELETE_SUBJECT:
                result = new DeleteSubjectCommand();
                break;
            default:
                throw new IllegalAccessException("Invalid command " + command);
        }

        return result;
    }
}
