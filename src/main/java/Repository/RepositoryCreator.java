package Repository;

import Connection.ConnectionPool;

import java.sql.Connection;

public class RepositoryCreator implements AutoCloseable {
    private Connection connection;
    private ConnectionPool connectionPool;

    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }

    public SubjectRepository getSubjectRepository() {
        return new SubjectRepository(connection);
    }

    @Override
    public void close() throws Exception {
        connectionPool.releaseConnection(connection);
    }
}
