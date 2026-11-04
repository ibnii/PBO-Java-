package Student;

public class Student {
    private String name;
    private String studentId;
    private double gpa;

    Student(String name, String studentId, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public String describe() {
        return name + " (" + studentId + ", GPA: " + gpa + ")";
    }
}
