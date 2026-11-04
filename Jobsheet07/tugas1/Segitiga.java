package tugas1;

public class Segitiga {
    private int sudut;

    public int totalSudut(int sudutA) {
        this.sudut = 180 - sudutA;
        return this.sudut;
    }

    public int totalSudut(int sudutA, int sudutB) {
        this.sudut = 180 - (sudutA + sudutB);
        return this.sudut;
    }

    public int kelling(int sisiA, int sisiB, int sisiC) {
        return sisiA + sisiB + sisiC;
    }

    public double kelling(int sisiA, int sisiB) {
        double sisiC = Math.sqrt(Math.pow(sisiA, 2) + Math.pow(sisiB, 2));
        return sisiA + sisiB + sisiC;
    }

    public int getSudut() {
        return sudut;
    }

    public static void main(String[] args) {
        Segitiga segitiga = new Segitiga();

        System.out.println("== totalSudut(int sudutA) ==");
        System.out.println("Sudut A = 60 -> sudut ketiga = " + segitiga.totalSudut(60));

        System.out.println("\n== totalSudut(int sudutA, int sudutB) ==");
        System.out.println("Sudut A = 60, Sudut B = 60 -> sudut ketiga = "
                + segitiga.totalSudut(60, 60));
        System.out.println("Sudut A = 90, Sudut B = 45 -> sudut ketiga = "
                + segitiga.totalSudut(90, 45));

        System.out.println("\n== kelling(int sisiA, int sisiB, int sisiC) ==");
        System.out.println("Sisi 3, 4, 5 -> keliling = " + segitiga.kelling(3, 4, 5));

        System.out.println("\n== kelling(int sisiA, int sisiB) ==");
        System.out.println("Sisi tegak 3 dan 4 -> keliling = " + segitiga.kelling(3, 4));
        System.out.println("Sisi tegak 6 dan 8 -> keliling = " + segitiga.kelling(6, 8));
    }
}
