package com.bbq.dao;

import com.bbq.beans.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    int insertStudent(Student t);

    Student getStudentById(long id);

    List<Student> getStudentByName(String name);

    boolean updateStudent(Student t);

    int deleteStudentById(long id);
}
