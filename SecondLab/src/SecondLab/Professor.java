package SecondLab;

/**
 * Используется для хранения информации о преподователе
 */

public class Professor extends Collaborator{
    private Subjects subject; /** предмет преподователя. */


    Professor(Subjects subject) {
        this.subject = subject;
    }


    /**
     * Данный метод используется для принятия экзамена у студента,
     * может изменить оценку в зачетке студента.
     * @param studentRecord_book
     */
    public void takeTheExam(StudentRecord_book studentRecord_book) {
        studentRecord_book.setMark((int) (Math.random() * 10) + 1);
    }
}
