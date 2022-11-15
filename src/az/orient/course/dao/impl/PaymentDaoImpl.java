package az.orient.course.dao.impl;

import az.orient.course.dao.PaymentDao;
import az.orient.course.model.*;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "    p.id, "
                + "    s.id AS student_id, "
                + "    s.name AS student_name, "
                + "    s.surname AS student_surname, "
                + "    t.id AS teacher_id, "
                + "    t.name AS teacher_name, "
                + "    t.surname AS teacher_surname, "
                + "    l.id AS lesson_id, "
                + "    l.lesson_name AS lesson_name, "
                + "    p.amount, "
                + "    p.date_time "
                + "FROM "
                + "    payment AS p "
                + "        INNER JOIN "
                + "    student AS s ON p.s_id = s.id "
                + "        INNER JOIN "
                + "    teacher AS t ON p.t_id = t.id "
                + "        INNER JOIN "
                + "    lesson AS l ON p.l_id = l.id "
                + "WHERE "
                + "    p.active = 1 and s.active=1 and t.active=1";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("p.id"));
                    Student student = new Student();
                    student.setId(rs.getLong("student_id"));
                    student.setName(rs.getString("student_name"));
                    student.setSurname(rs.getString("student_surname"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("teacher_id"));
                    teacher.setName(rs.getString("teacher_name"));
                    teacher.setSurname(rs.getString("teacher_surname"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("lesson_name"));
                    payment.setStudent(student);
                    payment.setTeacher(teacher);
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPayDate(rs.getDate("date_time"));
                    paymentList.add(payment);

                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into videoders.payment (id,s_id,t_id,l_id,amount) values (?,?,?,?,?)";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, payment.getId());
                ps.setLong(2, payment.getStudent().getId());
                ps.setLong(3, payment.getTeacher().getId());
                ps.setLong(4, payment.getLesson().getId());
                ps.setDouble(5, payment.getAmount());
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

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,s_id,t_id,l_id,amount  FROM videoders.payment where active=1 and id=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    payment.setId(rs.getLong("id"));
                    Student student = new Student();
                    student.setId(rs.getLong("s_id"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("t_id"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("l_id"));
                    payment.setStudent(student);
                    payment.setTeacher(teacher);
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    System.out.println(payment.getStudent().getId());
                    System.out.println(payment.getTeacher().getId());
                    System.out.println(payment.getLesson().getId());
                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return payment;
    }

    @Override
    public boolean updatePayment(Payment payment) throws Exception {
        boolean result=false;
        Connection c=null;
        PreparedStatement ps=null;
        String sql="update payment set s_id=?,t_id=?,l_id=?,amount=? where id=?";
        try
        {
           c= JdbcUtility.getMyConnection();
           if(c!=null)
           {
              ps=c.prepareStatement(sql) ;
              ps.setInt(1, (int) payment.getStudent().getId());
              ps.setInt(2, (int) payment.getTeacher().getId());
               ps.setInt(3, (int) payment.getLesson().getId());
               ps.setDouble(4,  payment.getAmount());
               ps.setInt(5, (int) payment.getId());
               ps.execute();
               result=true;
           }else {
               System.out.println("Connection is null!");
           }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }

    @Override
    public List<Payment> searchPaymentData(String keyword) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "    p.id, "
                + "    s.id AS student_id, "
                + "    s.name AS student_name, "
                + "    s.surname AS student_surname, "
                + "    t.id AS teacher_id, "
                + "    t.name AS teacher_name, "
                + "    t.surname AS teacher_surname, "
                + "    l.id AS lesson_id, "
                + "    l.lesson_name AS lesson_name, "
                + "    p.amount, "
                + "    p.date_time "
                + "FROM "
                + "    payment AS p "
                + "        INNER JOIN "
                + "    student AS s ON p.s_id = s.id "
                + "        INNER JOIN "
                + "    teacher AS t ON p.t_id = t.id "
                + "        INNER JOIN "
                + "    lesson AS l ON p.l_id = l.id "
                + "WHERE "
                + "    p.active = 1 and s.active=1 and t.active=1 and " +
                "lower(s.name) like lower (?) or lower(s.surname) like lower (?) or " +
                "lower( t.name) like lower (?) " +
                "or lower( t.surname) like lower (?) or lower(l.lesson_name) like lower (?) ";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1,"%"+keyword+"%");
                ps.setString(2,"%"+keyword+"%");
                ps.setString(3,"%"+keyword+"%");
                ps.setString(4,"%"+keyword+"%");
                ps.setString(5,"%"+keyword+"%");

                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("p.id"));
                    Student student = new Student();
                    student.setId(rs.getLong("student_id"));
                    student.setName(rs.getString("student_name"));
                    student.setSurname(rs.getString("student_surname"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("teacher_id"));
                    teacher.setName(rs.getString("teacher_name"));
                    teacher.setSurname(rs.getString("teacher_surname"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("lesson_name"));
                    payment.setStudent(student);
                    payment.setTeacher(teacher);
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPayDate(rs.getDate("date_time"));
                    paymentList.add(payment);

                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;    }

    @Override
    public List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception {
        DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "    p.id, "
                + "    s.id AS student_id, "
                + "    s.name AS student_name, "
                + "    s.surname AS student_surname, "
                + "    t.id AS teacher_id, "
                + "    t.name AS teacher_name, "
                + "    t.surname AS teacher_surname, "
                + "    l.id AS lesson_id, "
                + "    l.lesson_name AS lesson_name, "
                + "    p.amount, "
                + "    p.date_time "
                + "FROM "
                + "    payment AS p "
                + "        INNER JOIN "
                + "    student AS s ON p.s_id = s.id "
                + "        INNER JOIN "
                + "    teacher AS t ON p.t_id = t.id "
                + "        INNER JOIN "
                + "    lesson AS l ON p.l_id = l.id "
                + "WHERE "
                + "    p.active = 1 and s.active=1 and t.active=1";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                if(advancedSearch.getLessonId()!=null )
                    sql +=" AND l.id= "+advancedSearch.getLessonId();
                if(advancedSearch.getTeacherId()!=null)
                    sql +=" AND t.id= "+advancedSearch.getTeacherId();
                if(advancedSearch.getMinAmount()!=null)
                    sql +=" AND p.amount>= "+advancedSearch.getMinAmount();
                if(advancedSearch.getMaxAmount()!=null)
                    sql +=" AND p.amount<= "+advancedSearch.getMaxAmount();
                if(advancedSearch.getBeginDate()!=null && !advancedSearch.getBeginDate().isEmpty())//bu mende stringdir bir defe util date sonra ise sql date etmeliyem bunu
                    sql +=" AND p.date_time>= "+new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime());
                if(advancedSearch.getEndDate()!=null && !advancedSearch.getEndDate().isEmpty())
                    sql +=" AND p.date_time<= "+new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime());

                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getLong("p.id"));
                    Student student = new Student();
                    student.setId(rs.getLong("student_id"));
                    student.setName(rs.getString("student_name"));
                    student.setSurname(rs.getString("student_surname"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("teacher_id"));
                    teacher.setName(rs.getString("teacher_name"));
                    teacher.setSurname(rs.getString("teacher_surname"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("lesson_name"));
                    payment.setStudent(student);
                    payment.setTeacher(teacher);
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPayDate(rs.getDate("date_time"));
                    paymentList.add(payment);

                }

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;    }
}
