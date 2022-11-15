package az.orient.course.dao.impl;

import az.orient.course.dao.DBHelper;
import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    public List<Student> getstudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,surname,dob,address,phone,email from student where active=1";
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/videoders?useSSl=false";
            // Database name to access
            String dbName = "videoders";
            String dbUsername = "root";
            String dbPassword = "12345";

            Class.forName(dbDriver);
            c = DriverManager.getConnection(dbURL,
                    dbUsername,
                    dbPassword);
           // c = DBHelper.getConnetcion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getString("dob"));
                    student.setAdress(rs.getString("address"));
                    student.setEmail(rs.getString("email"));
                    student.setPhone(rs.getString("phone"));
                    studentList.add(student);

                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return studentList;
    }

    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into videoders.student(name,surname,address,phone,dob,email) values(?,?,?,?,?,?)";
        String dbName = "videoders";
        String dbUsername = "root";
        String dbPassword = "12345";
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/videoders?useSSl=false";
        Class.forName(dbDriver);
        c = DriverManager.getConnection(dbURL,
                dbUsername,
                dbPassword);
        try {
//            c = DBHelper.getConnetcion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getAdress());
                ps.setString(4, student.getPhone());
                ps.setString(5, student.getDob() + "");
                ps.setString(6, student.getEmail());

                ps.execute();
                result = true;

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
        } finally {

            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,surname,dob,address,phone,email from student" + " where active=1 and id=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, studentId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getString("dob"));
                    student.setAdress(rs.getString("address"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));

                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return student;
    }

    public boolean updateStudent(Student student1, Long studentId) throws Exception {

        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update student set name=?,surname=?,address=?,dob=?,phone=?" + " where id=?";

        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student1.getName());
                ps.setString(2, student1.getSurname());
                ps.setString(3, student1.getAdress());
                ps.setString(4, student1.getDob());
                ps.setString(5, student1.getPhone());
                ps.setLong(6, studentId);

                ps.execute();
                result = true;

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
        } finally {

            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    public boolean deleteStudent(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update student set active=0" + " where id=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    public List<Student> searchStudentData(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,surname,dob,address,phone from student" + " where active=1"
                + " and name like (?)or surname like (?) or address like (?)";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getString("dob"));
                    student.setAdress(rs.getString("address"));
                    student.setPhone(rs.getString("phone"));
                    studentList.add(student);

                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return studentList;
    }

}
