package vn.id.tozydev.demo.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {
    
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
            "service", "student",
            "status", "ok",
            "message", "Student service is running"
        );
    }
    
    @GetMapping("/list")
    public Map<String, Object> getStudents() {
        return Map.of(
            "service", "student",
            "students", new String[]{"Alice", "Bob", "Charlie"}
        );
    }
}
