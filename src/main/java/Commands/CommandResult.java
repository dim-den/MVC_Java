package Commands;

public class CommandResult {
    private String page;
    private boolean isRedirect;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public CommandResult() {}

    public CommandResult(String page)
    {
        setPage(page);
    }
    public CommandResult(String page, boolean isRedirect)
    {
        setPage(page);
        setRedirect(isRedirect);
    }

}
