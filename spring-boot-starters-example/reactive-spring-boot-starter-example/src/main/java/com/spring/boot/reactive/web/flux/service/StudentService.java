package com.spring.boot.reactive.web.flux.service;

import com.spring.boot.reactive.web.flux.entity.Student;
import com.spring.boot.reactive.web.flux.model.Gender;
import com.spring.boot.reactive.web.flux.repository.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * 学生服务
 *
 * @author lihuagang
 * @since 2024/11/5
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    public void save(String name) {
        Student entity = new Student("123456", name, Gender.MALE, 1);
        studentRepository.save(entity);
    }

    public Student findById(String id) {
        return studentRepository.findById(id)
                .orElse(null);
    }
}
