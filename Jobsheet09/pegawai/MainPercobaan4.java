package pegawai;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Owner ow = new Owner();

        ElectricityBill eBill = new ElectricityBill(5, "R-1");
        ow.pay(eBill); // bayar tagihan listrik
        System.out.println("------------------------------");

        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        ow.pay(pEmp); // bayar pegawai tetap
        System.out.println("------------------------------");

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ow.showMyEmployee(pEmp); // info pegawai tetap
        System.out.println("------------------------------");
        ow.showMyEmployee(iEmp); // info pegawai magang
    }
}
