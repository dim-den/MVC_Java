package Repository;

import Builders.BuilderFactory;
import Builders.IBuilder;
import Exceptions.RepositoryException;
import Models.UserType;

import java.sql.*;
import java.util.*;

public abstract class AbstractRepository<T> implements IRepository<T> {
    private Connection connection;
    private static final String GET_ALL_QUERY = "SELECT * FROM ";

    protected abstract String getTableName();

    AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    private static Integer getType(Object object) {
        if (object instanceof Integer) {
            return Types.INTEGER;
        }
        if (object instanceof Float) {
            return Types.FLOAT;
        }
        if (object instanceof UserType) {
            return Types.INTEGER;
        }
        if (object instanceof String) {
            return Types.VARCHAR;
        } else {
            return Types.NULL;
        }
    }

    public static void prepare(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i) == null) {
                preparedStatement.setNull(i + 1, getType(parameters.get(i)));
            } else {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
        }
    }

    public static void prepare(PreparedStatement preparedStatement, Map<String, Object> fields, String tableName) throws SQLException {
        int i = 1;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (!key.equals(SQLHelper.ID)) {
                if (value == null) {
                    preparedStatement.setNull(i++, getType(value));
                } else {
                    preparedStatement.setObject(i++, value);
                }
            }
        }
        Object id = fields.get(SQLHelper.ID);
        if (id != null) {
            preparedStatement.setString(i++, String.valueOf(id));
        }
    }

    public List<T> executeQuery(String sql, IBuilder<T> builder, List<Object> parameters) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            prepare(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } catch (RepositoryException e) {
            throw e;
        }
        return objects;
    }

    protected Optional<T> executeQueryForSingleResult(String query, IBuilder<T> builder, List<Object> parameters) throws RepositoryException {
        List<T> items = executeQuery(query, builder, parameters);
        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    protected abstract Map<String, Object> getFields(T obj);

    @Override
    public void deleteByName(String name) {
        String sql = SQLHelper.deleteByNameQuery(name, getTableName());
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteByID(String id) {
        String sql = SQLHelper.deleteByIDQuery(id, getTableName());
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Integer save(T object) throws SQLException {
        Map<String, Object> fields = getFields(object);
        String sql = SQLHelper.makeInsertQuery(fields, getTableName());
        return executeSave(sql, fields);
    }

    private Integer executeSave(String query, Map<String, Object> fields) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepare(preparedStatement, fields, getTableName());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer generatedId = null;
            while (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
            return generatedId;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<T> findAll() throws RepositoryException {
        IBuilder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName();
        return executeQuery(query, builder, Collections.emptyList());
    }
}
