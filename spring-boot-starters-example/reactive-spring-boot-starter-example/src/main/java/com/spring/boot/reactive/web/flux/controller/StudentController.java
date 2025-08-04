package com.spring.boot.reactive.web.flux.controller;

import com.spring.boot.reactive.web.flux.entity.Student;
import com.spring.boot.reactive.web.flux.service.StudentService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 学生控制器
 *
 * @author lihuagang
 */
@RestController("studentController")
@RequestMapping(path = "/student", produces = APPLICATION_JSON_VALUE)
@ResponseBody
public class StudentController {
    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @PostMapping("/save/{name}")
    public Mono<Boolean> save(@PathVariable("name") String name) {
        studentService.save(name);
        return Mono.just(Boolean.TRUE);
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable("id") String id) {
        return Mono.just(studentService.findById(id));
    }
}
