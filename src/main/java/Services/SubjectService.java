package Services;

import Exceptions.RepositoryException;
import Exceptions.ServiceException;
import Models.Subject;
import Repository.RepositoryCreator;
import Repository.SubjectRepository;

import java.util.List;

public class SubjectService {
    public List<Subject> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            SubjectRepository subjectRepository = repositoryCreator.getSubjectRepository();
            return subjectRepository.findAll();
        } catch (RepositoryException | Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Subject subject) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            SubjectRepository subjectRepository = repositoryCreator.getSubjectRepository();
            subjectRepository.save(subject);
        } catch (Exception exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void deleteByID(String id) throws Exception {
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            SubjectRepository subjectRepository = repositoryCreator.getSubjectRepository();
            subjectRepository.deleteByID(id);
        }
    }
}
