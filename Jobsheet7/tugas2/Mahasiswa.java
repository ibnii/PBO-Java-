package tugas2;

public class Mahasiswa extends Manusia {
    @Override
    public void makan() {
        System.out.println("Mahasiswa makan mie instan");
    }

    public void tidur() {
        System.out.println("Mahasiswa tidur setelah begadang tugas");
    }
}
