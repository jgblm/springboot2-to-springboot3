package top.jgblm.jdk_module.service;

import top.jgblm.jdk_module.model.Student;

public interface StudentService {
    String create(Student student);

    Student read(String id);

    Student update(Student student);

    String delete(String id);
}
