package Repository;

import Builders.SubjectBuilder;
import Builders.UserBuilder;
import Exceptions.RepositoryException;
import Models.Subject;
import Models.User;
import Repository.Constants.SubjectsTableConstants;
import Repository.Constants.UserTableConstants;
import Repository.ParamSpecification.Parameter;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SubjectRepository extends AbstractRepository<Subject> {
    SubjectRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return SQLHelper.SUBJECTS_TABLE;
    }

    @Override
    protected Map<String, Object> getFields(Subject subject) {
        Map<String, Object> fields = new HashMap<>();
        fields.put(SubjectsTableConstants.NAME.getFieldName(), subject.getName());
        fields.put(SubjectsTableConstants.TEACHER.getFieldName(), subject.getTeacher());
        fields.put(SubjectsTableConstants.FACULTY.getFieldName(), subject.getFaculty());
        return fields;
    }

    @Override
    public List<Subject> query(String sqlString, Parameter parameter) throws RepositoryException {
        List<Subject> subjects = executeQuery(sqlString,new SubjectBuilder(), parameter.getParameters());
        return subjects;
    }

    @Override
    public Optional<Subject> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException {
        List<Subject> subjects = query(sqlString, parameter);
        return subjects.size() == 1 ?
                Optional.of(subjects.get(0)) :
                Optional.empty();
    }
}
