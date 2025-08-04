package com.spring.boot.reactive.web.flux.repository;

import com.spring.boot.reactive.web.flux.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 学生的存储库
 *
 * @author lihuagang
 */
@Repository("studentRepository")
public interface StudentRepository extends CrudRepository<Student, String> {
}
