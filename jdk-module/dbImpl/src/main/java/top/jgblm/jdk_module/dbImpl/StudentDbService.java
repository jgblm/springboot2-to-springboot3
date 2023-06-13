package top.jgblm.jdk_module.dbImpl;

import top.jgblm.jdk_module.model.Student;
import top.jgblm.jdk_module.service.StudentService;

public class StudentDbService implements StudentService {
    @Override
    public String create(Student student) {
        return student.getId();
    }

    @Override
    public Student read(String id) {
        return new Student();
    }

    @Override
    public Student update(Student student) {
        return student;
    }

    @Override
    public String delete(String id) {
        return id;
    }
}
