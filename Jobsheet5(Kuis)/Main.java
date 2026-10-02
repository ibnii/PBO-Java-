import java.util.Scanner;

//Nama : Ibni Andarta
//NIM  : 254107020258
//Kelas: TI-2G

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Studio studio = new Studio("Studio Merah", 200000);
        Operator operator = new Operator("Hakim", 5000);

        System.out.println("===================================");
        System.out.println("Reservasi");
        System.out.println("===================================");
        System.out.print("Kode reservasi\t\t: ");
        String kodeReservasi = input.nextLine();
        System.out.print("Jumlah tiket\t\t: ");
        int jumlahTiket = input.nextInt();
        System.out.print("Durasi sewa studio (jam): ");
        int durasiSewa = input.nextInt();

        Reservasi reservasi = new Reservasi(kodeReservasi, jumlahTiket);

        System.out.println("\n===================================");
        System.out.println("Detail Reservasi");
        System.out.println("===================================");
        System.out.println("Kode reservasi : " + reservasi.getKodeReservasi());
        System.out.println("Jumlah tiket   : " + reservasi.getJumlahTiket());
        System.out.println("Nama studio    : " + reservasi.getStudio(studio).getNama());
        System.out.println("Nama operator  : " + reservasi.getOperator(operator).getNama());
        System.out.println("Total biaya    : " + reservasi.hitungBiaya(durasiSewa, operator, studio));
        System.out.println("===================================");
    }
}
