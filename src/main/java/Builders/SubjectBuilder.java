package Builders;

import Exceptions.RepositoryException;
import Models.Subject;
import Repository.Constants.SubjectsTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectBuilder implements  IBuilder<Subject> {
    @Override
    public Subject build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(SubjectsTableConstants.ID.getFieldName());
            String name = resultSet.getString(SubjectsTableConstants.NAME.getFieldName());
            String teacher = resultSet.getString(SubjectsTableConstants.TEACHER.getFieldName());
            String faculty = resultSet.getString(SubjectsTableConstants.FACULTY.getFieldName());

            return new Subject(id, name, teacher, faculty);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }
}

