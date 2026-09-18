# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK
## Pertemuan 4: Relasi Kelas (Aggregation, Composition, dan Dependency)

---

### Identitas Mahasiswa
- **Nama** : Ibni Andarta
- **Kelas** : TI-2G
- **NIM** : 254107020258
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek (PBO)

---

## A. Ringkasan Materi & Teori

Relasi antar kelas dalam Pemrograman Berorientasi Objek mendefinisikan bagaimana objek-objek berinteraksi, berbagi data, dan terhubung satu sama lain. Pada Jobsheet 4, dipelajari tiga bentuk relasi utama:

1. **Aggregation (Agregasi - *has-a*)**:
   - Merupakan relasi *whole-part* dengan kepemilikan yang lemah (*weak ownership*).
   - Daur hidup (*lifecycle*) objek *part* independen dari objek *whole*. Objek *part* dibuat di luar kelas *whole*, kemudian disuntikkan (*injected*) melalui constructor atau setter.
   - Dilambangkan dengan notasi *white diamond* kosong (◇) pada diagram UML.

2. **Composition (Komposisi - *has-a*)**:
   - Merupakan relasi *whole-part* dengan kepemilikan eksklusif yang kuat (*strong ownership*).
   - Daur hidup objek *part* terikat erat dengan objek *whole*. Objek *part* dibuat sendiri oleh *whole* (biasanya di dalam constructor *whole*), dan tidak disediakan setter publik untuk mengganti objek tersebut dari luar. Jika objek *whole* dimusnahkan, objek *part* ikut musnah.
   - Dilambangkan dengan notasi *black diamond* terisi (◆) pada diagram UML.

3. **Dependency (Dependensi - *uses-a*)**:
   - Merupakan relasi fungsional di mana suatu kelas membutuhkan kelas lain secara temporer / sesaat saja.
   - Objek lain tidak disimpan sebagai atribut kelas, melainkan hanya dioper sebagai parameter sebuah method atau dibuat sebagai variabel lokal method.
   - Dilambangkan dengan garis putus-putus berpanah terbuka (`- - - ->`) pada diagram UML.

---

## B. Percobaan 1: Aggregation Satu-ke-Satu (Laptop dan Processor)

### 1. Deskripsi Singkat
Mengimplementasikan relasi Aggregation satu-ke-satu antara kelas `Laptop` (*whole*) dan kelas `Processor` (*part*). Objek `Processor` dibuat di luar kelas `Laptop` lalu dihubungkan menggunakan konstruktor berparameter maupun setter.

### 2. Kode Program
- **`Processor.java`**
```java
package Jobsheet4.LaptopProcessor;

public class Processor {
    private String merk;
    private double cache;

    public Processor(){
    }

    public Processor(String merk, double cache) {
        this.merk = merk;
        this.cache = cache;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }
    
    public void info(){
        System.out.printf("Merk Processor\t: %s\n", merk);
        System.out.printf("Cache Memory = %.2f\n", cache);
    }
}
```

- **`Laptop.java`**
```java
package Jobsheet4.LaptopProcessor;

public class Laptop {
    private String merk;
    private Processor proc;

    public Laptop(){
    }

    public Laptop(String merk, Processor proc) {
        this.merk = merk;
        this.proc = proc;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public Processor getProc() {
        return proc;
    }

    public void setProc(Processor proc) {
        this.proc = proc;
    }
    
    public void info(){
        System.out.println("Merk Laptop = " + merk);
        proc.info();
    }
}
```

- **`MainPercobaan1.java`**
```java
package Jobsheet4.LaptopProcessor;

public class MainPercobaan1 {
    public static void main(String[] args) {
        Processor p = new Processor("Intel i5", 3);
        Laptop l = new Laptop("Thinkpad", p);
        l.info();

        Processor p1 = new Processor();
        p1.setMerk("Intel i5");
        p1.setCache(4);
        Laptop l1 = new Laptop();
        l1.setMerk("Thinkpad");
        l1.setProc(p1);
        l1.info();

        Laptop l2 = new Laptop("Thinkpad",
                new Processor("Intel i5", 3));
        l2.info();
    }
}
```

### 3. Output Eksekusi
```
Merk Laptop = Thinkpad
Merk Processor	: Intel i5
Cache Memory = 3,00
Merk Laptop = Thinkpad
Merk Processor	: Intel i5
Cache Memory = 4,00
Merk Laptop = Thinkpad
Merk Processor	: Intel i5
Cache Memory = 3,00
```

### 4. Jawaban Pertanyaan Percobaan 1
1. **Di dalam class Processor dan class Laptop, terdapat method setter dan getter untuk masing-masing atributnya. Apakah gunanya method setter dan getter tersebut?**  
   **Jawab:**  
   Method setter dan getter berguna untuk menerapkan prinsip **Enkapsulasi** (*Encapsulation*). Atribut dideklarasikan dengan access modifier `private` agar terlindung dari akses langsung dari luar kelas. Getter (*accessor*) bertindak sebagai perantara aman untuk membaca nilai atribut, sedangkan setter (*mutator*) bertindak sebagai perantara untuk memvalidasi dan mengubah nilai atribut secara terkontrol.

2. **Di dalam class Processor dan class Laptop, masing-masing terdapat konstruktor default dan konstruktor berparameter. Bagaimanakah beda penggunaan dari kedua jenis konstruktor tersebut?**  
   **Jawab:**  
   - **Konstruktor default (tanpa parameter)**: Digunakan ketika objek ingin diinstansiasi terlebih dahulu dengan nilai bawaan, dan nilai atributnya akan diatur belakangan melalui method setter (*setter injection*).
   - **Konstruktor berparameter**: Digunakan ketika ingin menginstansiasi objek sekaligus menginisialisasi atribut-atributnya secara langsung (*constructor injection*) sehingga objek langsung siap pakai (*fully initialized*).

3. **Perhatikan class Laptop, di antara 2 atribut yang dimiliki (merk dan proc), atribut manakah yang bertipe object? Baris kode manakah yang menunjukkan bahwa class Laptop memiliki relasi dengan class Processor?**  
   **Jawab:**  
   - Atribut yang bertipe objek dari kelas yang dibuat sendiri adalah `proc` (bertipe `Processor`).  
   - Baris kode yang menunjukkan relasi:
     - Deklarasi field: `private Processor proc;`
     - Parameter konstruktor: `public Laptop(String merk, Processor proc)`
     - Parameter setter: `public void setProc(Processor proc)`

4. **Perhatikan pada class Laptop, apakah guna dari sintaks proc.info()?**  
   **Jawab:**  
   Sintaks tersebut merupakan mekanisme **Delegasi Method** (*method delegation*). Kelas `Laptop` tidak perlu mengetahui rincian bagaimana format data processor dicetak, melainkan mendelegasikan tanggung jawab pencetakan data processor kepada method `info()` milik objek `proc`.

5. **Pada Langkah 8, objek p dibuat lebih dulu baru diberikan ke constructor Laptop. Pada Langkah 10, objek Processor dibuat langsung di dalam argumen constructor Laptop (tanpa variabel p). Apakah keduanya menghasilkan output yang berbeda? Mengapa?**  
   **Jawab:**  
   Keduanya menghasilkan **output yang sama (identik)**. Alasannya, kedua cara tersebut sama-sama menginstansiasi objek `Processor` baru di memori *heap* dengan argumen `"Intel i5"` dan `3`. Bedanya hanya pada variabel perantara: Langkah 8 menyimpan alamat referensinya ke variabel referensi `p` terlebih dahulu, sedangkan Langkah 10 langsung menyalurkannya sebagai *anonymous object* ke dalam parameter constructor `Laptop`.

6. **Secara kode, apakah relasi Laptop-Processor pada percobaan ini termasuk Aggregation atau Composition? Tunjukkan baris kode yang menjadi bukti jawabanmu.**  
   **Jawab:**  
   Termasuk **Aggregation**. Buktinya:
   - Objek `Processor` diinstansiasi **di luar** kelas `Laptop` (di file `MainPercobaan1`), bukan di dalam kelas `Laptop` sendiri:
     ```java
     Processor p = new Processor("Intel i5", 3);
     Laptop l = new Laptop("Thinkpad", p);
     ```
   - Kelas `Laptop` menerima objek tersebut melalui parameter constructor (`public Laptop(..., Processor proc)`) atau setter (`public void setProc(Processor proc)`).

7. **Andaikan constructor Laptop diubah menjadi seperti berikut, sehingga Processor dibuat sendiri di dalam Laptop, bukan diterima sebagai parameter:**  
   ```java
   public Laptop (String merk) { 
       this.merk = merk; 
       this.proc = new Processor ("Generic", 1); 
   }
   ```  
   **Apakah relasi Laptop-Processor pada versi ini masih Aggregation? Jelaskan alasannya.**  
   **Jawab:**  
   **Tidak**, relasi tersebut berubah menjadi **Composition**. Hal ini karena objek `Processor` dibuat sendiri secara internal di dalam constructor kelas `Laptop` (`new Processor(...)`) tanpa menerima referensi dari luar. Dengan demikian, daur hidup (*lifecycle*) objek `Processor` terikat penuh dengan `Laptop`. Jika objek `Laptop` dihapus, objek `Processor` tersebut juga ikut musnah karena tidak ada referensi di luar yang memegangnya.

---

## C. Percobaan 2: Aggregation dengan Relasi Ganda (Rental Mobil)

### 1. Deskripsi Singkat
Mengimplementasikan relasi Aggregation ganda, di mana kelas `Pelanggan` (*whole*) berelasi dengan dua kelas part yang berbeda sekaligus, yaitu `Mobil` dan `Sopir`.

### 2. Kode Program
- **`Mobil.java`**
```java
package Jobsheet4.RentalMobil;

public class Mobil {
    private String merk;
    private int biaya;

    public Mobil() {
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getBiaya() {
        return biaya;
    }

    public int hitungBiayaMobil(int hari) {
        return biaya * hari;
    }
}
```

- **`Sopir.java`**
```java
package Jobsheet4.RentalMobil;

public class Sopir {
    private String nama;
    private int biaya;

    public Sopir() {
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getBiaya() {
        return biaya;
    }

    public int hitungBiayaSopir(int hari) {
        return biaya * hari;
    }
}
```

- **`Pelanggan.java`**
```java
package Jobsheet4.RentalMobil;

public class Pelanggan {
    private String nama;
    private Mobil mobil;
    private Sopir sopir;
    private int hari;

    public Pelanggan() {
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setSopir(Sopir sopir) {
        this.sopir = sopir;
    }

    public Sopir getSopir() {
        return sopir;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public int getHari() {
        return hari;
    }

    public int hitungBiayaTotal() {
        return mobil.hitungBiayaMobil(hari) + sopir.hitungBiayaSopir(hari);
    }
}
```

- **`MainPercobaan2.java`**
```java
package Jobsheet4.RentalMobil;

public class MainPercobaan2 {
    public static void main(String[] args) {
        Mobil m = new Mobil();
        m.setMerk("Avanza");
        m.setBiaya(350000);

        Sopir s = new Sopir();
        s.setNama("John Doe");
        s.setBiaya(200000);

        Pelanggan p = new Pelanggan();
        p.setNama("Jane Doe");
        p.setMobil(m);
        p.setSopir(s);
        p.setHari(2);

        System.out.println("Biaya Total = " + p.hitungBiayaTotal());
        System.out.println(p.getMobil().getMerk());
    }
}
```

### 3. Output Eksekusi
```
Biaya Total = 1100000
Avanza
```

### 4. Jawaban Pertanyaan Percobaan 2
1. **Perhatikan class Pelanggan. Pada baris program manakah yang menunjukkan bahwa class Pelanggan memiliki relasi dengan class Mobil dan class Sopir?**  
   **Jawab:**  
   Pada baris deklarasi atribut:
   ```java
   private Mobil mobil;
   private Sopir sopir;
   ```
   serta method mutator-nya:
   ```java
   public void setMobil(Mobil mobil) { this.mobil = mobil; }
   public void setSopir(Sopir sopir) { this.sopir = sopir; }
   ```

2. **Perhatikan method hitungBiayaSopir pada class Sopir, serta method hitungBiayaMobil pada class Mobil. Mengapa method tersebut harus memiliki argument hari, padahal hari sendiri adalah atribut milik Pelanggan, bukan milik Mobil atau Sopir?**  
   **Jawab:**  
   Karena durasi sewa (`hari`) adalah informasi kontekstual dari transaksi sewa yang dilakukan oleh `Pelanggan`. Objek `Mobil` dan `Sopir` hanya menyimpan informasi tarif harian (`biaya`). Agar kelas-kelas tersebut bersifat *reusable* dan independen, perhitungan total biaya sewa membutuhkan parameter `hari` yang dikirim dari pemanggil (`Pelanggan`).

3. **Perhatikan kode dari class Pelanggan. Untuk apakah perintah mobil.hitungBiayaMobil(hari) dan sopir.hitungBiayaSopir(hari)?**  
   **Jawab:**  
   Perintah tersebut digunakan untuk memanggil method perhitungan biaya pada masing-masing objek part dengan mengirimkan nilai `hari`, lalu nilai kembalian dari kedua method dijumlahkan untuk mendapatkan total biaya transaksi rental pada `hitungBiayaTotal()`.

4. **Perhatikan class MainPercobaan2. Untuk apakah sintaks p.setMobil(m) dan p.setSopir(s)?**  
   **Jawab:**  
   Untuk menginjeksikan referensi objek mobil `m` dan objek sopir `s` ke dalam atribut `mobil` dan `sopir` pada objek pelanggan `p` (*setter injection*).

5. **Untuk apakah proses p.hitungBiayaTotal()?**  
   **Jawab:**  
   Untuk memproses dan mengembalikan total biaya rental mobil beserta sopir selama durasi sewa yang telah ditentukan pada objek `p`.

6. **Pada Langkah 7, p.getMobil().getMerk() memanggil dua method sekaligus secara berantai. Jelaskan urutan eksekusinya: objek apa yang dikembalikan p.getMobil(), dan objek apa yang kemudian dipanggil .getMerk()-nya?**  
   **Jawab:**  
   - `p.getMobil()` dieksekusi terlebih dahulu pada objek `Pelanggan` `p`, mengembalikan referensi objek `Mobil` yang disewa.
   - Kemudian `.getMerk()` dipanggil pada objek `Mobil` hasil pengembalian tersebut, yang mengembalikan String nama merk mobil (`"Avanza"`).

7. **Andaikan p.setMobil(m) tidak pernah dipanggil lalu p.hitungBiayaTotal() dijalankan, error apa yang akan muncul? Jelaskan mengapa error itu terjadi, dikaitkan dengan konsep referensi objek yang sudah kita pelajari sebelumnya.**  
   **Jawab:**  
   Akan terjadi error **`NullPointerException`**.  
   Hal ini terjadi karena nilai default variabel referensi objek `mobil` pada `Pelanggan` adalah `null`. Jika `p.setMobil(m)` tidak dipanggil, maka variabel `mobil` belum menunjuk ke alamat memori objek manapun. Ketika `mobil.hitungBiayaMobil(hari)` dieksekusi, JVM berusaha memanggil method dari referensi kosong (`null`), sehingga sistem memunculkan runtime error `NullPointerException`.

---

## D. Percobaan 3: Aggregation dengan Dua Role ke Kelas yang Sama (Kereta Api)

### 1. Deskripsi Singkat
Mengimplementasikan relasi Aggregation di mana kelas `KeretaApi` memiliki dua atribut yang sama-sama bertipe `Pegawai`, namun dibedakan berdasarkan perannya (*role*): satu sebagai `masinis` dan satu lagi sebagai `asisten`. Dilengkapi penanganan *guard clause* untuk mengantisipasi `asisten` yang bernilai `null`.

### 2. Kode Program
- **`Pegawai.java`**
```java
package Jobsheet4.KeretaApi;

public class Pegawai {
    private String nip;
    private String nama;

    public Pegawai(String nip, String nama) {
        this.nip = nip;
        this.nama = nama;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String info() {
        String info = "";
        info += "Nip: " + this.nip + "\n";
        info += "Nama: " + this.nama + "\n";
        return info;
    }
}
```

- **`KeretaApi.java`**
```java
package Jobsheet4.KeretaApi;

public class KeretaApi {
    private String nama;
    private String kelas;
    private Pegawai masinis;
    private Pegawai asisten;

    public KeretaApi(String nama, String kelas, Pegawai masinis) {
        this.nama = nama;
        this.kelas = kelas;
        this.masinis = masinis;
    }

    public KeretaApi(String nama, String kelas, Pegawai masinis, Pegawai asisten) {
        this.nama = nama;
        this.kelas = kelas;
        this.masinis = masinis;
        this.asisten = asisten;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKelas() {
        return kelas;
    }

    public void setMasinis(Pegawai masinis) {
        this.masinis = masinis;
    }

    public Pegawai getMasinis() {
        return masinis;
    }

    public void setAsisten(Pegawai asisten) {
        this.asisten = asisten;
    }

    public Pegawai getAsisten() {
        return asisten;
    }

    public String info() {
        String info = "";
        info += "Nama: " + this.nama + "\n";
        info += "Kelas: " + this.kelas + "\n";
        info += "Masinis: " + this.masinis.info() + "\n";
        if (this.asisten != null) {
            info += "Asisten: " + this.asisten.info() + "\n";
        }
        return info;
    }
}
```

- **`MainPercobaan3.java`**
```java
package Jobsheet4.KeretaApi;

public class MainPercobaan3 {
    public static void main(String[] args) {
        Pegawai masinis = new Pegawai("1234", "Spongebob Squarepants");
        Pegawai asisten = new Pegawai("4567", "Patrick Star");
        KeretaApi keretaApi = new KeretaApi("Gaya Baru", "Bisnis", masinis, asisten);
        System.out.println(keretaApi.info());
    }
}
```

- **`MainPertanyaan.java`**
```java
package Jobsheet4.KeretaApi;

public class MainPertanyaan {
    public static void main(String[] args) {
        Pegawai masinis = new Pegawai("1234", "Spongebob Squarepants");
        KeretaApi keretaApi = new KeretaApi("Gaya Baru", "Bisnis", masinis);
        System.out.println(keretaApi.info());
    }
}
```

### 3. Output Eksekusi
- **Eksekusi `MainPercobaan3` (dengan asisten):**
```
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants

Asisten: Nip: 4567
Nama: Patrick Star
```

- **Eksekusi `MainPertanyaan` (tanpa asisten / asisten bernilai `null`):**
```
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants
```

### 4. Jawaban Pertanyaan Percobaan 3
1. **Di dalam method info() pada class KeretaApi, baris this.masinis.info() dan this.asisten.info() digunakan untuk apa?**  
   **Jawab:**  
   Digunakan untuk pendelegasian pemanggilan method `info()` milik objek `Pegawai` (`masinis` dan `asisten`) agar detail identitas (NIP dan Nama) masing-masing pegawai tersebut dapat diambil dan digabungkan ke dalam string informasi kereta api.

2. **Apa hasil output dari MainPertanyaan sebelum diperbaiki (Langkah 8)? Mengapa hal tersebut dapat terjadi?**  
   **Jawab:**  
   Program mengalami crash dengan pesan error:  
   `Exception in thread "main" java.lang.NullPointerException`  
   pada baris `info += "Asisten: " + this.asisten.info() + "\n";`.  
   Hal tersebut terjadi karena objek `KeretaApi` diinstansiasi menggunakan konstruktor 3-parameter (hanya memasukkan masinis), sehingga atribut `asisten` tidak diinisialisasi dan tetap bernilai `null`. Saat `this.asisten.info()` dipanggil, method dipanggil pada referensi `null`.

3. **Kaitkan dengan materi referensi objek: apa isi variabel asisten di dalam objek KeretaApi yang dibuat lewat constructor 3-parameter, sebelum guard clause ditambahkan?**  
   **Jawab:**  
   Isi variabel `asisten` adalah `null`. Dalam Java, variabel referensi objek yang belum dihubungkan ke instance objek manapun di memori heap secara otomatis memiliki nilai default `null`.

4. **Setelah guard clause ditambahkan (Langkah 9), apakah objek masinis juga perlu dicek dengan cara yang sama? Perhatikan kedua constructor KeretaApi, apakah mungkin masinis bernilai null? Jelaskan.**  
   **Jawab:**  
   Secara logika perancangan constructor kelas `KeretaApi`, parameter `masinis` wajib disertakan pada kedua constructor, sehingga diasumsikan kereta api selalu memiliki masinis (masinis tidak boleh kosong). Namun secara teknis bahasa Java, seorang pemanggil program masih bisa saja sengaja mengoper argumen bernilai `null` (misal: `new KeretaApi("Gaya Baru", "Bisnis", null)`). Oleh karena itu, pengecekan guard clause pada `asisten` adalah mutlak karena constructor 3 parameter melegalkan `asisten` bernilai `null`, sedangkan pengecekan pada `masinis` dapat ditambahkan sebagai praktik *defensive programming*.

5. **Kelas Pegawai dipakai lewat dua atribut berbeda (masinis dan asisten) pada KeretaApi. Apakah ini membuat KeretaApi punya dua objek Pegawai yang berbeda, atau satu objek Pegawai yang dipakai dua kali? Jelaskan berdasarkan kode pada Langkah 6.**  
   **Jawab:**  
   KeretaApi memiliki **dua objek Pegawai yang berbeda**. Hal ini dibuktikan dari pemanggilan operator `new` dua kali di `MainPercobaan3`:
   ```java
   Pegawai masinis = new Pegawai("1234", "Spongebob Squarepants");
   Pegawai asisten = new Pegawai("4567", "Patrick Star");
   ```
   Kedua objek tersebut memiliki alamat memori terpisah, NIP yang berbeda, dan nama yang berbeda.

---

## E. Percobaan 4: Array of Object dan Multiplicity (Gerbong, Kursi, dan Penumpang)

### 1. Deskripsi Singkat
Mengimplementasikan perpaduan relasi Composition dan Aggregation dengan konsep *multiplicity* satu-ke-banyak (`1..*`). Kelas `Gerbong` memiliki banyak `Kursi` dalam bentuk array (`Kursi[]`) yang diinisialisasi secara internal (Composition), dan setiap `Kursi` dapat diduduki oleh `0..1` `Penumpang` (Aggregation).

### 2. Kode Program
- **`Penumpang.java`**
```java
package Jobsheet4.GerbongKereta;

public class Penumpang {
    private String ktp;
    private String nama;

    public Penumpang(String ktp, String nama) {
        this.ktp = ktp;
        this.nama = nama;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getKtp() {
        return ktp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String info() {
        String info = "";
        info += "Ktp: " + ktp + "\n";
        info += "Nama: " + nama + "\n";
        return info;
    }
}
```

- **`Kursi.java`**
```java
package Jobsheet4.GerbongKereta;

public class Kursi {
    private String nomor;
    private Penumpang penumpang;

    public Kursi(String nomor) {
        this.nomor = nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNomor() {
        return nomor;
    }

    public void setPenumpang(Penumpang penumpang) {
        this.penumpang = penumpang;
    }

    public Penumpang getPenumpang() {
        return penumpang;
    }

    public String info() {
        String info = "";
        info += "Nomor: " + nomor + "\n";
        if (this.penumpang != null) {
            info += "Penumpang: " + penumpang.info() + "\n";
        }
        return info;
    }
}
```

- **`Gerbong.java`**
```java
package Jobsheet4.GerbongKereta;

public class Gerbong {
    private String kode;
    private Kursi[] arrayKursi;

    public Gerbong(String kode, int jumlah) {
        this.kode = kode;
        this.arrayKursi = new Kursi[jumlah];
        this.initKursi();
    }

    private void initKursi() {
        for (int i = 0; i < arrayKursi.length; i++) {
            this.arrayKursi[i] = new Kursi(String.valueOf(i + 1));
        }
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

    public Kursi[] getArrayKursi() {
        return arrayKursi;
    }

    public void setPenumpang(Penumpang penumpang, int nomor) {
        // Pengecekan / validasi agar tidak menimpa kursi yang telah berpenumpang (Pertanyaan 5)
        if (this.arrayKursi[nomor - 1].getPenumpang() != null) {
            System.out.println("Kursi nomor " + nomor + " sudah ditempati oleh " 
                    + this.arrayKursi[nomor - 1].getPenumpang().getNama() 
                    + ", tidak dapat digantikan oleh " + penumpang.getNama() + "!");
            return;
        }
        this.arrayKursi[nomor - 1].setPenumpang(penumpang);
    }

    public String info() {
        String info = "";
        info += "Kode: " + kode + "\n";
        for (Kursi kursi : arrayKursi) {
            info += kursi.info();
        }
        return info;
    }
}
```

- **`MainPercobaan4.java`**
```java
package Jobsheet4.GerbongKereta;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Penumpang p = new Penumpang("12345", "Mr. Krab");
        Gerbong gerbong = new Gerbong("A", 10);
        gerbong.setPenumpang(p, 1);
        System.out.println(gerbong.info());
    }
}
```

- **`MainPertanyaan4.java` (Pengujian Pertanyaan 4 & 5)**
```java
package Jobsheet4.GerbongKereta;

public class MainPertanyaan4 {
    public static void main(String[] args) {
        Penumpang p = new Penumpang("12345", "Mr. Krab");
        Gerbong gerbong = new Gerbong("A", 10);
        gerbong.setPenumpang(p, 1);

        // Uji coba menimpa kursi nomor 1 dengan penumpang baru
        Penumpang budi = new Penumpang("67890", "Budi");
        gerbong.setPenumpang(budi, 1);

        System.out.println(gerbong.info());
    }
}
```

### 3. Output Eksekusi
- **Eksekusi `MainPercobaan4`:**
```
Kode: A
Nomor: 1
Penumpang: Ktp: 12345
Nama: Mr. Krab

Nomor: 2
Nomor: 3
Nomor: 4
Nomor: 5
Nomor: 6
Nomor: 7
Nomor: 8
Nomor: 9
Nomor: 10
```

- **Eksekusi `MainPertanyaan4` (dengan validasi kursi ganda):**
```
Kursi nomor 1 sudah ditempati oleh Mr. Krab, tidak dapat digantikan oleh Budi!
Kode: A
Nomor: 1
Penumpang: Ktp: 12345
Nama: Mr. Krab

Nomor: 2
Nomor: 3
Nomor: 4
Nomor: 5
Nomor: 6
Nomor: 7
Nomor: 8
Nomor: 9
Nomor: 10
```

### 4. Jawaban Pertanyaan Percobaan 4
1. **Pada main program dalam class MainPercobaan4, berapakah jumlah kursi dalam Gerbong A?**  
   **Jawab:**  
   Jumlah kursi adalah **10 kursi**, karena diinstansiasi dengan `new Gerbong("A", 10);`.

2. **Perhatikan potongan kode if (this.penumpang != null) { ... } pada method info() dalam class Kursi. Apa maksud kode tersebut?**  
   **Jawab:**  
   Kode tersebut merupakan *guard clause* yang memeriksa apakah kursi tersebut sedang diduduki oleh penumpang atau kosong. Jika kursi berpenumpang (`!= null`), maka informasi penumpang akan dicetak. Jika kursi masih kosong, bagian cetak penumpang dilewati sehingga tidak menyebabkan `NullPointerException`.

3. **Mengapa pada method setPenumpang() dalam class Gerbong, nilai nomor dikurangi dengan angka 1?**  
   **Jawab:**  
   Karena pengindeksan array di Java berbasis nol (*zero-based indexing*), di mana elemen pertama beralamat di indeks `0`. Sementara itu, penomoran kursi yang dikenali pengguna dimulai dari angka `1`. Sehingga nomor kursi dikurangi 1 (`nomor - 1`) untuk menyesuaikan dengan indeks array `arrayKursi`.

4. **Instansiasi objek baru budi dengan tipe Penumpang, kemudian masukkan objek baru tersebut pada gerbong dengan gerbong.setPenumpang(budi, 1), menimpa Mr. Krab yang sudah duduk di sana. Apakah yang terjadi? Apakah Java memberi peringatan/error?**  
   **Jawab:**  
   Pada kode awal (sebelum modifikasi soal nomor 5), objek `budi` akan langsung menimpa referensi `Mr. Krab` pada kursi nomor 1. Java **tidak** memberikan error maupun peringatan runtime (*silent overwrite*), karena bagi Java menugaskan referensi objek baru ke variabel yang sudah ada adalah instruksi penugasan yang sepenuhnya sah. Akibatnya, penumpang lama hilang dari kursi tersebut.

5. **Modifikasi program sehingga tidak diperkenankan menduduki kursi yang sudah ada penumpang lain (tambahkan pengecekan pada Gerbong.setPenumpang() sebelum baris arrayKursi[nomor - 1].setPenumpang(...) dijalankan).**  
   **Jawab:**  
   Modifikasi dilakukan pada method `setPenumpang` di kelas `Gerbong.java`:
   ```java
   if (this.arrayKursi[nomor - 1].getPenumpang() != null) {
       System.out.println("Kursi nomor " + nomor + " sudah ditempati oleh " 
               + this.arrayKursi[nomor - 1].getPenumpang().getNama() 
               + ", tidak dapat digantikan oleh " + penumpang.getNama() + "!");
       return;
   }
   ```

6. **Bandingkan tiga bentuk relasi has-a yang sudah kita praktikkan: Laptop-Processor (Percobaan 1, 1-1), KeretaApi-Pegawai (Percobaan 3, dua relasi 1-1 bernama), dan Gerbong-Kursi (Percobaan 4, 1..*). Untuk kasus seperti apa kita akan memilih array, dan untuk kasus seperti apa kita akan memilih atribut bernama satu-satu?**  
   **Jawab:**  
   - **Atribut bernama satu-satu**: Dipilih jika relasi bersifat tetap dengan jumlah part sedikit/terbatas, dan masing-masing part memiliki peran semantik yang berbeda serta diperlakukan secara spesifik (contoh: `masinis` dan `asisten`).
   - **Array / Koleksi**: Dipilih jika relasi memiliki *multiplicity* banyak (`1..*` atau `0..*`), jumlah part dinamis atau berjumlah banyak, dan semua part memiliki tipe seragam serta diperlakukan secara homogen (contoh: sekumpulan kursi di gerbong kereta).

7. **Terapkan kriteria kode (siapa yang memanggil new) pada dua relasi has-a di Percobaan ini: Gerbong-Kursi dan Kursi-Penumpang. Manakah yang Aggregation dan manakah yang Composition? Tunjukkan baris kode yang menjadi bukti untuk masing-masing.**  
   **Jawab:**  
   - **Gerbong – Kursi adalah Composition**:  
     Bukti kode: Objek `Kursi` diinstansiasi langsung di dalam method internal `initKursi()` milik `Gerbong`:
     ```java
     this.arrayKursi[i] = new Kursi(String.valueOf(i + 1));
     ```
     Objek Kursi tidak pernah dibuat di luar kelas Gerbong dan tidak ada setter untuk menggantinya.
   - **Kursi – Penumpang adalah Aggregation**:  
     Bukti kode: Objek `Penumpang` diinstansiasi di luar (`MainPercobaan4`):
     ```java
     Penumpang p = new Penumpang("12345", "Mr. Krab");
     gerbong.setPenumpang(p, 1);
     ```
     Objek disuntikkan dari luar lewat setter `setPenumpang()`. Penumpang dapat eksis mandiri tanpa kursi, dan kursi bisa ada tanpa penumpang.

---

## F. Percobaan 5: Composition (Mobil dan Mesin)

### 1. Deskripsi Singkat
Mengimplementasikan relasi Composition murni di mana kelas `Mobil` (*whole*) menciptakan objek `Mesin` miliknya sendiri di dalam constructor-nya. Kelas `Mobil` sengaja tidak menyediakan method setter maupun getter untuk `Mesin`, guna menjamin *lifecycle* eksklusif dan kepemilikan tunggal.

### 2. Kode Program
- **`Mesin.java`**
```java
package Jobsheet4.MobilMesin;

public class Mesin {
    private String tipe;

    public Mesin() {
        this.tipe = "4-silinder";
    }

    public String getTipe() {
        return tipe;
    }
}
```

- **`Mobil.java`**
```java
package Jobsheet4.MobilMesin;

public class Mobil {
    private String merek;
    private Mesin mesin;

    public Mobil(String merek) {
        this.merek = merek;
        this.mesin = new Mesin();
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getMerek() {
        return merek;
    }

    public void tampilkanInfo() {
        System.out.println("Mobil: " + merek);
        System.out.println("Mesin: " + mesin.getTipe());
    }
}
```

- **`MainPercobaan5.java`**
```java
package Jobsheet4.MobilMesin;

public class MainPercobaan5 {
    public static void main(String[] args) {
        Mobil mobil = new Mobil("Avanza");
        mobil.tampilkanInfo();
    }
}
```

### 3. Output Eksekusi
```
Mobil: Avanza
Mesin: 4-silinder
```

### 4. Jawaban Pertanyaan Percobaan 5
1. **Pada class Mobil, baris manakah yang menunjukkan bahwa Mesin adalah bagian yang “dimiliki secara eksklusif” oleh Mobil (bukan sekadar “dipinjam”)?**  
   **Jawab:**  
   Baris pada constructor `Mobil`:
   ```java
   this.mesin = new Mesin();
   ```
   Pemanggilan `new Mesin()` dilakukan secara mandiri di dalam kelas `Mobil` tanpa menerima masukan dari luar.

2. **Apa yang terjadi secara desain jika ditambahkan method setMesin(Mesin mesin) pada class Mobil? Apakah relasi ini akan tetap menjadi Composition? Jelaskan.**  
   **Jawab:**  
   Jika ditambahkan `setMesin(Mesin mesin)`, maka sifat kepemilikan eksklusif (*strong ownership*) akan rusak. Relasi tersebut akan terdegradasi menjadi **Aggregation**, karena kelas luar dapat kapan saja mengganti objek `Mesin` atau memasukkan objek mesin yang dibuat dari luar.

3. **Bandingkan dengan Percobaan 1 (Laptop-Processor): sebutkan satu perbedaan baris kode yang membuat salah satunya Aggregation dan yang lain Composition.**  
   **Jawab:**  
   - Pada Percobaan 1 (Aggregation): Objek part diterima sebagai parameter konstruktor:
     `public Laptop(String merk, Processor proc) { this.proc = proc; }`
   - Pada Percobaan 5 (Composition): Objek part dibuat sendiri secara internal di dalam konstruktor tanpa parameter bertipe Mesin:
     `public Mobil(String merek) { this.mesin = new Mesin(); }`

4. **Jika objek mobil di MainPercobaan5 di-set null setelah tampilkanInfo() dipanggil, apa yang terjadi pada objek Mesin miliknya? Bandingkan dengan nasib objek Processor pada Percobaan 1 seandainya objek Laptop-nya dihapus, apakah Processor tersebut masih bisa “diselamatkan” oleh kode lain? Kenapa Mesin tidak bisa?**  
   **Jawab:**  
   - Pada Percobaan 5: Jika `mobil = null;`, objek `Mesin` di dalamnya kehilangan referensi tunggalnya (*unreachable*) sehingga otomatis akan dibersihkan oleh *Garbage Collector*. Mesin tidak dapat diselamatkan karena sejak awal referensinya tidak pernah dipegang oleh variabel luar manapun.
   - Pada Percobaan 1: Objek `Processor` tetap aman di memori karena referensinya masih dipegang oleh variabel `p` di `MainPercobaan1` (`Processor p = new Processor(...)`).

5. **Coba (secara terpisah, boleh di file/package percobaan sendiri) tambahkan constructor kedua pada Mobil yang menerima parameter Mesin, mirip pola Percobaan 1: public Mobil(String merek, Mesin mesin) { this.merek = merek; this.mesin = mesin; }. Kalau constructor ini yang dipakai, apakah Mobil-Mesin berubah menjadi Aggregation? Jelaskan alasannya.**  
   **Jawab:**  
   **Ya**, relasi tersebut akan berubah menjadi **Aggregation**. Alasannya, objek `Mesin` dibuat di luar kelas `Mobil` kemudian disuntikkan (*injected*) ke dalam `Mobil`. Hal ini menyebabkan siklus hidup `Mesin` tidak lagi terikat eksklusif dengan `Mobil`, dan objek `Mesin` tersebut dapat tetap eksis di luar objek `Mobil`.

---

## G. Percobaan 6: Dependency / Uses-A (Laptop Mencetak Dokumen ke Printer)

### 1. Deskripsi Singkat
Mengimplementasikan relasi Dependency (*uses-a*), di mana kelas `Laptop` memanfaatkan kelas `Printer` hanya untuk sesaat melalui parameter method `cetakDokumen(Printer printer, String namaFile)`. Kelas `Laptop` sama sekali tidak menyimpan referensi ke `Printer` sebagai atribut.

### 2. Kode Program
- **`Printer.java`**
```java
package Jobsheet4.LaptopPrinter;

public class Printer {
    private String merk;

    public Printer(String merk) {
        this.merk = merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void cetak(String namaFile) {
        System.out.println("[" + merk + "] Mencetak " + namaFile + "...");
        System.out.println("[" + merk + "] Selesai.");
    }
}
```

- **`Laptop.java`**
```java
package Jobsheet4.LaptopPrinter;

public class Laptop {
    private String merk;

    public Laptop(String merk) {
        this.merk = merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void cetakDokumen(Printer printer, String namaFile) {
        System.out.println(merk + " mengirim dokumen ke printer...");
        printer.cetak(namaFile);
    }
}
```

- **`MainPercobaan6.java`**
```java
package Jobsheet4.LaptopPrinter;

public class MainPercobaan6 {
    public static void main(String[] args) {
        Laptop laptop = new Laptop("Thinkpad");
        Printer printer = new Printer("Epson L3110");
        laptop.cetakDokumen(printer, "Laporan.pdf");
    }
}
```

### 3. Output Eksekusi
```
Thinkpad mengirim dokumen ke printer...
[Epson L3110] Mencetak Laporan.pdf...
[Epson L3110] Selesai.
```

### 4. Jawaban Pertanyaan Percobaan 6
1. **Apakah class Laptop pada percobaan ini memiliki atribut bertipe Printer? Bandingkan dengan Percobaan 1, di mana Processor disimpan sebagai atribut Laptop.**  
   **Jawab:**  
   **Tidak**, kelas `Laptop` tidak memiliki atribut bertipe `Printer`. Berbeda dengan Percobaan 1 di mana `Processor` disimpan secara permanen sebagai atribut instans (`private Processor proc;`).

2. **Setelah method cetakDokumen() selesai dijalankan, apakah Laptop masih menyimpan referensi ke objek printer yang tadi dipakai? Jelaskan berdasarkan baris kode class Laptop.**  
   **Jawab:**  
   **Tidak**. Objek `printer` hanya berstatus sebagai variabel lokal/parameter pada method `cetakDokumen(Printer printer, String namaFile)`. Setelah method selesai dijalankan, masa hidup (*scope*) variabel tersebut berakhir dan referensinya dilepas dari *call stack*. Tidak ada atribut di dalam kelas `Laptop` yang menyimpannya.

3. **Mengapa relasi Laptop-Printer pada percobaan ini disebut Dependency (uses-a), bukan Aggregation, meskipun sama-sama melibatkan dua objek yang saling berinteraksi?**  
   **Jawab:**  
   Karena relasi yang terbentuk bersifat temporer (hanya saat pemanggilan method berlangsung). `Laptop` tidak memiliki (*has-a*) `Printer` sebagai bagian dari identitas/strukturnya, melainkan hanya memakai (*uses-a*) fungsionalitas `Printer` sesaat untuk mencetak dokumen.

4. **Coba ubah kode Laptop supaya Printer disimpan sebagai atribut (mis. private Printer printerDefault, diisi lewat constructor atau setter, lalu dipakai kembali di cetakDokumen() tanpa parameter Printer). Apakah relasi ini sekarang berubah dari Dependency menjadi Aggregation? Jelaskan.**  
   **Jawab:**  
   **Ya**, relasinya berubah menjadi **Aggregation**. Karena `Printer` sekarang disimpan secara persisten sebagai atribut kelas (*has-a*), diisi melalui injeksi konstruktor/setter dari luar, dan referensinya tetap tersimpan selama objek `Laptop` masih hidup.

5. **Lengkapi tabel berikut dengan kata-katamu sendiri:**
   | Kriteria Perbandingan | Aggregation | Composition | Dependency |
   | :--- | :--- | :--- | :--- |
   | **(a) Apakah objek part disimpan sebagai atribut?** | **Ya**, disimpan sebagai atribut (*has-a*), namun dengan kepemilikan longgar (*loose coupling*). | **Ya**, disimpan sebagai atribut (*has-a*) dengan kepemilikan mutlak dan eksklusif. | **Tidak**, tidak disimpan sebagai atribut. Hanya dilewatkan melalui parameter method atau dibuat lokal (*uses-a*). |
   | **(b) Siapa yang memanggil `new` untuk membuat objek part tersebut?** | Dibuat di **luar** kelas *whole* (oleh kode pemanggil / `Main`), lalu dimasukkan via constructor/setter injection. | Dibuat **di dalam** kelas *whole* itu sendiri (oleh konstruktor atau method internal milik *whole*). | Dibuat di **luar** kelas, lalu dipinjamkan sesaat sebagai parameter method saat dipanggil. |

---

## H. Kesimpulan

Dari keenam percobaan yang telah dipraktikkan, dapat ditarik kesimpulan fundamental mengenai relasi antar kelas dalam PBO:
1. **Aggregation** cocok digunakan ketika objek *part* dapat hidup mandiri di luar objek *whole* dan objek *part* tersebut dapat digunakan bersama atau dipindahkan ke objek lain.
2. **Composition** wajib digunakan saat objek *part* merupakan komponen esensial yang tidak memiliki arti atau kegunaan jika berdiri sendiri tanpa objek *whole*-nya, serta daur hidupnya dikontrol penuh oleh *whole*.
3. **Dependency** adalah opsi desain terbaik ketika suatu kelas hanya membutuhkan layanan dari kelas lain untuk menjalankan operasi tertentu sesaat saja, guna menghindari kopling (*coupling*) data atribut yang tidak perlu.
