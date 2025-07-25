package com.iut.firstclass.controllers;


import com.iut.firstclass.models.dto.AttendanceDto;
import com.iut.firstclass.models.entity.Student;
import com.iut.firstclass.repositories.AttendanceRepository;
import com.iut.firstclass.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final AttendanceRepository attendanceRepository;

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

    @GetMapping("/getAllAttendanceByDate")
    public List<AttendanceDto> getAllAttendanceByDate(
            @RequestParam("studentId") Long studentId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {
        return studentService.findAttendanceOfStudentByIdAndDate(studentId, startDate, endDate);
    }

    @PostMapping("/seedData")
    public String seedData() {
        // Create students
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Alice");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Bob");

        studentService.save(student1);
        studentService.save(student2);

        // Add attendance for 2 months for both students
        LocalDate startDate = LocalDate.now().minusMonths(2);
        LocalDate endDate = LocalDate.now();

        for (Student student : List.of(student1, student2)) {
            LocalDate date = startDate;
            while (!date.isAfter(endDate)) {
                com.iut.firstclass.models.entity.Attendance attendance = new com.iut.firstclass.models.entity.Attendance();
                attendance.setDate(date);
                attendance.setAttendanceType(
                    date.getDayOfMonth() % 3 == 0 ? 
                    com.iut.firstclass.models.enums.AttendanceType.ABSENT : 
                    com.iut.firstclass.models.enums.AttendanceType.PRESENT
                );
                attendance.setStudent(student);
                attendanceRepository.save(attendance);
                date = date.plusDays(1);
            }
        }
        return "Seeded data for students 1 and 2 for 2 months.";
    }



}
