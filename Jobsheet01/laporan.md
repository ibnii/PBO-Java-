# LAPORAN PRAKTIKUM
Nama    : Ibni Andarta<br/>
Kelas   : TI-2G<br/>
---
## PEMROGRAMAN BERORIENTASI OBJEK
**Jobsheet 01: Pengantar Konsep Pemrograman Berorientasi Objek**


---

### A. Data Praktikan
- **Mata Kuliah** : Pemrograman Berorientasi Objek
- **Topik** : Pengantar Konsep Pemrograman Berorientasi Objek
- **Waktu Pelaksanaan** : Praktikum 01

---

### B. Hasil Percobaan

#### 1. Percobaan 1: Pengenalan Class, Object, dan Method (Bike & BikeDemo)
Pada percobaan ini, dibuat class `Bike` yang merepresentasikan objek sepeda dengan atribut `brand`, `speed`, dan `gear`, serta method untuk akselerasi, deselerasi, perpindahan gear, dan mencetak informasi sepeda.

**Kode Program `Bike.java`:**
```java
package BikeDemo;

public class Bike {
    private String brand;
    private int speed;
    private int gear = 1;
    private final int[] GEAR_SPEED_LIMITS = {5, 10, 25, 30, 40, 60};

    public void setBrand(String brandName) {
        brand = brandName;
    }

    public void gearChanges(int gearValue) {
        if (gearValue < 1 || gearValue > 6) {
            System.out.println("Invalid gear value. Gear must be between 1 and 6.");
        } else {
            gear = gearValue;
        }
    }

    public int speedAcceleration(int increment) {
        speed += increment;
        if (speed > GEAR_SPEED_LIMITS[gear - 1]) {
            speed = GEAR_SPEED_LIMITS[gear - 1];
        }
        return speed;
    }

    public int speedDeceleration(int decrement) {
        speed -= decrement;
        if (speed < 0) {
            speed = 0;
        }
        return speed;
    }

    public void printInfo() {
        System.out.println("Brand : " + brand);
        System.out.println("Speed : " + speed);
        System.out.println("Gear  : " + gear);
    }
}
```

**Kode Program `BikeDemo.java` (Percobaan 1):**
```java
package BikeDemo;

public class BikeDemo {
    public static void main(String[] args) {
        Bike mountainBike1 = new Bike();
        Bike mountainBike2 = new Bike();

        mountainBike1.setBrand("Trek");
        mountainBike1.speedAcceleration(10);
        mountainBike1.gearChanges(2);
        mountainBike1.printInfo();

        mountainBike2.setBrand("Giant");
        mountainBike2.speedAcceleration(20);
        mountainBike2.gearChanges(3);
        mountainBike2.printInfo();
    }
}
```

**Output Percobaan 1:**
```text
Brand : Trek
Speed : 5
Gear  : 2
Brand : Giant
Speed : 5
Gear  : 3
```

---

#### 2. Percobaan 2: Pewarisan / Inheritance (`RoadBike` Turunan dari `Bike`)
Pada percobaan ini, diterapkan konsep pewarisan (*inheritance*) di mana class `RoadBike` mewarisi class `Bike` dengan kata kunci `extends`. Atribut dan method dari superclass (`Bike`) otomatis dimiliki oleh subclass (`RoadBike`), serta ditambahkan atribut baru `tireWidth` dan method overriding `printInfo()`.

**Kode Program `RoadBike.java`:**
```java
package BikeDemo;

public class RoadBike extends Bike {
    private int tireWidth;

    public void setTireWidth(int width) {
        tireWidth = width;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Tire Width : " + tireWidth + " mm");
        System.out.println("Bike Type  : Road Bike");
    }
}
```

**Kode Program `BikeDemo.java` (Percobaan 2 Lengkap):**
```java
package BikeDemo;

public class BikeDemo {
    public static void main(String[] args) {
        Bike mountainBike1 = new Bike();
        Bike mountainBike2 = new Bike();
        RoadBike roadBike1 = new RoadBike();

        mountainBike1.setBrand("Trek");
        mountainBike1.speedAcceleration(10);
        mountainBike1.gearChanges(2);
        mountainBike1.printInfo();

        mountainBike2.setBrand("Giant");
        mountainBike2.speedAcceleration(20);
        mountainBike2.gearChanges(3);
        mountainBike2.printInfo();

        roadBike1.setBrand("Specialized");
        roadBike1.setTireWidth(25);
        roadBike1.speedAcceleration(15);
        roadBike1.gearChanges(4);
        roadBike1.printInfo();
    }
}
```

**Output Percobaan 2:**
```text
Brand : Trek
Speed : 5
Gear  : 2
Brand : Giant
Speed : 5
Gear  : 3
Brand : Specialized
Speed : 5
Gear  : 4
Tire Width : 25 mm
Bike Type  : Road Bike
```

---

### C. Jawaban Pertanyaan Modul

#### **Pertanyaan 1:**
*Jelaskan perbedaan antara object dengan class!*

**Jawaban:**
- **Class** adalah *blueprint*, kerangka dasar, rancangan, atau prototipe yang mendefinisikan struktur data (atribut/variabel) dan perilaku (*behaviour*/method) yang akan dimiliki oleh objek. Class belum memakan alokasi memori untuk data riil karena hanya berupa definisi.
- **Object** adalah instansiasi (*instance*) nyata dari suatu class yang telah dibuat di memori (menggunakan kata kunci `new`). Objek memiliki nilai status (*state*) konkret pada atributnya dan dapat menjalankan aksi/method sesuai definisi yang ada pada class-nya.

---

#### **Pertanyaan 2:**
*Jelaskan alasan gear dan brand dapat menjadi atribut dari object Bike!*

**Jawaban:**
Dalam konsep PBO, atribut merepresentasikan karakteristik, ciri-ciri, atau *state* (keadaan) dari suatu objek di dunia nyata. `brand` (merek) dan `gear` (posisi gigi) adalah karakteristik esensial yang melekat serta mendeskripsikan kondisi fisik dan operasional dari sebuah sepeda (`Bike`). Nilai dari `brand` dan `gear` menentukan identitas serta performa kerja objek sepeda tersebut pada suatu waktu.

---

#### **Pertanyaan 3:**
*Sebutkan salah satu kelebihan utama dari pemrograman berorientasi objek dibandingkan dengan pemrograman prosedural!*

**Jawaban:**
Kelebihan utama PBO adalah **modularitas, fleksibilitas, dan kemudahan dalam pemeliharaan (*maintainability*) serta penggunaan kembali kode (*reusability*)**. 
Pada PBO, kode dibungkus ke dalam objek-objek mandiri. Jika terjadi perubahan atau penambahan fitur pada suatu komponen/class, perubahan tersebut tidak akan merusak keseluruhan sistem program secara global (berbeda dengan prosedural yang rentan terhadap *side-effect*). Selain itu, dengan adanya konsep *Inheritance*, kita dapat memperluas fungsionalitas tanpa harus menulis ulang kode dari awal.

---

#### **Pertanyaan 4:**
*Apakah diperbolehkan melakukan pendefinisian dua buah atribut dalam satu baris kode seperti `"public String nama, alamat;"`?*

**Jawaban:**
Boleh (secara sintaksis Java valid dan legal).
---

#### **Pertanyaan 5:**
*Pada class RoadBike, jelaskan alasan atribut brand, speed, dan gear tidak lagi ditulis di dalam class tersebut!*

**Jawaban:**
Karena class `RoadBike` telah mewarisi (*extends*) class `Bike` (Superclass). Melalui prinsip **Inheritance (Pewarisan)**, semua atribut dan method non-private (atau yang dapat diakses melalui setter/getter/method publik) dari superclass secara otomatis diwariskan ke subclass (`RoadBike`). Oleh karena itu, class `RoadBike` tidak perlu mendeklarasikan ulang atribut-atribut tersebut, melainkan cukup menambahkan atribut dan perilaku baru yang spesifik (seperti `tireWidth`). Hal ini mencegah terjadinya duplikasi kode (*redundancy*).

---

### D. Tugas Praktikum
#### 1. Class `Kasur.java` (Objek Mandiri)
```java
package TugasPraktikum;

public class Kasur {
    int panjang;
    int lebar;

    public Kasur(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    void panjangKasur(){
        System.out.println("Panjang = " + panjang);
    }

    void lebarKasur(){
        System.out.println("Lebar = " + lebar);
    }

    void LuasKasur(){
        System.out.println("Luas = " + (panjang*lebar));
    }
}
```
---
#### 2. Class Laptop.java (Superclass)
```package TugasPraktikum;

public class Laptop {
    String jenisLayar;
    String baterai = "kwh";
    
    public Laptop() {
        this.jenisLayar = "IPS";
    }

    void informasiLayar(){
        System.out.println("Jenis Layar\t: " + jenisLayar);
    }

    void on (){
        System.out.println("Laptop di hidupkan");
    }

    void off (){
        System.out.println("Laptop di matikan");
    }
}
```
---

#### 3. Class Hp.java (Subclass dari Laptop)
```package TugasPraktikum;
import java.util.Scanner;

public class Hp extends Laptop {
    private String tujuanTelfon;
    String touchScreenStatus = "Normal";

    Hp(){
        super();
        super.jenisLayar = "TN Panel";
    }

    public void setTujuanTelfon(String tujuanTelfon) {
        this.tujuanTelfon = tujuanTelfon;
    }

    void touchscreenCheck() {
        System.out.println("Touchscreen\t: " + touchScreenStatus);
    }

    void telfon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nomer tujuan : ");
        setTujuanTelfon(sc.nextLine());
        System.out.println(". . . . . . Menghubungi " + tujuanTelfon);
    }
}
```
---
#### 4. Class Komputer.java (Subclass dari Laptop)
``` package TugasPraktikum;

public class Komputer extends Laptop {
    String gpu;
    String setupStatus = "Setup Succesfully";

    public Komputer(String gpu) {
        super();
        super.jenisLayar = "OLED";
        this.gpu = gpu;
    }

    @Override
    void informasiLayar() {
        jenisLayar = "OLED";
        System.out.println("Informasi Layar\t: " + jenisLayar + " 100%SRGB");
    }

    void Gpu() {
        System.out.println("Gpu\t: " + gpu);
    }

    void setup() {
        System.out.println(setupStatus);
    }
}
```

#### Class Demo.java (Main Class)
``` package TugasPraktikum;

public class Demo {
    public static void main(String[] args) {
        Hp hp = new Hp();
        Kasur kasur = new Kasur(15, 10);
        Komputer komputer = new Komputer("Arc B580");
        Laptop laptop = new Laptop();
        
        System.out.println("========  HP  ========");
        hp.on();
        hp.off();
        hp.informasiLayar();
        hp.telfon();
        hp.touchscreenCheck();
        
        System.out.println("========  Kasur  ========");
        kasur.LuasKasur();
        kasur.lebarKasur();
        kasur.panjangKasur();
        
        System.out.println("========  Komputer  ========");
        komputer.on();
        komputer.off();
        komputer.informasiLayar();
        komputer.Gpu();
        komputer.setup();
        
        System.out.println("========  Laptop  ========");
        laptop.on();
        laptop.off();
        laptop.informasiLayar();
    }
}
```

---

### E. Kesimpulan
1. **Pemrograman Berorientasi Objek (PBO)** menstrukturkan program ke dalam kesatuan unit objek yang membungkus *state* (atribut) dan *behaviour* (method), sehingga arsitektur aplikasi lebih modular, mudah dipelihara, dan skalabel dibandingkan paradigma prosedural.
2. **Class** bertindak sebagai cetak biru (*blueprint*), sedangkan **Object** merupakan wujud nyata hasil instansiasi dari class tersebut di memori.
3. Konsep **Inheritance (Pewarisan)** memungkinkan subclass mewarisi sifat dan perilaku dari superclass tanpa duplikasi kode, sekaligus membuka peluang penambahan fitur baru atau spesialisasi method melalui mekanisme *method overriding*.
