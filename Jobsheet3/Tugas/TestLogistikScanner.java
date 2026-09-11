package Tugas;

import java.util.Scanner;

public class TestLogistikScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("   SISTEM MANAJEMEN LOGISTIK KARGO KONTAINER     ");
        System.out.println("==================================================");

        System.out.print("Masukkan Nomor Resi        : ");
        String resi = sc.nextLine();

        System.out.print("Masukkan Nama Pemilik      : ");
        String pemilik = sc.nextLine();

        System.out.print("Masukkan Kapasitas Max (kg): ");
        double kapasitas = sc.nextDouble();

        Kontainer k = new Kontainer(resi, pemilik, kapasitas);

        System.out.println("\nKontainer berhasil dibuat!");
        System.out.println("Pemilik: " + k.getNamaPemilik() + " | Resi: " + k.getNomorResi());
        System.out.println("Kapasitas Maksimal: " + k.getKapasitasMaksimal() + " kg");

        int pilihan = 0;
        do {
            System.out.println("\n--- MENU OPERASIONAL KONTAINER ---");
            System.out.println("1. Tambah Muatan");
            System.out.println("2. Turunkan Muatan (Bongkar Muat)");
            System.out.println("3. Cek Status Kontainer");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi (1-4): ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan berat muatan yang ditambahkan (kg): ");
                    double tambah = sc.nextDouble();
                    k.tambahMuatan(tambah);
                    System.out.println("Berat muatan saat ini: " + k.getBeratMuatanSaatIni() + " kg");
                    break;
                case 2:
                    System.out.print("Masukkan berat muatan yang ingin diturunkan (kg): ");
                    double turun = sc.nextDouble();
                    k.turunkanMuatan(turun);
                    System.out.println("Berat muatan saat ini: " + k.getBeratMuatanSaatIni() + " kg");
                    break;
                case 3:
                    System.out.println("\n=== STATUS KONTAINER ===");
                    System.out.println("Nomor Resi        : " + k.getNomorResi());
                    System.out.println("Nama Pemilik      : " + k.getNamaPemilik());
                    System.out.println("Kapasitas Maksimal: " + k.getKapasitasMaksimal() + " kg");
                    System.out.println("Muatan Saat Ini   : " + k.getBeratMuatanSaatIni() + " kg");
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem logistik.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);

        sc.close();
    }
}
