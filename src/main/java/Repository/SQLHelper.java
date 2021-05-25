package Repository;

import Repository.Constants.*;

import java.util.Map;

public class SQLHelper {
    public static final String ID = "id";
    private static final String INSERT_QUERY = "INSERT INTO ";
    private static final String VALUES = " VALUES ";
    private static final String WHERE = "WHERE ";
    private static final String SELECT = "SELECT";
    public static final String USER_TABLE = "users";
    public static final String SUBJECTS_TABLE = "subjects";

    public final static String SQL_GET_SUBJECTS = "select * from " + SUBJECTS_TABLE;

    public final static String SQL_GET_USER_BY_LOGIN = "SELECT * from " + USER_TABLE + " WHERE " +
            UserTableConstants.LOGIN.getFieldName() + " = (?)";

    public final static String SQL_INSERT_USER = "INSERT INTO " + USER_TABLE + "(" +
            UserTableConstants.LOGIN.getFieldName() + " ," +
            UserTableConstants.PASSWORD.getFieldName() + " ," +
            UserTableConstants.USER_TYPE.getFieldName() + ") VALUES (? , ?, ?)";

    public final static String SQL_INSERT_SUBJECT = INSERT_QUERY + SUBJECTS_TABLE + "(" +
            SubjectsTableConstants.NAME + " ," +
            SubjectsTableConstants.TEACHER + " ," +
            SubjectsTableConstants.FACULTY + ") VALUES(? , ? , ?)";

    public static String makeInsertQuery(Map<String, Object> fields, String table) {
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            if (column.equals(ID)) {
                continue;
            }
            columns.append(column).append(", ");
            values.append("?, ");
        }

        values.deleteCharAt(values.lastIndexOf(","));
        columns.deleteCharAt(columns.lastIndexOf(","));
        values.append(")");
        columns.append(")");

        return INSERT_QUERY + table + columns + VALUES + values + ";";
    }

    public  static String deleteByNameQuery(String name, String table)
    {
        return "DELETE FROM " + table + " WHERE name = \"" + name + "\";";
    }

    public  static String deleteByIDQuery(String id, String table)
    {
        return "DELETE FROM " + table + " WHERE id = " + id + ";";
    }
}
