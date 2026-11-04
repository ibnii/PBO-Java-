package pegawai;

public class MainPercobaan2 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        Employee e;
        e = pEmp;

        System.out.println(e.getEmployeeInfo());
        System.out.println("------------------");
        System.out.println(pEmp.getEmployeeInfo());

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        e = iEmp;
        System.out.println("------------------");
        System.out.println(e.getEmployeeInfo()); // pernyataan sama, objek berbeda
    }
}
