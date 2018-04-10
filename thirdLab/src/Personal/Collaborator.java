package Personal;

/**
 * Используется для хранения информации о сотруднике.
 */

public abstract class Collaborator {
    protected String name; /** имя сотрудника */

    public Collaborator(String name) {
        this.name = name;
    }
}
