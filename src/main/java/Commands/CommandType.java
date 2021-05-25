package Commands;

public enum CommandType {

    LOGIN("login"),
    REGISTER("register"),
    LOGIN_PAGE("login_page"),
    REGISTER_PAGE("register_page"),
    WELCOME("welcome"),
    ADD_SUBJECT("add_subject"),
    DELETE_SUBJECT("delete_subject");

    private String  command;

    CommandType(String command) {
        this.command = command;
    }
}
