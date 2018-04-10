package Personal;

import UniversityRealization.Exam;
import UniversityRealization.Subjects;

public class HeadOfTheDepartment extends Collaborator {

    public HeadOfTheDepartment(String name) {
        super(name);
    }

    public Exam appointTheExam(Subjects subject) {
        return (new Exam(subject));
    }
}
