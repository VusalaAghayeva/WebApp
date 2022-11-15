package az.orient.course.service;

import az.orient.course.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeacherList() throws Exception;
    List<Teacher> getTeacherComboByLessonId(Long lessonId) throws Exception;

}
