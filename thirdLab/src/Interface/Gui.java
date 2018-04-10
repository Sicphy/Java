package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import UniversityRealization.*;

/**
 * Класс для работы с графическим интерфейсом.
 */
public class Gui extends JFrame{
    private JTextField field = new JTextField("Введите имя студента...");
    private JTextArea studentRecord_bookArea = new JTextArea("Номер зачетки:\nИмя студента:\nМатематика:\nСтатус:");
    private JButton addStudent = new JButton("Add Student"),
            giveSRB = new JButton("give record-book"),
            enterData = new JButton("enter student's data"),
            passTheExam = new JButton("pass the exam"),
            assingTheExam = new JButton("assing the exam");
    private University university;
    private boolean fl = true;

    public Gui(University university) {
        this.university = university;
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        addStudent.addActionListener(new onAddStudentButtonClick());
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        addStudent.setPreferredSize(new Dimension(150,23));
        gbl.setConstraints(addStudent, c);
        add(addStudent);

        giveSRB.addActionListener(new onGiveSRBClick());
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        giveSRB.setPreferredSize(new Dimension(150,23));
        gbl.setConstraints(giveSRB, c);
        add(giveSRB);

        c.gridx = 0;
        c.gridy = 2;
        enterData.addActionListener(new onEnterDataClick());
        enterData.setPreferredSize(new Dimension(150,23));
        gbl.setConstraints(enterData, c);
        add(enterData);


        assingTheExam.addActionListener(new onAssingTheExamButtonClick());
        c.gridx = 0;
        c.gridy = 3;
        assingTheExam.setPreferredSize(new Dimension(150,23));
        gbl.setConstraints(assingTheExam, c);
        add(assingTheExam);

        c.gridx = 0;
        c.gridy = 4;
        passTheExam.addActionListener(new onPassTheExamClick());
        passTheExam.setPreferredSize(new Dimension(150,23));
        gbl.setConstraints(passTheExam, c);
        add(passTheExam);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,10,10,0);
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(200,23));
        gbl.setConstraints(field, c);
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (fl) {
                    if (e.getKeyChar() == '\b') {
                        field.setText("");
                        fl = false;
                    }
                }
            }
        });
        add(field);

        studentRecord_bookArea.setBackground(Color.WHITE);
        studentRecord_bookArea.setEditable(false);
        studentRecord_bookArea.setPreferredSize(new Dimension(200,92));
        c.insets = new Insets(0,10,0,0);
        c.gridheight = 4;
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(studentRecord_bookArea, c);
        add(studentRecord_bookArea);
    }

    /**
     * Действие на кнопку addStudent: добавляет нового студента.
     */
    class onAddStudentButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            university.enrollStudent(field.getText());
            studentRecord_bookArea.setText("Номер зачетки:\nИмя студента:\nМатематика:\nСтатус:");
        }
    }

    /**
     * Действие на кнопку assingTheExam: создаёт экзамен.
     */
    class onAssingTheExamButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if((university.getStudent() != null) && (university.getStudent().getStudentRecord_book() != null) &&
                    (university.getStudent().getStudentRecord_book().getName() != ""))
            university.setTheExam(university.getHeadOfTheDepartment().appointTheExam(Subjects.Maths));
        }
    }

    /**
     * Действие на кнопку giveSRB: выдаёт зачетку студенту.
     */
    class onGiveSRBClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((university.getStudent() != null) && (university.getStudent().getStudentRecord_book() == null)) {
                university.giveStudentRB();
                studentRecord_bookArea.setText("Номер зачетки: " +
                        university.getStudent().getStudentRecord_book().getNumber() +
                        "\nИмя студента:\nМатематика:\nСтвтус:");
            }
        }
    }


    /**
     * Действие на кнопку enterData: вводит личную информацию студента.
     */
    class onEnterDataClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((university.getStudent() != null) && (university.getStudent().getStudentRecord_book() != null) &&
                    (university.getStudent().getStudentRecord_book().getName() == "")) {
                university.getStudent().enterData();
                studentRecord_bookArea.setText("Номер зачетки: " +
                        university.getStudent().getStudentRecord_book().getNumber() + "\nИмя студента: " +
                        university.getStudent().getStudentRecord_book().getName() + "\nМатематика:\nСтатус:");
            }
        }
    }

    /**
     * Действие на кнопку passTheExam: сдать экзамен.
     */
    class onPassTheExamClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((university.getStudent() != null) && (university.getStudent().getStudentRecord_book() != null) &&
                    (university.getStudent().getStudentRecord_book().getMarks() == null) &&
                    (university.getStudent().getStudentRecord_book().getName() != "")) {
                university.getStudent().passTheExam();
                studentRecord_bookArea.setText("Номер зачетки: " +
                        university.getStudent().getStudentRecord_book().getNumber() + "\nИмя студента: " +
                        university.getStudent().getStudentRecord_book().getName() + "\nМатематика: " +
                        university.getStudent().getStudentRecord_book().getMarks() + "\nСтатус: " +
                        university.getMathsExam().status);
            }
        }
    }
}

class SwingConsole {
    public static void
    run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }
}


