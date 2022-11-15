package az.orient.course.dao;

import az.orient.course.model.Teacher;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getTeacherList() throws Exception;
    List<Teacher> getTeacherComboByLessonId(Long lessonId) throws Exception;

}
