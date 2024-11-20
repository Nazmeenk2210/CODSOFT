import java.io.Serializable;

public class Student implements Serializable {
    private String name, surname, grade, email, phone, stream;
    private int rollNumber, year;

    public Student(String name, String surname, int rollNumber, String grade, String email, String phone, String stream, int year) {
        this.name = name;
        this.surname = surname;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
        this.phone = phone;
        this.stream = stream;
        this.year = year;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getStream() { return stream; }
    public int getYear() { return year; }

    public void setGrade(String grade) { this.grade = grade; }
}
