package Pages;

public enum Page {
    LOGIN_PAGE("/Views/login.jsp"),
    REGISTER_PAGE("/Views/register.jsp"),
    WELCOME_PAGE("/Views/welcome.jsp"),
    ERROR_PAGE("/Views/error.jsp");

    private String page;

    Page(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
