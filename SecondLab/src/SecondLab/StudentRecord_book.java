package SecondLab;


/**
 * Используется для хранения оценок студента.
 */
public class StudentRecord_book {
    private  int number; /** номер зачетки. */
    private String name = ""; /** имя студента. */
    private Integer marks = null; /** оценки студента. */

    StudentRecord_book() {
        this.number = (int) (Math.random() * 1000000) + 100000;
    }

    public void setStudentData(String name) {
        this.name = name;
    }

    public void setMark(Integer mark) {
        this.marks = mark;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public Integer getMarks() {
        return marks;
    }
}
