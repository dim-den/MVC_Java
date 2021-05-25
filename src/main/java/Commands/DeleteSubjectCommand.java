package Commands;

import Exceptions.ServiceException;
import Models.Subject;
import Pages.Page;
import Services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static Commands.Constants.AuthenticationConstants.COMMAND_WELCOME;
import static Commands.Constants.AuthenticationConstants.ERROR_MESSAGE;
import static java.util.Optional.of;

public class DeleteSubjectCommand implements ICommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        SubjectService subjectService = new SubjectService();
        Optional<String> id = of(request)
                .map(httpServletRequest -> httpServletRequest.getParameter("id"));

        if(!id.isPresent()) {
            request.setAttribute("AddErrorMessage", "Empty id!");
            return new CommandResult(COMMAND_WELCOME, false);
        }

        try {
            subjectService.deleteByID(id.get());
        } catch (Exception exception) {
            request.setAttribute("AddErrorMessage", "An error occurred!");
            return new CommandResult(COMMAND_WELCOME, false);
        }

        return new CommandResult(COMMAND_WELCOME, true);
    }
}
