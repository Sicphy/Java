package UniversityRealization;

import Personal.*;

/**
 * Используется для хранения информации об университете.
 */
public class University {
    private String name; /** название университета. */
    private Student student = null;
    private Professor professor = null;
    private StudentRecord_book studentRecord_book = null;
    private HeadOfTheDepartment headOfTheDepartment = null;
    private Exam mathsExam = null;

    public University() {
        this.headOfTheDepartment = new HeadOfTheDepartment("AAAAAA");
        this.name = "BSUIR";
        this.professor = new Professor(Subjects.Maths,"Borisenko", this);
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public Exam getMathsExam() {
        return mathsExam;
    }

    public void setTheExam(Exam exam) {
        this.mathsExam = exam;
    }

    public HeadOfTheDepartment getHeadOfTheDepartment() {
        return headOfTheDepartment;
    }

    public Student getStudent() {
        return this.student;
    }

    /**
     * Данный метод выдаёт зачетку объекту студент,
     * сообщая классу Student ссылку на StudentRecord_book.
     * @see StudentRecord_book
     */
    public void giveStudentRB () {
        this.studentRecord_book = new StudentRecord_book();
        this.student.setStudentRB(this.studentRecord_book);
    }


    /**
     * Данный метод зачисляет студента,
     * создавая объект класса Student.
     * @see Student
     */
    public void enrollStudent (String name) {
        student = new Student(name,this);
    }
}
