package tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        TelevisiModern tv = new TelevisiModern("Samsung", 100);
        System.out.println("Channel aktif: " + tv.getChannelAktif());
        tv.pindahChannel(20);
        System.out.println("Channel aktif sekarang: "
                + tv.getChannelAktif());
        tv.gantiModusTampilan("HDMI");
        tv.mainkanDVD();
        tv.masukkanDVD("The Matrix");
        tv.mainkanDVD();

        // Uji tambahan: validasi batasan channel
        tv.pindahChannel(150);
        System.out.println("Channel aktif setelah pindah ke 150: " + tv.getChannelAktif());
    }
}
