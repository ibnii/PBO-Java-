# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK
## Pertemuan 7: Overloading dan Overriding

---

### Identitas Mahasiswa
- **Nama** : Ibni Andarta
- **Kelas** : TI-2G
- **NIM** : 254107020258
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek (RTI253008)
- **Jurusan** : Teknologi Informasi - Politeknik Negeri Malang

---

## A. Ringkasan Materi

**Overloading** adalah penulisan beberapa method dengan **nama sama** pada satu class, dibedakan oleh **daftar parameter** (jumlah, tipe, atau urutan). Syaratnya: nama sama, daftar parameter berbeda, return type boleh sama atau berbeda. Nama variabel parameter **bukan** pembeda. Terjadi saat *compile-time*.

**Overriding** adalah penulisan ulang method superclass oleh subclass dengan **signature identik** (nama, return type, daftar parameter) untuk memberi perilaku lebih spesifik. Aturannya: mode akses overriding method harus **sama atau lebih luas**, hanya boleh di-override sekali, dan tidak boleh melempar *checked exception* yang lebih luas. Terjadi saat *runtime*.

| Aspek | Overloading | Overriding |
| :--- | :--- | :--- |
| Pembeda | Jumlah/tipe/urutan parameter | Tidak ada — signature sama persis |
| Return type | Boleh berbeda | Harus sama atau *covariant* |
| Access modifier | Bebas | Tidak boleh lebih sempit |
| Binding | Statis (*compile-time*) | Dinamis (*runtime*) |
| `@Override` | Tidak boleh | Dianjurkan |

**Dynamic Method Dispatch** adalah mekanisme Java memilih versi method saat runtime berdasarkan **tipe objek sebenarnya**, bukan tipe variabel referensinya.

**Urutan pemilihan method** pada overloading: *exact match* → *primitive widening* → *boxing/unboxing* → *var-args*.

---

## B. Percobaan 1: Hierarki `Karyawan`

### 1. Deskripsi

- `Karyawan` (superclass): `nama`, `nip`, `golongan`, `gaji`; gaji pokok ditentukan dari kode golongan `'1'`–`'5'`.
- `Staff` (subclass): menambah `lembur` dan `gajiLembur`, memiliki `getGaji()` (**overriding**) dan `getGaji(int, double)` (**overloading**).
- `Manager` (subclass): menambah `tunjangan`, `bagian`, dan `Staff[] st` (*aggregation*), meng-override `getGaji()`.
- `Utama`: membuat 2 `Manager`, masing-masing dengan 2 dan 3 `Staff`.

### 2. Kode Program

- **`Karyawan.java`**
```java
package percobaan1;

public class Karyawan {
    private String nama;
    private String nip;
    private String golongan;
    private double gaji;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
        switch (golongan.charAt(0)) {
            case '1':
                this.gaji = 5000000;
                break;
            case '2':
                this.gaji = 3000000;
                break;
            case '3':
                this.gaji = 2000000;
                break;
            case '4':
                this.gaji = 1000000;
                break;
            case '5':
                this.gaji = 750000;
                break;
        }
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }

    public String getGolongan() {
        return golongan;
    }

    public double getGaji() {
        return gaji;
    }
}
```

- **`Staff.java`**
```java
package percobaan1;

public class Staff extends Karyawan {
    private int lembur;
    private double gajiLembur;

    public void setLembur(int lembur) {
        this.lembur = lembur;
    }

    public int getLembur() {
        return lembur;
    }

    public void setGajiLembur(double gajiLembur) {
        this.gajiLembur = gajiLembur;
    }

    public double getGajiLembur() {
        return gajiLembur;
    }

    public double getGaji(int lembur, double gajiLembur) {
        return super.getGaji() + lembur * gajiLembur;
    }

    @Override
    public double getGaji() {
        return super.getGaji() + getLembur() * getGajiLembur();
    }

    public void lihatInfo() {
        System.out.println("NIP         : " + this.getNip());
        System.out.println("Nama        : " + this.getNama());
        System.out.println("Golongan    : " + this.getGolongan());
        System.out.println("Jml Lembur  : " + this.getLembur());
        System.out.printf("Gaji Lembur : %.0f\n", this.getGajiLembur());
        System.out.printf("Gaji        : %.0f\n", this.getGaji());
    }
}
```

- **`Manager.java`**
```java
package percobaan1;

public class Manager extends Karyawan {
    private double tunjangan;
    private String bagian;
    private Staff[] st;

    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }

    public double getTunjangan() {
        return tunjangan;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getBagian() {
        return bagian;
    }

    public void setStaff(Staff[] st) {
        this.st = st;
    }

    public void viewStaff() {
        System.out.println("----------------------------");
        if (st != null) {
            for (int i = 0; i < st.length; i++) {
                if (st[i] != null) {
                    st[i].lihatInfo();
                }
            }
        }
        System.out.println("----------------------------");
    }

    public void lihatInfo() {
        System.out.println("Manager     : " + this.getBagian());
        System.out.println("NIP         : " + this.getNip());
        System.out.println("Nama        : " + this.getNama());
        System.out.println("Golongan    : " + this.getGolongan());
        System.out.printf("Tunjangan   : %.0f\n", this.getTunjangan());
        System.out.printf("Gaji        : %.0f\n", this.getGaji());
        System.out.println("Bagian      : " + this.getBagian());
        this.viewStaff();
    }

    @Override
    public double getGaji() {
        return super.getGaji() + tunjangan;
    }
}
```

- **`Utama.java`**
```java
package percobaan1;

public class Utama {
    public static void main(String[] args) {
        System.out.println("Program Testing Class Manager & Staff");
        Manager[] man = new Manager[2];
        Staff[] staff1 = new Staff[2];
        Staff[] staff2 = new Staff[3];

        man[0] = new Manager();
        man[0].setNama("Tedjo");
        man[0].setNip("101");
        man[0].setGolongan("1");
        man[0].setTunjangan(5000000);
        man[0].setBagian("Administrasi");

        man[1] = new Manager();
        man[1].setNama("Atika");
        man[1].setNip("102");
        man[1].setGolongan("1");
        man[1].setTunjangan(2500000);
        man[1].setBagian("Pemasaran");

        staff1[0] = new Staff();
        staff1[0].setNama("Usman");
        staff1[0].setNip("0003");
        staff1[0].setGolongan("2");
        staff1[0].setLembur(10);
        staff1[0].setGajiLembur(10000);

        staff1[1] = new Staff();
        staff1[1].setNama("Anugrah");
        staff1[1].setNip("0005");
        staff1[1].setGolongan("2");
        staff1[1].setLembur(10);
        staff1[1].setGajiLembur(55000);
        man[0].setStaff(staff1);

        staff2[0] = new Staff();
        staff2[0].setNama("Hendra");
        staff2[0].setNip("0004");
        staff2[0].setGolongan("3");
        staff2[0].setLembur(15);
        staff2[0].setGajiLembur(5500);

        staff2[1] = new Staff();
        staff2[1].setNama("Arie");
        staff2[1].setNip("0006");
        staff2[1].setGolongan("4");
        staff2[1].setLembur(5);
        staff2[1].setGajiLembur(100000);

        staff2[2] = new Staff();
        staff2[2].setNama("Mentari");
        staff2[2].setNip("0007");
        staff2[2].setGolongan("3");
        staff2[2].setLembur(6);
        staff2[2].setGajiLembur(20000);
        man[1].setStaff(staff2);

        man[0].lihatInfo();
        man[1].lihatInfo();
    }
}
```

### 3. Eksekusi

```bash
cd "Jobsheet7"
javac -d /tmp/build percobaan1/*.java
java -cp /tmp/build percobaan1.Utama
```

**Output:**
```text
Program Testing Class Manager & Staff
Manager     : Administrasi
NIP         : 101
Nama        : Tedjo
Golongan    : 1
Tunjangan   : 5000000
Gaji        : 10000000
Bagian      : Administrasi
----------------------------
NIP         : 0003
Nama        : Usman
Golongan    : 2
Jml Lembur  : 10
Gaji Lembur : 10000
Gaji        : 3100000
NIP         : 0005
Nama        : Anugrah
Golongan    : 2
Jml Lembur  : 10
Gaji Lembur : 55000
Gaji        : 3550000
----------------------------
Manager     : Pemasaran
NIP         : 102
Nama        : Atika
Golongan    : 1
Tunjangan   : 2500000
Gaji        : 7500000
Bagian      : Pemasaran
----------------------------
NIP         : 0004
Nama        : Hendra
Golongan    : 3
Jml Lembur  : 15
Gaji Lembur : 5500
Gaji        : 2082500
NIP         : 0006
Nama        : Arie
Golongan    : 4
Jml Lembur  : 5
Gaji Lembur : 100000
Gaji        : 1500000
NIP         : 0007
Nama        : Mentari
Golongan    : 3
Jml Lembur  : 6
Gaji Lembur : 20000
Gaji        : 2120000
----------------------------
```

### 4. Analisis Perhitungan Gaji

| Objek | Golongan | Gaji Pokok | Tambahan | Total |
| :--- | :---: | ---: | ---: | ---: |
| Tedjo (Manager) | 1 | 5.000.000 | Tunjangan 5.000.000 | 10.000.000 |
| Atika (Manager) | 1 | 5.000.000 | Tunjangan 2.500.000 | 7.500.000 |
| Usman (Staff) | 2 | 3.000.000 | 10 × 10.000 | 3.100.000 |
| Anugrah (Staff) | 2 | 3.000.000 | 10 × 55.000 | 3.550.000 |
| Hendra (Staff) | 3 | 2.000.000 | 15 × 5.500 | 2.082.500 |
| Arie (Staff) | 4 | 1.000.000 | 5 × 100.000 | 1.500.000 |
| Mentari (Staff) | 3 | 2.000.000 | 6 × 20.000 | 2.120.000 |

### 5. Jawaban Pertanyaan

1. **Di mana overloading dan overriding pada kode ini?**  
   **Jawab:**
   - **Overloading**: `Staff.getGaji(int lembur, double gajiLembur)` — nama sama dengan `Karyawan.getGaji()`, daftar parameter berbeda.
   - **Overriding**: `Staff.getGaji()` dan `Manager.getGaji()` — keduanya menimpa `Karyawan.getGaji()` dengan signature identik (`public double getGaji()`).

2. **Apa fungsi `super.getGaji()`?**  
   **Jawab:** Memanggil implementasi `getGaji()` milik superclass `Karyawan` untuk mengambil gaji pokok, lalu ditambah komponen tambahan milik subclass (`lembur × gajiLembur` untuk Staff, `tunjangan` untuk Manager).

3. **Mengapa `getGaji()` pada `Staff` dan `Manager` menghasilkan nilai berbeda?**  
   **Jawab:** Karena keduanya meng-override method yang sama dengan rumus berbeda. Saat `lihatInfo()` dijalankan, Java memilih versi `getGaji()` sesuai tipe objek konkretnya (*dynamic method dispatch*).

4. **Mengapa `Staff` boleh punya `getGaji()` dan `getGaji(int, double)` sekaligus?**  
   **Jawab:** Karena daftar parameternya berbeda, sehingga sah sebagai overloading. Dua `getGaji()` tanpa parameter tidak diperbolehkan karena signature-nya identik → `method getGaji() is already defined`.

5. **Apa fungsi `@Override`?**  
   **Jawab:** Memerintahkan compiler memverifikasi bahwa method benar-benar menimpa method superclass. Tanpa anotasi ini, salah ketik nama method (mis. `getgaji()`) tetap lolos kompilasi tetapi tidak menimpa apa pun sehingga menjadi bug tersembunyi. Dengan anotasi, langsung menjadi *compile error*.

6. **Apa hubungan antara `Manager` dan `Staff`?**  
   **Jawab:** **Aggregation (*has-a*)**, bukan pewarisan. Keduanya sama-sama subclass langsung `Karyawan` (saudara). `Manager` *has-a* `Staff[] st` yang ditampilkan lewat `viewStaff()`.

---

## C. Latihan 1: Overloading Beda **Jumlah** Parameter

### Kode
```java
package latihan1;

public class PerkalianKu {
    void perkalian(int a, int b) {
        System.out.println(a * b);
    }

    void perkalian(int a, int b, int c) {
        System.out.println(a * b * c);
    }

    public static void main(String args[]) {
        PerkalianKu objek = new PerkalianKu();

        objek.perkalian(25, 43);
        objek.perkalian(34, 23, 56);
    }
}
```

### Output
```text
1075
43792
```

### Jawaban Pertanyaan

**4.1 Di manakah overloading?**  
**Jawab:** Pada method `perkalian()` di class `PerkalianKu`, yaitu `perkalian(int a, int b)` dan `perkalian(int a, int b, int c)`. Nama sama, jumlah parameter berbeda.

**4.2 Ada berapa jumlah parameter yang berbeda?**  
**Jawab:** **2**, yaitu versi **2 parameter** (25 × 43 = 1075) dan versi **3 parameter** (34 × 23 × 56 = 43792).

---

## D. Latihan 2: Overloading Beda **Tipe** Parameter

### Kode
```java
package latihan2;

public class PerkalianKu {
    void perkalian(int a, int b) {
        System.out.println(a * b);
    }

    void perkalian(double a, double b) {
        System.out.println(a * b);
    }

    public static void main(String args[]) {
        PerkalianKu objek = new PerkalianKu();

        objek.perkalian(25, 43);
        objek.perkalian(34.56, 23.7);
    }
}
```

### Output
```text
1075
819.072
```

### Jawaban Pertanyaan

**4.3 Di manakah overloading?**  
**Jawab:** Pada method `perkalian()` di class `PerkalianKu`, yaitu `perkalian(int a, int b)` dan `perkalian(double a, double b)`. Nama dan jumlah parameter sama, tetapi tipe parameternya berbeda.

**4.4 Ada berapa tipe parameter yang berbeda?**  
**Jawab:** **2**, yaitu tipe **`int`** (25 × 43 = 1075) dan tipe **`double`** (34.56 × 23.7 = 819.072).

> **Catatan:** literal desimal di Java *default*-nya bertipe `double`, sehingga `perkalian(34.56, 23.7)` sah dan memilih versi `double`. Bila method `double` dihapus, baris itu gagal dikompilasi dengan error `incompatible types: possible lossy conversion from double to int`.

---

## E. Latihan 3: Overriding dan Dynamic Method Dispatch

### Kode
```java
package latihan3;

public class Ikan {
    public void swim() {
        System.out.println("Ikan bisa berenang");
    }
}
```
```java
package latihan3;

public class Piranha extends Ikan {
    @Override
    public void swim() {
        System.out.println("Piranha bisa makan daging");
    }
}
```
```java
package latihan3;

public class Fish {
    public static void main(String[] args) {
        Ikan a = new Ikan();
        Ikan b = new Piranha();

        a.swim();
        b.swim();
    }
}
```

> Kode asli jobsheet menaruh tiga class dalam satu file. Di sini dipecah menjadi tiga file agar sesuai konvensi satu class publik per file.

### Output
```text
Ikan bisa berenang
Piranha bisa makan daging
```

### Jawaban Pertanyaan

**4.5 Di manakah overriding?**  
**Jawab:** Pada method `swim()` di class `Piranha`. Method tersebut menimpa `Ikan.swim()` karena nama, return type (`void`), dan daftar parameter (kosong) identik, serta modifier aksesnya sama (`public`).

**4.6 Jabarkan mekanisme overriding pada kode tersebut.**  
**Jawab:**
1. `Piranha extends Ikan` → `Piranha` mewarisi `swim()` dari `Ikan`.
2. `Ikan.swim()` adalah **overridden method**; `Piranha.swim()` adalah **overriding method**.
3. `Ikan b = new Piranha();` adalah **upcasting** — referensi superclass menunjuk objek subclass, sah karena `Piranha` *is-a* `Ikan`.
4. `a.swim()` → objek `Ikan` → mencetak `"Ikan bisa berenang"`.
5. `b.swim()` → objek `Piranha` → mencetak `"Piranha bisa makan daging"`. Versi method dipilih saat **runtime** berdasarkan objeknya, bukan tipe variabelnya (*dynamic method dispatch*).
6. `@Override` memastikan compiler memvalidasi overriding. Jika nama method salah ketik, kompilasi gagal: `method does not override or implement a method from a supertype`.
7. Modifier akses overriding tidak boleh lebih sempit. Mengubah `swim()` menjadi `protected` menghasilkan error: `attempting to assign weaker access privileges; was public`.

---

## F. Tugas 1: Overloading pada Class `Segitiga`

### Kode
```java
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
```

### Output
```text
== totalSudut(int sudutA) ==
Sudut A = 60 -> sudut ketiga = 120

== totalSudut(int sudutA, int sudutB) ==
Sudut A = 60, Sudut B = 60 -> sudut ketiga = 60
Sudut A = 90, Sudut B = 45 -> sudut ketiga = 45

== kelling(int sisiA, int sisiB, int sisiC) ==
Sisi 3, 4, 5 -> keliling = 12

== kelling(int sisiA, int sisiB) ==
Sisi tegak 3 dan 4 -> keliling = 12.0
Sisi tegak 6 dan 8 -> keliling = 24.0
```

### Analisis

| Pasangan Method | Nama | Parameter | Return Type | Status |
| :--- | :---: | :--- | :--- | :---: |
| `totalSudut(int)` vs `totalSudut(int, int)` | ✓ | Beda jumlah | Sama (`int`) | Overload sah |
| `kelling(int,int,int)` vs `kelling(int,int)` | ✓ | Beda jumlah | Beda (`int` vs `double`) | Overload sah |

Perhitungan Phytagoras:
- `kelling(3, 4)` → c = √(9 + 16) = 5 → 3 + 4 + 5 = **12.0**
- `kelling(6, 8)` → c = √(36 + 64) = 10 → 6 + 8 + 10 = **24.0**

**Catatan batasan overloading:** return type **bukan** penentu overloading. Menulis dua method `kelling(int, int, int)` dengan return type `int` dan `double` akan gagal: `method kelling(int,int,int) is already defined`. Return type boleh berbeda hanya jika daftar parameternya sudah berbeda, seperti pada pasangan di atas.

---

## G. Tugas 2: Overriding dengan Dynamic Method Dispatch

### Kode
```java
package tugas2;

public class Manusia {
    public void bernafas() {
        System.out.println("Manusia bernafas dengan paru-paru");
    }

    public void makan() {
        System.out.println("Manusia makan nasi");
    }
}
```
```java
package tugas2;

public class Dosen extends Manusia {
    @Override
    public void makan() {
        System.out.println("Dosen makan siang di kantin kampus");
    }

    public void lembur() {
        System.out.println("Dosen lembur mengoreksi tugas mahasiswa");
    }
}
```
```java
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
```
```java
package tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        Manusia manusia = new Manusia();
        Manusia dosen = new Dosen();
        Manusia mahasiswa = new Mahasiswa();

        System.out.println("== manusia (Manusia) ==");
        manusia.bernafas();
        manusia.makan();

        System.out.println("\n== dosen (referensi Manusia -> objek Dosen) ==");
        dosen.bernafas();
        dosen.makan();

        System.out.println("\n== mahasiswa (referensi Manusia -> objek Mahasiswa) ==");
        mahasiswa.bernafas();
        mahasiswa.makan();

        System.out.println("\n== method spesifik subclass (downcasting) ==");
        ((Dosen) dosen).lembur();
        ((Mahasiswa) mahasiswa).tidur();

        System.out.println("\n== method yang TIDAK di-override ==");
        dosen.bernafas();
        mahasiswa.bernafas();
    }
}
```

### Output
```text
== manusia (Manusia) ==
Manusia bernafas dengan paru-paru
Manusia makan nasi

== dosen (referensi Manusia -> objek Dosen) ==
Manusia bernafas dengan paru-paru
Dosen makan siang di kantin kampus

== mahasiswa (referensi Manusia -> objek Mahasiswa) ==
Manusia bernafas dengan paru-paru
Mahasiswa makan mie instan

== method spesifik subclass (downcasting) ==
Dosen lembur mengoreksi tugas mahasiswa
Mahasiswa tidur setelah begadang tugas

== method yang TIDAK di-override ==
Manusia bernafas dengan paru-paru
Manusia bernafas dengan paru-paru
```

### Analisis

- **Overridden method**: `Manusia.makan()`.
- **Overriding method**: `Dosen.makan()` dan `Mahasiswa.makan()`.
- `bernafas()` tidak di-override sehingga selalu memakai implementasi `Manusia`.
- `dosen.makan()` tetap mencetak versi `Dosen` meskipun variabelnya bertipe `Manusia` → *dynamic method dispatch*.
- `lembur()` dan `tidur()` bukan member `Manusia`, sehingga butuh **downcasting** `((Dosen) dosen)` untuk dipanggil.
- Bila `Dosen` tidak meng-override `makan()`, maka `dosen.makan()` mewarisi versi `Manusia` dan mencetak `"Manusia makan nasi"`.
- Bila signature diubah menjadi `makan(String menu)` dengan `@Override`, kompilasi gagal: `method does not override or implement a method from a supertype` — karena itu overloading, bukan overriding.

---

## H. Tugas 3: Jawab Singkat

1. **Perbedaan overloading dan overriding beserta contohnya.**  
   **Jawab:**
   - **Overloading**: satu nama method untuk beberapa fungsi, dibedakan daftar parameter, terjadi saat *compile-time*. Contoh: `Segitiga.totalSudut(int)` dan `totalSudut(int, int)`.
   - **Overriding**: subclass menulis ulang method superclass dengan signature identik, terjadi saat *runtime*. Contoh: `Piranha.swim()` menimpa `Ikan.swim()`.

2. **Mengapa overloading disebut *compile-time polymorphism* dan overriding *runtime polymorphism*?**  
   **Jawab:** Pada overloading, compiler sudah bisa menentukan method yang dipanggil hanya dari tipe argumen di kode sumber, sehingga binding-nya statis. Pada overriding, compiler hanya tahu method itu ada; versi mana yang berjalan baru ditentukan saat runtime berdasarkan objek konkret di heap.

3. **Risiko bila `@Override` tidak ditulis padahal bermaksud overriding.**  
   **Jawab:** Salah ketik nama method atau keliru daftar parameter akan lolos kompilasi tanpa peringatan, sehingga method dianggap baru dan implementasi superclass tetap yang dijalankan. Akibatnya komponen perhitungan tambahan tidak pernah diterapkan — bug logika yang sulit dilacak.

4. **Penilaian terhadap desain `Staff.getGaji()` dan `getGaji(int, double)` yang berdampingan.**  
   **Jawab:** Sah secara teknis tetapi kurang baik secara desain. Parameter `lembur`/`gajiLembur` menduplikasi atribut instans yang sudah dimiliki objek, sehingga muncul dua sumber kebenaran. Lebih baik cukup pertahankan overriding `getGaji()` dan beri nama berbeda untuk perhitungan variatif, misalnya `hitungGajiDenganLembur(...)`.

---

## I. Kesimpulan

1. **Overloading** memerlukan nama sama dan daftar parameter berbeda. Nama variabel parameter dan return type bukan pembeda yang sah.
2. **Overriding** memerlukan signature identik dan mode akses yang tidak boleh lebih sempit dari method induk.
3. **`@Override`** mengubah potensi bug tersembunyi menjadi *compile error* yang terdeteksi dini.
4. **Dynamic Method Dispatch** menentukan method yang dieksekusi berdasarkan tipe objek konkret, bukan tipe variabel referensi — inti polimorfisme runtime.
5. Overloading dan overriding dapat berdampingan dalam satu class turunan, namun sebaiknya dihindari dengan penamaan method yang lebih deskriptif.
