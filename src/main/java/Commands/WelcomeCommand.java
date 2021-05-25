package Commands;

import Exceptions.ServiceException;
import Models.Subject;
import Pages.Page;
import Services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Commands.Constants.WelcomePageConstants.*;

public class WelcomeCommand implements ICommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        SubjectService subjectService = new SubjectService();
        List<Subject> subjects = subjectService.findAll();

        request.setAttribute(SUBJECTS, subjects);

        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}
