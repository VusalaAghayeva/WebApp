package az.orient.course.dao.impl;

import az.orient.course.dao.LessonDao;
import az.orient.course.model.Lesson;
import az.orient.course.model.Student;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static az.orient.course.util.JdbcUtility.getMyConnection;

public class LessonDaoImpl implements LessonDao {
    @Override
    public List<Lesson> getLessonList()throws Exception {
            List<Lesson> lessonList = new ArrayList<Lesson>();
            Connection c = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "select id,lesson_name,lesson_price,data_date from lesson where active=1";
            try {
               c= getMyConnection();
                if (c != null) {
                    ps = c.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Lesson lesson = new Lesson();
                        lesson.setId(rs.getLong("id"));
                        lesson.setLessonName(rs.getString("lesson_name"));
                        lesson.setLessonPrice(rs.getDouble("lesson_price"));
                        lesson.setLessonTime(rs.getDate("data_date"));
                        lessonList.add(lesson);

                    }
                } else {
                    System.out.println("Connection is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcUtility.close(c, ps, rs);
            }
            return lessonList;
        }
    }
