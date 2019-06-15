import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TeacherDataAccess {

    private Connection conn;
    private static final String teacherTable = "teacher";

    public TeacherDataAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        //STEP 2: Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java_codereview06" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // we will use this connection to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

    /**
     * Get all db records
     * @return
     * @throws SQLException
     */
    public List<Teacher> getAllRows()  throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacherName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Teacher> list = new ArrayList<>();

        while (rs.next()) {
            int i = rs.getInt("teacherId");
            String name = rs.getString("teacherName");
            String surname = rs.getString("teacherSurname");
            String email = rs.getString("teacherEmail");
            list.add(new Teacher(i, name, surname, email));
        }

        pstmnt.close(); // also closes related result set
        return list;
    }

    public List<Classes> getAllRows2(int i) throws SQLException {

        String sql = "SELECT class.classId, class.className FROM class INNER JOIN teacherclass ON class.classId = teacherclass.fk_classId WHERE teacherclass.fk_teacherId = ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1, i);
        ResultSet rs = pstmnt.executeQuery();
        List<Classes> listClasses = new ArrayList<>();

        while (rs.next()) {
            int i2 = rs.getInt("classId");
            String name = rs.getString("className");
            listClasses.add(new Classes(i2, name));
        }
        pstmnt.close();
        return listClasses;
    }

/*
    public boolean nameExists(Teacher teacher) throws SQLException {

        String sql = "SELECT COUNT(id) FROM " + teacherTable + " WHERE name = ? AND id <> ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setString(1, teacher.getTeacherName());
        pstmnt.setInt(2, teacher.getTeacherId());
        ResultSet rs = pstmnt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        pstmnt.close();

        if (count > 0) {

            return true;
        }

        return false;
    }
 */
}

