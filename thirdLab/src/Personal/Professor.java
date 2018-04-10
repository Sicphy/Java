package Personal;


import UniversityRealization.Subjects;
import UniversityRealization.StudentRecord_book;
import UniversityRealization.University;

/**
 * Используется для хранения информации о преподователе
 */
public class Professor extends Collaborator{
    private Subjects subject; /** предмет преподователя. */
    private University university = null;


    public Professor(Subjects subject, String name, University university) {
        super(name);
        this.subject = subject;
        this.university = university;
    }


    /**
     * Данный метод используется для принятия экзамена у студента,
     * может изменить оценку в зачетке студента.
     * @param studentRecord_book
     */
    public void takeTheExam(StudentRecord_book studentRecord_book) {
        studentRecord_book.setMark((int) (Math.random() * 10) + 1);
        if(studentRecord_book.getMarks() >= 4) {
            university.getMathsExam().status = "сдан";
        }
        else {
            university.getMathsExam().status = "не сдан";
        }
    }
}

