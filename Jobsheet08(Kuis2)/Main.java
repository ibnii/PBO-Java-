public class Main {
    public static void main(String[] args) {
        Senjata pedang = new Senjata("Pedang Baja", 8);
        Senjata tongkat = new Senjata("Tongkat Api", 6);
        Karakter arga = new Prajurit("Arga", 100, 12);
        Karakter luna = new Penyihir("Luna", 80, 7, 30);

        arga.pasangSenjata(pedang);
        luna.pasangSenjata(tongkat);
        System.out.println(arga.info());
        System.out.println(luna.info());
        System.out.println("Damage Arga: " + arga.hitungDamage());
        Arena arena = new Arena();
        String pemenang = arena.duel((Prajurit) arga, (Penyihir) luna);
        System.out.println("Pemenang: " + pemenang);
    }
}
/* JAWABAN SOAL NO.3
    Saya akan menambahkan kelas baru yaitu kelas Pemanah extend Karakter dan saya akan mengoverride method hitungDamage,
    untuk alasan kenapa rumus damage tidak disalin dari karakter adalah karena adanya ketentuan baru 
    untuk damage sebagai pemanah yaitu damage dilipatgandakan setiap kelipatan 3.
*/