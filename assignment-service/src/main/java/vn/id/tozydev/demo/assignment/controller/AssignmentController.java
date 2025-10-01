package vn.id.tozydev.demo.assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AssignmentController {
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "service", "assignment",
                "status", "ok",
                "message", "Assignment service is running"
        );
    }

    @GetMapping("/list")
    public Map<String, Object> getAssignments() {
        return Map.of(
                "service", "assignment",
                "assignments", new String[]{"Java Basics", "Spring Boot API", "Microservices Design"}
        );
    }
}
