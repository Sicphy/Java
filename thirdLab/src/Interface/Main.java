package Interface;

import UniversityRealization.University;
import static Interface.SwingConsole.run;


/**
 *Базовый класс с запуском программы.
 */
public class Main {
    public static void main(String[] args) {
        University university = new University();
        run(new Gui(university), 400, 200);
    }
}

