package com.iut.firstclass.models.entity;


import com.iut.firstclass.models.enums.AttendanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private AttendanceType attendanceType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
