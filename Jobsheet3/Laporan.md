# LAPORAN PRAKTIKUM PEMROGRAMAN BERORIENTASI OBJEK
## JOBSHEET 3: ENKAPSULASI PADA PEMROGRAMAN BERORIENTASI OBJEK

---

### IDENTITAS MAHASISWA
* **Nama**  : Ibni Andarta
* **NIM**   : 254107020258
* **Kelas** : TI-2G
* **Mata Kuliah** : Praktikum Pemrograman Berorientasi Objek
* **Program Studi**: D-IV Teknik Informatika
* **Jurusan**     : Teknologi Informasi - Politeknik Negeri Malang

---

## 1. RINGKASAN TEORI & KOMPETENSI DASAR

Pada praktikum Jobsheet 3 ini, kompetensi yang dipelajari dan dicapai meliputi:
1. **Enkapsulasi (*Data Hiding / Information Hiding*)**: Menyembunyikan kompleksitas data serta implementasi internal suatu objek dari dunia luar, serta membatasi akses langsung ke variabel/state agar perubahan data hanya bisa dilakukan melalui antarmuka (*interface*) resmi yang terkontrol dan tervalidasi.
2. **Access Modifier**: Memahami ruang lingkup akses (*scope*) dari empat modifier di Java:
   * `private` (`-`): Hanya dapat diakses di dalam kelas yang sama.
   * `default` (`~`): Dapat diakses oleh kelas-kelas di dalam satu *package* yang sama.
   * `protected` (`#`): Dapat diakses di dalam *package* yang sama dan oleh kelas turunan (*subclass*) di luar *package*.
   * `public` (`+`): Dapat diakses dari mana saja tanpa batasan *package*.
3. **Konstruktor (*Constructor*)**: Method khusus yang dieksekusi secara otomatis saat objek diinstansiasi dengan kata kunci `new`, berfungsi untuk mengalokasikan memori serta menginisialisasi atribut/state awal objek.
4. **Getter dan Setter**: 
   * *Getter* (Accessor): Method `public` dengan *return value* untuk membaca data atribut `private`.
   * *Setter* (Mutator): Method `public` bertipe `void` dengan parameter untuk mengubah/memvalidasi data atribut `private`.
5. **Notasi UML Class Diagram**: Notasi visual standar untuk merepresentasikan kelas, atribut, method, serta tingkat hak aksesnya.

---

## 2. PERCOBAAN 1: ENKAPSULASI

### 2.1 Deskripsi & Kode Program Awal
Pada percobaan pertama, dibuat kelas `Motor` dengan atribut `kecepatan` dan `kontakOn` yang masih menggunakan modifier `public`.

**Kode Program `Motor.java` (Versi Percobaan 1):**
```java
package Motor;

public class Motor {
    public int kecepatan = 0;
    public boolean kontakOn = false;

    public void printStatus() {
        if (kontakOn == true) {
            System.out.println("Kontak On");
        } else {
            System.out.println("Kontak Off");
        }
        System.out.println("Kecepatan " + kecepatan + "\n");
    }
}
```

**Kode Program `MotorDemo.java` (Versi Percobaan 1):**
```java
package Motor;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();
        motor.printStatus();
        motor.kecepatan = 50;
        motor.printStatus();
    }
}
```

### 2.2 Output Percobaan 1
```text
Kontak Off
Kecepatan 0

Kontak Off
Kecepatan 50
```

### 2.3 Analisis Kejanggalan Percobaan 1
Dari hasil eksekusi program di atas, terdapat kejanggalan yang sangat nyata dari sudut pandang logika dunia nyata (*real-world logic*):
* Nilai kecepatan motor tiba-tiba berubah dari `0` menjadi `50` km/jam.
* Kecepatan melonjak menjadi `50` padahal posisi kunci kontak motor masih dalam kondisi **OFF** (`kontakOn = false`).
* Hal ini terjadi karena atribut `kecepatan` dideklarasikan bertipe `public`, sehingga kelas eksternal (`MotorDemo`) dapat langsung mengakses dan mengubah nilai variabel internal tanpa melalui mekanisme kontrol atau aturan validasi mesin motor. Keadaan ini melanggar prinsip *data integrity* pada PBO.

---

## 3. PERCOBAAN 2: ACCESS MODIFIER

### 3.1 Deskripsi & Perbaikan Program
Untuk memperbaiki kejanggalan pada Percobaan 1, atribut `kecepatan` dan `kontakOn` diubah menjadi `private`, serta disediakan method pengontrol: `nyalakanMesin()`, `matikanMesin()`, `tambahKecepatan()`, dan `kurangiKecepatan()`.

**Kode Program `Motor.java` (Versi Percobaan 2):**
```java
package Motor;

public class Motor {
    private int kecepatan = 0;
    private boolean kontakOn = false;

    public void nyalakanMesin() {
        kontakOn = true;
    }

    public void matikanMesin() {
        kontakOn = false;
        kecepatan = 0;
    }

    public void tambahKecepatan() {
        if (kontakOn == true) {
            kecepatan += 5;
        } else {
            System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off! \n");
        }
    }

    public void kurangiKecepatan() {
        if (kontakOn == true) {
            kecepatan -= 5;
        } else {
            System.out.println("Kecepatan tidak bisa berkurang karena Mesin Off! \n");
        }
    }

    public void printStatus() {
        if (kontakOn == true) {
            System.out.println("Kontak On");
        } else {
            System.out.println("Kontak Off");
        }
        System.out.println("Kecepatan " + kecepatan + "\n");
    }
}
```

**Kode Program `MotorDemo.java` (Versi Percobaan 2):**
```java
package Motor;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();
        motor.printStatus();
        motor.tambahKecepatan();

        motor.nyalakanMesin();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.matikanMesin();
        motor.printStatus();
    }
}
```

### 3.2 Output Percobaan 2
```text
Kontak Off
Kecepatan 0

Kecepatan tidak bisa bertambah karena Mesin Off! 

Kontak On
Kecepatan 0

Kontak On
Kecepatan 5

Kontak On
Kecepatan 10

Kontak On
Kecepatan 15

Kontak Off
Kecepatan 0
```

---

## 4. PERTANYAAN 3.3 (PERCOBAAN 1 & 2) BESERTA JAWABAN

### Pertanyaan 1:
> Pada class TestMobil (merujuk pada class `MotorDemo`), saat kita menambah kecepatan untuk pertama kalinya, mengapa muncul peringatan *"Kecepatan tidak bisa bertambah karena Mesin Off!"*?

**Jawaban:**
Peringatan tersebut muncul karena pada saat objek `Motor` pertama kali diinstansiasi (`new Motor()`), atribut `kontakOn` secara *default* bernilai `false`. Ketika method `tambahKecepatan()` dipanggil pertama kali sebelum pemanggilan `nyalakanMesin()`, kondisi pada blok percabangan:
```java
if (kontakOn == true) {
    kecepatan += 5;
} else {
    System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off! \n");
}
```
menghasilkan nilai `false`. Akibatnya, alur program melompat ke blok `else` dan mencetak peringatan bahwa mesin masih dalam posisi *off*.

---

### Pertanyaan 2:
> Mengapa atribut kecepatan dan kontakOn diset `private`?

**Jawaban:**
Atribut `kecepatan` dan `kontakOn` diset `private` untuk menerapkan prinsip **Enkapsulasi (*Data Hiding*)**. Tujuannya adalah:
1. **Mencegah Akses & Modifikasi Sembarangan:** Menutup akses langsung dari kelas luar sehingga pengguna/driver tidak bisa seenaknya mengubah state objek secara ilegal (seperti menaikkan kecepatan saat mesin mati).
2. **Menjaga Integritas Data (*Data Integrity*):** Memastikan bahwa seluruh perubahan nilai atribut hanya dapat dilakukan melalui method resmi (`nyalakanMesin()`, `tambahKecepatan()`, dsb.) yang telah dipasangi aturan logika dan validasi.
3. **Mencerminkan Perilaku Nyata:** Dalam dunia nyata, pengendara sepeda motor tidak bisa langsung memutar jarum speedometer menjadi 50 km/jam tanpa menghidupkan mesin dan menarik tuas gas secara bertahap.

---

### Pertanyaan 3:
> Ubah class Motor sehingga kecepatan maksimalnya adalah 100!

**Jawaban:**
Untuk membatasi kecepatan maksimal motor menjadi 100, kita memodifikasi method `tambahKecepatan()` dengan menambahkan kondisi pemeriksaan batas atas (`kecepatan + 5 <= 100`).

**Potongan Kode Modifikasi pada `Motor.java`:**
```java
    public void tambahKecepatan() {
        if (kontakOn == true) {
            if (kecepatan + 5 <= 100) {
                kecepatan += 5;
            } else {
                kecepatan = 100;
                System.out.println("Kecepatan tidak bisa bertambah karena sudah mencapai batas maksimal 100! \n");
            }
        } else {
            System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off! \n");
        }
    }
```
**Penjelasan:**
Dengan modifikasi ini, jika motor dalam keadaan ON dan kecepatan belum mencapai 100, kecepatan akan bertambah 5 setiap pemanggilan method. Namun jika penambahan tersebut akan melebihi atau telah mencapai 100, kecepatan ditahan di angka maksimal 100 dan sistem mencetak pemberitahuan bahwa batas maksimal telah tercapai.

---

## 5. PERCOBAAN 3: GETTER DAN SETTER

### 5.1 Deskripsi Kasus
Pada studi kasus koperasi, dibuat class `Anggota` yang memiliki atribut `nama`, `alamat`, dan `simpanan`. Atribut `nama` dan `alamat` dilengkapi getter dan setter, sedangkan atribut `simpanan` hanya memiliki getter (`getSimpanan()`) tanpa setter, karena perubahan saldo simpanan hanya boleh terjadi lewat transaksi `setor()` dan `pinjam()`.

**Kode Program `Anggota.java` (Percobaan 3):**
```java
package Koperasi;

public class Anggota {
    private String nama;
    private String alamat;
    private float simpanan;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public float getSimpanan() {
        return simpanan;
    }

    public void setor(float uang) {
        simpanan += uang;
    }

    public void pinjam(float uang) {
        simpanan -= uang;
    }
}
```

**Kode Program `KoperasiDemo.java` (Percobaan 3):**
```java
package Koperasi;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota();
        anggota1.setNama("Iwan Setiawan");
        anggota1.setAlamat("Jalan Sukarno Hatta no 10");
        anggota1.setor(100000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.pinjam(5000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());
    }
}
```

### 5.2 Output Percobaan 3
```text
Simpanan Iwan Setiawan : Rp 100000.0
Simpanan Iwan Setiawan : Rp 95000.0
```

---

## 6. PERCOBAAN 4: KONSTRUKTOR DAN INSTANSIASI

### 6.1 Deskripsi & Masalah Nilai Default
Ketika class `KoperasiDemo` dimodifikasi untuk langsung menampilkan nama dan simpanan sebelum setter dipanggil:
```java
Anggota anggota1 = new Anggota();
System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());
```
Output program menghasilkan:
```text
Simpanan null : Rp 0.0
```
Nilai `null` muncul karena atribut referensi String `nama` belum diinisialisasi dengan nilai awal yang valid. Solusinya adalah menyediakan **Konstruktor berparameter** (*Parameterized Constructor*).

### 6.2 Perbaikan Menggunakan Konstruktor

**Kode Program `Anggota.java` (Dengan Konstruktor):**
```java
package Koperasi;

public class Anggota {
    private String nama;
    private String alamat;
    private float simpanan;

    // Konstruktor dengan parameter nama dan alamat
    Anggota(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.simpanan = 0;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public float getSimpanan() {
        return simpanan;
    }

    public void setor(float uang) {
        simpanan += uang;
    }

    public void pinjam(float uang) {
        simpanan -= uang;
    }
}
```

**Kode Program `KoperasiDemo.java` (Instansiasi dengan Parameter):**
```java
package Koperasi;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota("Iwan", "Jalan Mawar");
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.setNama("Iwan Setiawan");
        anggota1.setAlamat("Jalan Sukarno Hatta no 10");
        anggota1.setor(100000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.pinjam(5000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());
    }
}
```

### 6.3 Output Percobaan 4
```text
Simpanan Iwan : Rp 0.0
Simpanan Iwan Setiawan : Rp 100000.0
Simpanan Iwan Setiawan : Rp 95000.0
```

---

## 7. PERTANYAAN 3.6 (PERCOBAAN 3 & 4) BESERTA JAWABAN

### Pertanyaan 1:
> Apa yang dimaksud getter dan setter?

**Jawaban:**
* **Getter (Accessor Method):** Method `public` yang memiliki tipe nilai kembalian (*return type*), digunakan untuk membaca atau mengambil nilai dari atribut yang dideklarasikan `private` dari luar kelas.
* **Setter (Mutator Method):** Method `public` bertipe `void` yang menerima parameter masukan, digunakan untuk memberi atau mengubah nilai atribut `private` dengan menerapkan aturan validasi internal terlebih dahulu.

---

### Pertanyaan 2:
> Apa kegunaan dari method getSimpanan()?

**Jawaban:**
Method `getSimpanan()` berguna untuk membaca dan mengembalikan nilai saldo simpanan milik anggota koperasi yang tersimpan secara aman dalam variabel `private float simpanan`. Dengan method ini, pihak luar dapat mengetahui saldo simpanan tanpa memiliki kemampuan untuk mengubah nilainya secara langsung.

---

### Pertanyaan 3:
> Method apa yang digunakan untuk menambah saldo?

**Jawaban:**
Method `setor(float uang)`. Pada method ini, nilai simpanan ditambah sebesar nilai parameter yang diinputkan (`simpanan += uang;`).

---

### Pertanyaan 4:
> Apa yang dimaksud konstruktor?

**Jawaban:**
Konstruktor adalah blok fungsi khusus pada suatu kelas yang namanya persis sama dengan nama kelas, tidak memiliki tipe data kembalian (*return type*), dan secara otomatis dieksekusi oleh Java Virtual Machine (JVM) saat proses instansiasi objek menggunakan kata kunci `new`. Konstruktor berfungsi mengalokasikan memori dan memberikan nilai inisialisasi awal pada atribut objek.

---

### Pertanyaan 5:
> Sebutkan aturan dalam membuat konstruktor?

**Jawaban:**
1. Nama konstruktor **harus sama persis** dengan nama kelas (termasuk huruf besar dan kecilnya).
2. Konstruktor **tidak memiliki tipe kembalian** (*return type*), bahkan tidak boleh menuliskan `void`. Jika diberi tipe data/`void`, method tersebut akan dianggap sebagai method biasa, bukan konstruktor.
3. Konstruktor **tidak boleh menggunakan kata kunci non-access modifier** seperti `abstract`, `static`, `final`, maupun `synchronized`.
4. Konstruktor dapat memiliki *access modifier* (`public`, `protected`, `default`, atau `private`).
5. Jika di dalam kelas tidak didefinisikan konstruktor sama sekali, *compiler* Java otomatis menyediakan *default constructor* tanpa parameter (*no-argument constructor*). Namun jika sudah didefinisikan konstruktor berparameter, *default constructor* tidak disediakan lagi secara otomatis.

---

### Pertanyaan 6:
> Apakah boleh konstruktor bertipe private?

**Jawaban:**
**Boleh**. Konstruktor bertipe `private` digunakan untuk mencegah kelas tersebut diinstansiasi secara bebas dari luar kelas menggunakan kata kunci `new`. Penerapan konstruktor private umum ditemui pada:
1. **Singleton Design Pattern:** Memastikan hanya ada satu instance objek dari kelas tersebut yang dibuat di dalam memori.
2. **Utility Class:** Kelas yang hanya berisi sekumpulan konstanta dan method statis (contohnya kelas bawaan Java `java.lang.Math`), di mana pembuatan objek menjadi tidak relevan.
3. **Factory Method Pattern:** Penciptaan objek diatur dan dikontrol secara terpusat melalui *static method* di dalam kelas tersebut.

---

### Pertanyaan 7:
> Kapan menggunakan konstruktor dengan passing parameter?

**Jawaban:**
Konstruktor dengan *passing parameter* (*parameterized constructor*) digunakan ketika suatu objek saat pertama kali dibuat **memerlukan data inisialisasi yang spesifik, dinamis, atau wajib ada (mandatori)** yang ditentukan oleh pemanggil (misalnya: saat mendaftar nasabah baru, nama dan alamat wajib diisi). Hal ini menjamin bahwa objek yang tercipta langsung berada dalam status (*state*) yang valid dan siap digunakan segera setelah instansiasi selesai, serta menghindari adanya atribut bernilai kosong (*null*) yang tidak diinginkan.

---

### Pertanyaan 8:
> Apa perbedaan inisialisasi atribut dan instansiasi atribut?

**Jawaban:**
* **Inisialisasi Atribut:** Proses pengisian atau pemberian nilai awal pada suatu variabel/atribut. Nilai yang diisikan dapat berupa tipe data primitif (seperti `int kecepatan = 0;`, `boolean kontakOn = false;`) maupun nilai awal objek.
* **Instansiasi Atribut:** Proses pembuatan objek baru di memori menggunakan kata kunci `new` untuk dijadikan nilai referensi bagi atribut yang bertipe kelas/kompleks (misalnya: `private Mesin mesin = new Mesin();` atau di dalam konstruktor: `this.mesin = new Mesin();`).

---

### Pertanyaan 9:
> Apa perbedaan inisialisasi method dan instansiasi method?

**Jawaban:**
Dalam terminologi pemrograman berorientasi objek di Java:
* **Inisialisasi Method:** Secara teknis Java tidak melakukan inisialisasi pada method, melainkan melakukan **Deklarasi dan Definisi Method**, yaitu merancang *header method* (nama, return type, parameter) dan menuliskan isi blok kodenya (*method body*). Namun jika merujuk pada konsep *Functional Programming* (Java 8+), inisialisasi method dapat berupa mengasosiasikan referensi method (*method reference*) ke suatu variabel antarmuka fungsional (*Functional Interface*).
* **Instansiasi Method:** Istilah ini secara teknis **tidak ada / keliru**, karena yang dapat diinstansiasi hanyalah **Class menjadi Object**. Method tidak dapat diinstansiasi; method hanya didefinisikan di dalam kelas dan selanjutnya **dipanggil (*invoked/called*)** melalui referensi objek hasil instansiasi (atau dipanggil langsung jika berupa `static method`).

---

## 8. KESIMPULAN MODUL PRAKTIKUM

Dari serangkaian percobaan dan pemahaman teori pada modul ini, dapat disimpulkan bahwa:
1. **Enkapsulasi** merupakan pilar utama PBO yang melindungi atribut kelas dari akses liar di luar kelas dengan menyetel hak aksesnya menjadi `private`.
2. **Access Modifier** memberikan batasan otoritas yang jelas: `private` untuk keamanan tertinggi, `default` untuk lingkup satu paket, `protected` untuk mekanisme pewarisan (*inheritance*), dan `public` untuk fungsi yang memang ditujukan sebagai antarmuka luar.
3. **Getter dan Setter** bertindak sebagai perantara yang aman. Setter memungkinkan diterapkannya validasi logika bisnis sebelum nilai atribut benar-benar diubah, sedangkan Getter memberikan data tanpa membiarkan variabel aslinya diubah.
4. **Konstruktor** menjamin siklus awal suatu objek berada dalam keadaan valid dan konsisten saat pertama kali dialokasikan ke memori.

---

## 9. TUGAS PRAKTIKUM (JOBSHEET HALAMAN 14 - 17) BESERTA JAWABAN LENGKAP

---

### Soal 1: Percobaan Program EncapDemo dan EncapTest

Cobalah program di bawah ini dan tuliskan hasil outputnya:

**Kode Program `EncapDemo.java` (Versi Soal 1):**
```java
package Tugas;

public class EncapDemo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        if (newAge > 30) {
            age = 30;
        } else {
            age = newAge;
        }
    }
}
```

**Kode Program `EncapTest.java`:**
```java
package Tugas;

public class EncapTest {
    public static void main(String args[]) {
        EncapDemo encap = new EncapDemo();
        encap.setName("James");
        encap.setAge(35);

        System.out.println("Name : " + encap.getName());
        System.out.println("Age : " + encap.getAge());
    }
}
```

**Hasil Output Program:**
```text
Name : James
Age : 30
```

---

### Soal 2: Penjelasan Nilai Umur (Age)

> Pada program di atas, pada class `EncapTest` kita mengeset `age` dengan nilai 35, namun pada saat ditampilkan ke layar nilainya 30, jelaskan mengapa.

**Jawaban:**
Hal tersebut terjadi karena di dalam method `setAge(int newAge)` terdapat logika kontrol validasi nilai sebagai berikut:
```java
if (newAge > 30) {
    age = 30;
} else {
    age = newAge;
}
```
Ketika pemanggilan `encap.setAge(35)` dijalankan, nilai argumen `35` dievaluasi pada kondisi `if (newAge > 30)`. Karena `35 > 30` bernilai `true`, maka pernyataan di dalam blok `if` dieksekusi, yaitu nilai atribut `age` diatur menjadi `30`. Dengan demikian, nilai yang tersimpan dan diambil kembali melalui method `getAge()` adalah **30**.

---

### Soal 3: Modifikasi EncapDemo (Maksimal 30 dan Minimal 18)

> Ubah program di atas agar atribut `age` dapat diberi nilai maksimal 30 dan minimal 18.

**Jawaban:**
Untuk membatasi nilai `age` agar berada dalam rentang minimal 18 dan maksimal 30, kita modifikasi logika percabangan di dalam method `setAge(int newAge)`.

**Kode Program `EncapDemo.java` (Modifikasi):**
```java
package Tugas;

public class EncapDemo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getAge() {
        return age;
    }

    // Modifikasi: Nilai maksimal 30 dan minimal 18
    public void setAge(int newAge) {
        if (newAge > 30) {
            age = 30;
        } else if (newAge < 18) {
            age = 18;
        } else {
            age = newAge;
        }
    }
}
```

**Pengujian pada `EncapTest.java`:**
```java
package Tugas;

public class EncapTest {
    public static void main(String args[]) {
        EncapDemo encap = new EncapDemo();
        encap.setName("James");
        
        // Uji nilai di atas batas maksimal (> 30)
        encap.setAge(35);
        System.out.println("Name : " + encap.getName());
        System.out.println("Age (set 35 -> max 30): " + encap.getAge());

        // Uji nilai di bawah batas minimal (< 18)
        encap.setAge(15);
        System.out.println("Age (set 15 -> min 18): " + encap.getAge());

        // Uji nilai normal di dalam rentang 18 - 30
        encap.setAge(24);
        System.out.println("Age (set 24 -> valid): " + encap.getAge());
    }
}
```

**Hasil Output Pengujian:**
```text
Name : James
Age (set 35 -> max 30): 30
Age (set 15 -> min 18): 18
Age (set 24 -> valid): 24
```

---

### Soal 4: Sistem Kargo Logistik Kontainer

> Pada sebuah sistem manajemen pergudangan kargo ekspedisi, terdapat class `Kontainer` yang memiliki atribut antara lain `nomorResi`, `namaPemilik`, `kapasitasMaksimal` (dalam kg), dan `beratMuatanSaatIni`. Kontainer dapat menerima tambahan muatan barang dengan batasan kapasitas maksimal yang telah ditentukan. Kontainer juga dapat diturunkan muatannya (bongkar muat). Ketika barang diturunkan, maka jumlah muatan saat ini akan berkurang sesuai dengan nominal berat yang dikeluarkan.
> 
> Buatlah class `Kontainer` tersebut, berikan atribut (private), method getter, dan konstruktor sesuai dengan kebutuhan arsitektur enkapsulasi. Uji dengan kelas driver `TestLogistik`.

**Kode Program `Kontainer.java`:**
```java
package Tugas;

public class Kontainer {
    private String nomorResi;
    private String namaPemilik;
    private double kapasitasMaksimal;
    private double beratMuatanSaatIni;

    public Kontainer(String nomorResi, String namaPemilik, double kapasitasMaksimal) {
        this.nomorResi = nomorResi;
        this.namaPemilik = namaPemilik;
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.beratMuatanSaatIni = 0.0;
    }

    public String getNomorResi() {
        return nomorResi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }

    public double getBeratMuatanSaatIni() {
        return beratMuatanSaatIni;
    }

    public void tambahMuatan(double berat) {
        if (beratMuatanSaatIni + berat > kapasitasMaksimal) {
            System.out.println("Maaf, berat muatan melebihi kapasitas maksimal kontainer.");
        } else {
            beratMuatanSaatIni += berat;
        }
    }

    public void turunkanMuatan(double berat) {
        if (berat > beratMuatanSaatIni) {
            System.out.println("Maaf, berat muatan yang diturunkan melebihi muatan saat ini!");
        } else {
            beratMuatanSaatIni -= berat;
        }
    }
}
```

**Kode Driver `TestLogistik.java`:**
```java
package Tugas;

public class TestLogistik {
    public static void main(String[] args) {
        Kontainer kontainerAlfa = new Kontainer("REQ-9988", "PT. Maju Bersama", 5000);

        System.out.println("Nama Pemilik Kontainer: " + kontainerAlfa.getNamaPemilik());
        System.out.println("Kapasitas Maksimal: " + kontainerAlfa.getKapasitasMaksimal() + " kg");

        System.out.println("\nMemasukkan muatan baru seberat 6.000 kg...");
        kontainerAlfa.tambahMuatan(6000);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMemasukkan muatan baru seberat 4.000 kg...");
        kontainerAlfa.tambahMuatan(4000);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMembongkar muat/menurunkan barang seberat 500 kg...");
        kontainerAlfa.turunkanMuatan(500);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMembongkar muat/menurunkan barang seberat 1.500 kg...");
        kontainerAlfa.turunkanMuatan(1500);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");
    }
}
```

**Hasil Output yang Diharapkan & Terverifikasi:**
```text
Nama Pemilik Kontainer: PT. Maju Bersama
Kapasitas Maksimal: 5000.0 kg

Memasukkan muatan baru seberat 6.000 kg...
Maaf, berat muatan melebihi kapasitas maksimal kontainer.
Berat muatan saat ini: 0.0 kg

Memasukkan muatan baru seberat 4.000 kg...
Berat muatan saat ini: 4000.0 kg

Membongkar muat/menurunkan barang seberat 500 kg...
Berat muatan saat ini: 3500.0 kg

Membongkar muat/menurunkan barang seberat 1.500 kg...
Berat muatan saat ini: 2000.0 kg
```

---

### Soal 5: Modifikasi Keselamatan Crane (Maksimal 50% dari Muatan Saat Ini)

> Modifikasi soal kargo logistik di atas agar nominal berat muatan yang dibongkar/diturunkan dalam satu kali pemanggilan method `turunkanMuatan()` maksimal hanya boleh sebesar 50% dari total berat muatan saat ini. Langkah ini diterapkan demi alasan keselamatan kerja operasional alat berat (crane). Jika operator mencoba menurunkan muatan melebihi batas 50% tersebut, sistem harus memblokir aksi dan memunculkan peringatan: *"Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!"*.

**Jawaban & Modifikasi Method `turunkanMuatan` pada `Kontainer.java`:**
```java
    public void turunkanMuatan(double berat) {
        // Validasi keselamatan operasional crane: maksimal 50% dari muatan saat ini
        if (berat > (0.5 * beratMuatanSaatIni)) {
            System.out.println("Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!");
        } else if (berat > beratMuatanSaatIni) {
            System.out.println("Maaf, berat muatan yang diturunkan melebihi berat muatan saat ini!");
        } else {
            beratMuatanSaatIni -= berat;
        }
    }
```

**Pengujian Modifikasi pada `TestLogistik.java`:**
```java
        // Melanjutkan dari kondisi muatan saat ini = 2000.0 kg
        System.out.println("\n--- Pengujian Batas Keselamatan Crane 50% (Tugas 5) ---");
        System.out.println("Mencoba menurunkan barang seberat 1.200 kg (lebih dari 50% dari 2.000 kg)...");
        kontainerAlfa.turunkanMuatan(1200);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");
```

**Hasil Output Tambahan:**
```text
--- Pengujian Batas Keselamatan Crane 50% (Tugas 5) ---
Mencoba menurunkan barang seberat 1.200 kg (lebih dari 50% dari 2.000 kg)...
Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!
Berat muatan saat ini: 2000.0 kg
```
**Analisis:**
Karena 1.200 kg melebihi 50% dari muatan saat ini (50% dari 2.000 kg = 1.000 kg), sistem secara otomatis memblokir pembongkaran dan berat muatan tetap berada pada 2.000 kg.

---

### Soal 6: Modifikasi Driver TestLogistik Interaktif via `java.util.Scanner`

> Modifikasi kelas Main `TestLogistik` agar parameter jumlah berat barang yang dimasukkan (`tambahMuatan`) maupun berat barang yang dibongkar (`turunkanMuatan`) dapat menerima input nilai dinamis dari pengguna secara interaktif melalui terminal menggunakan utilitas `java.util.Scanner`.

**Kode Program `TestLogistikScanner.java`:**
```java
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
```

**Simulasi Interaksi Terminal:**
```text
==================================================
   SISTEM MANAJEMEN LOGISTIK KARGO KONTAINER     
==================================================
Masukkan Nomor Resi        : REQ-9988
Masukkan Nama Pemilik      : PT. Maju Bersama
Masukkan Kapasitas Max (kg): 5000

Kontainer berhasil dibuat!
Pemilik: PT. Maju Bersama | Resi: REQ-9988
Kapasitas Maksimal: 5000.0 kg

--- MENU OPERASIONAL KONTAINER ---
1. Tambah Muatan
2. Turunkan Muatan (Bongkar Muat)
3. Cek Status Kontainer
4. Keluar
Pilih opsi (1-4): 1
Masukkan berat muatan yang ditambahkan (kg): 4000
Berat muatan saat ini: 4000.0 kg

--- MENU OPERASIONAL KONTAINER ---
1. Tambah Muatan
2. Turunkan Muatan (Bongkar Muat)
3. Cek Status Kontainer
4. Keluar
Pilih opsi (1-4): 2
Masukkan berat muatan yang ingin diturunkan (kg): 1000
Berat muatan saat ini: 3000.0 kg

--- MENU OPERASIONAL KONTAINER ---
1. Tambah Muatan
2. Turunkan Muatan (Bongkar Muat)
3. Cek Status Kontainer
4. Keluar
Pilih opsi (1-4): 4
Terima kasih telah menggunakan sistem logistik.
```

---

### Soal 7: Sistem Pemesanan Tiket Bioskop

> Sebuah aplikasi pemesanan tiket bioskop memerlukan kelas `Tiket` untuk mengelola data pemesanan secara aman. Kelas ini harus memiliki atribut private: `judulFilm` (String), `hargaDasar` (double), dan `statusPembayaran` (boolean).
> 
> Ketentuan pengesetan nilai objek:
> * Konstruktor harus menerima parameter `judulFilm` dan `hargaDasar`. Nilai awal `statusPembayaran` selalu diset `false` (Belum Dibayar).
> * Atribut `hargaDasar` tidak boleh bernilai negatif. Jika input yang dimasukkan kurang dari 0, otomatis set nilai default ke Rp 35.000.
> * Sediakan method `lakukanPembayaran()` untuk mengubah `statusPembayaran` menjadi `true`.
> * Nilai `statusPembayaran` hanya boleh dibaca (*Read-Only*) menggunakan getter, tidak boleh memiliki fungsi setter langsung dari luar kelas demi alasan keamanan transaksi.
> * Uji kode Anda menggunakan kelas `TestBioskop`.

**Kode Program `Tiket.java`:**
```java
package Tugas;

public class Tiket {
    private String judulFilm;
    private double hargaDasar;
    private boolean statusPembayaran;

    public Tiket(String judulFilm, double hargaDasar) {
        this.judulFilm = judulFilm;
        
        // Validasi: harga tidak boleh negatif. Jika < 0 diset default ke Rp 35.000
        if (hargaDasar < 0) {
            this.hargaDasar = 35000.0;
        } else {
            this.hargaDasar = hargaDasar;
        }
        
        // Status pembayaran awal selalu false (Belum Dibayar)
        this.statusPembayaran = false;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    // Read-only getter untuk keamanan status transaksi
    public boolean isStatusPembayaran() {
        return statusPembayaran;
    }

    // Method mutasi resmi untuk menyelesaikan pembayaran
    public void lakukanPembayaran() {
        this.statusPembayaran = true;
    }
}
```

**Kode Driver `TestBioskop.java`:**
```java
package Tugas;

public class TestBioskop {
    public static void main(String[] args) {
        Tiket tiket1 = new Tiket("Avengers: Endgame", -50000);
        System.out.println("Film: " + tiket1.getJudulFilm());
        System.out.println("Harga Tiket: " + tiket1.getHargaDasar());
        System.out.println("Status Lunas? " + tiket1.isStatusPembayaran());

        System.out.println("\nMemproses pembayaran...");
        tiket1.lakukanPembayaran();
        System.out.println("Status Lunas Terbaru? " + tiket1.isStatusPembayaran());
    }
}
```

**Hasil Output Program:**
```text
Film: Avengers: Endgame
Harga Tiket: 35000.0
Status Lunas? false

Memproses pembayaran...
Status Lunas Terbaru? true
```

**Analisis Konsep Enkapsulasi pada Tiket Bioskop:**
1. **Validasi Nilai Default pada Konstruktor:** Saat input harga negatif (`-50000`) dimasukkan, konstruktor mendeteksi nilai tidak valid tersebut dan otomatis menetapkan nilai default `35000.0`. Hal ini mencegah terjadinya *bug* atau kerugian finansial akibat penetapan harga tidak valid.
2. ***Read-Only Property*:** Atribut `statusPembayaran` tidak memiliki method `setStatusPembayaran(boolean status)`. Ini menjamin integritas transaksi sehingga status pembayaran tidak dapat diubah sembarangan menjadi lunas (`true`) oleh pihak luar tanpa melalui alur resmi method `lakukanPembayaran()`.
