package Personal;

import UniversityRealization.*;

enum Subjects {Maths, Physics, CPP}


/**
 * Используется для хранения информации о студенте.
 */
public class Student {
    private String name; /** Имя студента */
    private Subjects subjects = Subjects.Maths; /** предметы студента. */
    private StudentRecord_book studentRecord_book = null; /** зачетка студента. */
    private University university; /** университет студента. */

    public Student(String name, University university) {
        this.name = name;
        this.university = university;
    }

    public String getName() {
        return this.name;
    }

    public  StudentRecord_book getStudentRecord_book() {
        return this.studentRecord_book;
    }

    public void setStudentRB(StudentRecord_book studentRecord_book) {
        this.studentRecord_book = studentRecord_book;
    }

    public void enterData() {
        this.studentRecord_book.setStudentData(this.name);
    }


    /**
     * Данный метод используется для сдачи экзамена студентом, вызывая
     * при этом метод takeTheExam класса Professor.
     * @see Professor
     */
    public void passTheExam() {
        university.getProfessor().takeTheExam(studentRecord_book);
    }
}



