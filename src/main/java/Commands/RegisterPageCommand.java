package Commands;

import Exceptions.ServiceException;
import Pages.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterPageCommand implements ICommand{
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        return new CommandResult(Page.REGISTER_PAGE.getPage(), false );
    }
}
