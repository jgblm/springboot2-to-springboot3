package top.jgblm.jdk_module.client;

import top.jgblm.jdk_module.dbImpl.StudentDbService;
import top.jgblm.jdk_module.model.Student;
import top.jgblm.jdk_module.service.StudentService;

public class StudentClient {
    public static void main(String[] args) {
        StudentService service = new StudentDbService();
        service.create(new Student());
        service.read("17SS0001");
        service.update(new Student());
        System.out.println(service.delete("17SS0001"));
    }
}
