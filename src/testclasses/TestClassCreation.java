package testclasses;

import datamodels.*;
import java.time.LocalDate;
import java.time.Month;

public class TestClassCreation {

    public static void main(String[] args) {

        // Create a date which will be used in all test methods
        LocalDate testDate = LocalDate.of(2018, Month.MAY, 4);
        
        // Create a classroom 
        Classroom classroom = new Classroom();
        classroom.setRoomNumber("THX1138");
        classroom.setRoomType("CB");
        classroom.setCapacity(1);
        System.out.println(classroom.toString());

        // Create a course
        Course course = new Course();
        course.setCourseID("JEDI301");
        course.setCourseName("Deflecting Blaster Bolts, Tips & Techniques");
        course.setClassroom(classroom);
        System.out.println(course.toString());

        // Create a Faculty member
        Faculty faculty = new Faculty();
        faculty.setDateOfBirth(testDate);
        faculty.setName("Obi-Wan Kenobi");
        faculty.setAddress("Stewjon");
        faculty.setStatus("FT");
        faculty.setDateOfHire(testDate);
        faculty.setSalary(100000000);
        faculty.getListOfCourses().add(course);
        System.out.println(faculty.toString());

        // Create a Student 
        Student newStudent = new Student();
        newStudent.setDateOfBirth(testDate);
        newStudent.setName("Luke Skywalker");
        newStudent.setAddress("Tatooine");
        newStudent.setStudentID(000327);
        newStudent.setDateOfGraduation(testDate);
        newStudent.setGPA(2.5f);
        newStudent.getListOfCourses().add(course);
        System.out.println(newStudent.toString());

    }
}
