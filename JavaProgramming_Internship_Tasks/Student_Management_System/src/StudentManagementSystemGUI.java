import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StudentManagementSystemGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTextArea displayArea;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(800, 600); // Larger window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        loadData();

        Font defaultFont = new Font("SansSerif", Font.PLAIN, 18);
        Font boldFont = new Font("SansSerif", Font.BOLD, 20); // Bold font for labels

        // Heading Section
        JLabel headingLabel = new JLabel("Welcome To Student Management System!", JLabel.CENTER);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headingLabel.setForeground(Color.BLUE);
        add(headingLabel, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(defaultFont);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10)); // Adjusted grid size for input fields
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField rollField = new JTextField();
        JTextField gradeField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField streamField = new JTextField();
        JTextField yearField = new JTextField();

        JLabel nameLabel = new JLabel("Enter Name :");
        JLabel surnameLabel = new JLabel("Enter Surname :");
        JLabel rollLabel = new JLabel("Enter Roll Number (Eg. 20) :");
        JLabel gradeLabel = new JLabel("Enter Grade :");
        JLabel emailLabel = new JLabel("Enter Email Address :");
        JLabel phoneLabel = new JLabel("Enter Phone Number (91+XXXXXXXXXX) :");
        JLabel streamLabel = new JLabel("Enter Stream :");
        JLabel yearLabel = new JLabel("Enter Year (1/2/3) :"); 

        nameLabel.setFont(boldFont);
        surnameLabel.setFont(boldFont);
        rollLabel.setFont(boldFont);
        gradeLabel.setFont(boldFont);
        emailLabel.setFont(boldFont);
        phoneLabel.setFont(boldFont);
        streamLabel.setFont(boldFont);
        yearLabel.setFont(boldFont);
        
        // Set font size for the input fields to match the label heading
        nameField.setFont(boldFont);
        surnameField.setFont(boldFont);
        rollField.setFont(boldFont);
        gradeField.setFont(boldFont);
        emailField.setFont(boldFont);
        phoneField.setFont(boldFont);
        streamField.setFont(boldFont);
        yearField.setFont(boldFont);
        
         // Set preferred width for input fields to make them full width
        nameField.setPreferredSize(new Dimension(250, 30));
        surnameField.setPreferredSize(new Dimension(250, 30));
        rollField.setPreferredSize(new Dimension(250, 30));
        gradeField.setPreferredSize(new Dimension(250, 30));
        emailField.setPreferredSize(new Dimension(250, 30));
        phoneField.setPreferredSize(new Dimension(250, 30));
        streamField.setPreferredSize(new Dimension(250, 30));
        yearField.setPreferredSize(new Dimension(250, 30));

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(surnameLabel);
        inputPanel.add(surnameField);
        inputPanel.add(rollLabel);
        inputPanel.add(rollField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(streamLabel);
        inputPanel.add(streamField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);

        add(inputPanel, BorderLayout.WEST);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton displayButton = new JButton("Display All Students");
        JButton editButton = new JButton("Edit Student");
        JButton removeButton = new JButton("Remove Student");
        JButton saveButton = new JButton("Save & Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String rollText = rollField.getText().trim();
            String grade = gradeField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String stream = streamField.getText().trim();
            String yearText = yearField.getText().trim();

            if (name.isEmpty() || surname.isEmpty() || rollText.isEmpty() || grade.isEmpty() || email.isEmpty() || phone.isEmpty() || stream.isEmpty() || yearText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Pattern.matches("91\\+[0-9]{10}", phone)) {
                JOptionPane.showMessageDialog(this, "Phone number must match the format 91+XXXXXXXXXX", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int rollNumber = Integer.parseInt(rollText);
                int year = Integer.parseInt(yearText);
                students.add(new Student(name, surname, rollNumber, grade, email, phone, stream, year));
                JOptionPane.showMessageDialog(this, "Student detail added successfully!");
                clearFields(nameField, surnameField, rollField, gradeField, emailField, phoneField, streamField, yearField);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Roll number and Year must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        displayButton.addActionListener(e -> displayStudents());

        editButton.addActionListener(e -> {
            String rollText = JOptionPane.showInputDialog(this, "Enter Roll Number to edit:");
            if (rollText != null) {
                try {
                    int rollNumber = Integer.parseInt(rollText);
                    for (Student student : students) {
                        if (student.getRollNumber() == rollNumber) {
                            String newGrade = JOptionPane.showInputDialog(this, "Enter new Grade:");
                            if (newGrade != null) {
                                student.setGrade(newGrade);
                                JOptionPane.showMessageDialog(this, "Student detail updated successfully!");
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Student not found!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Roll Number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(e -> {
            String rollText = JOptionPane.showInputDialog(this, "Enter Roll Number to remove:");
            if (rollText != null) {
                try {
                    int rollNumber = Integer.parseInt(rollText);
                    students.removeIf(student -> student.getRollNumber() == rollNumber);
                    JOptionPane.showMessageDialog(this, "Student detail removed successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Roll Number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(e -> {
            saveData();
            System.exit(0);
        });
    }

    private void displayStudents() {
        // Display students in a JTable
        String[] columnNames = {"ROLL NO.", "NAME", "SURNAME", "GRADE", "EMAIL", "PHONE NO.", "STREAM", "YEAR"};
        String[][] data = new String[students.size()][8];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i] = new String[]{
                String.valueOf(student.getRollNumber()),
                student.getName(),
                student.getSurname(),
                student.getGrade(),
                student.getEmail(),
                student.getPhone(),
                student.getStream(),
                String.valueOf(student.getYear())
            };
        }

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if file doesn't exist or data can't be loaded
        }
    }

    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
