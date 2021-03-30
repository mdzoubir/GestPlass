package org.example.DAO;

import org.example.Model.StudentEntity;
import org.example.Model.UserEntity;

import java.util.List;

public interface StudentDAO {
    public  void addStudent(StudentEntity studentEntity);
    public StudentEntity getStudentById(int id);
    public List<StudentEntity> getAllStudents();
    public void deleteStudent(int id);
    public StudentEntity updateStudent(StudentEntity student);
}
