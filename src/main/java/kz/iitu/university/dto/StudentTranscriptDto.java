package kz.iitu.university.dto;

import java.util.List;

// Data from all three databases in one response
public record StudentTranscriptDto(
        Long studentId,
        String studentName,
        List<CourseResult> courses,
        Double averageScore
) {
    public record CourseResult(String courseCode, String courseName, Integer score, String letterGrade) {}
}
