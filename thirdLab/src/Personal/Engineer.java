package Personal;

/**
 * Используется для хранения информации об инженере.
 */
public class Engineer extends Collaborator {
    private String lectureHall; /** номер аудитории инженера. */

    Engineer(String name,String lectureHall) {
        super(name);
        this.lectureHall = lectureHall;
    }
}

