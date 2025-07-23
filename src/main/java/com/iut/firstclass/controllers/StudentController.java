package com.iut.firstclass.controllers;


import com.iut.firstclass.models.entity.Student;
import com.iut.firstclass.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @PostMapping("/addNewStudent")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/getStudentID")
    public Student getStudentID(@RequestParam("id") Long id) {
        return studentService.findById(id);
    }
}
