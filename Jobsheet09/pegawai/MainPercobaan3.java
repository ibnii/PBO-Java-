package pegawai;

public class MainPercobaan3 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "R-1");

        Employee[] e = { pEmp, iEmp };
        Payable[] p = { pEmp, eBill };
        // Employee[] e2 = { pEmp, iEmp, eBill }; // tidak valid

        for (Employee emp : e) {
            System.out.println(emp.getEmployeeInfo());
        }

        int total = 0;
        for (Payable tagihan : p) {
            total += tagihan.getPaymentAmount();
        }
        System.out.println("Total tagihan bulan ini = " + total);
    }
}
