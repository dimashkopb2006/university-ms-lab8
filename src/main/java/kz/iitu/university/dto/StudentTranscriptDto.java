package kz.iitu.university.dto;

import java.util.List;

public record StudentTranscriptDto(
        Long studentId,
        String studentName,
        List<CourseResult> courses,
        Double averageScore
) {
    public record CourseResult(String courseCode, String courseName, Integer score, String letterGrade) {}
}
