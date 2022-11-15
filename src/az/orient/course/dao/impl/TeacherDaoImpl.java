package az.orient.course.dao.impl;

import az.orient.course.dao.DBHelper;
import az.orient.course.dao.TeacherDao;
import az.orient.course.model.Teacher;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id, name,surname,address,phone,dob FROM videoders.teacher";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getInt("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setSurname(rs.getString("surname"));
                    teacher.setAdress(rs.getString("address"));
                    teacher.setPhone(rs.getString("phone"));
                    teacher.setDob(rs.getDate("dob"));

                    teacherList.add(teacher);
                }

            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);

        }
        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherComboByLessonId(Long lessonId) throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select distinct  t.id,t.name,t.surname from payment  p inner join teacher  t on t.id=p.t_id\n" +
                "inner join lesson l on l.id=p.l_id where t.active=1 and l.id=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,lessonId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getInt("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setSurname(rs.getString("surname"));

                    teacherList.add(teacher);
                }

            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);

        }
        return teacherList;    }

}
