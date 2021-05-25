package Commands;

import Commands.CommandResult;
import Commands.ICommand;
import Exceptions.ServiceException;
import Models.Subject;
import Services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static Commands.Constants.AuthenticationConstants.COMMAND_WELCOME;
import static java.util.Optional.of;

public class AddNewSubjectCommand implements ICommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        SubjectService subjectService = new SubjectService();
        Optional<String> name = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter("name"));
        Optional<String> teacher = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter("teacher"));
        Optional<String> faculty = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter("faculty"));


        Subject subject = new Subject(name.get(), teacher.get(), faculty.get());
        subjectService.save(subject);

        return new CommandResult(COMMAND_WELCOME, true);
    }
}
