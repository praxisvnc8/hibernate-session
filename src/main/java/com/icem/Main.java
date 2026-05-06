import com.icem.Student;
import com.icem.StudentDAO;
import jakarta.transaction.SystemException;
import java.util.Scanner;

public static void main(String[] args) throws SystemException {

    Scanner sc = new Scanner(System.in);

    System.out.println("---WELCOME TO THE STUDENT MANAGEMENT SYSTEM---");
    System.out.println("Enter the firstname: ");
    String fname = sc.nextLine();
    System.out.println("Enter the lastname: ");
    String lname = sc.nextLine();
    System.out.println("Enter the email: ");
    String email = sc.nextLine();
    Student s1 = new Student(fname, lname, email);
    StudentDAO dao = new StudentDAO();
    dao.saveStudent(s1);

}