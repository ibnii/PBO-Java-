# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK
## Pertemuan 9: Polimorfisme

---

### Identitas Mahasiswa
- **Nama** : Ibni Andarta
- **Kelas** : TI-2G
- **NIM** : 254107020258
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek (RTI253008)
- **Jurusan** : Teknologi Informasi - Politeknik Negeri Malang

---

## A. Ringkasan Materi & Teori

**Polimorfisme** berarti satu objek dapat dipandang dalam banyak bentuk. Bentuk paling umum adalah variabel bertipe **superclass** atau **interface** yang dipakai untuk menunjuk objek dari **subclass** (relasi *is-a*) atau objek dari class yang meng-implements interface tersebut.

| Konsep | Penjelasan |
| :--- | :--- |
| **Tipe referensi** | Tipe yang tertulis pada deklarasi variabel. Ditentukan saat **kompilasi** dan menentukan anggota (field/method) apa yang boleh dipanggil. |
| **Tipe objek** | Class sebenarnya dari instance yang ditunjuk referensi. Ditentukan saat **runtime** dan menentukan versi method hasil overriding yang dijalankan. |
| **Upcasting** | Konversi dari subclass ke superclass/interface. Terjadi **otomatis** dan selalu aman (`Employee e = pEmp;`). |
| **Downcasting** | Konversi dari superclass/interface ke subclass. Harus **eksplisit** dengan tanda kurung dan hanya aman bila objek aslinya memang bertipe tujuan (`(PermanentEmployee) e`). |
| **Virtual method invocation** | Pemanggilan method hasil overriding melalui referensi polimorfik; versi method dipilih JVM berdasarkan tipe objek (*dynamic method dispatch*). |
| **Heterogeneous collection** | Array/koleksi bertipe superclass atau interface yang elemennya boleh berupa berbagai objek turunan/implementor. |
| **Polymorphic argument** | Parameter method bertipe superclass/interface sehingga satu method dapat menerima berbagai jenis objek. |
| **`instanceof`** | Operator untuk memeriksa tipe objek sebenarnya sebelum melakukan downcasting. |
| **Interface** | Kontrak berisi method tanpa isi; method-nya otomatis `public abstract`; satu class boleh meng-implements lebih dari satu interface. |
| **Abstract class** | Class yang tidak dapat diinstansiasi; method abstract wajib di-override oleh subclass konkret. |

Kaidah penting yang diuji pada jobsheet ini:

1. Referensi bertipe `T` boleh menunjuk objek bertipe `T` (bila `T` class), turunan `T` (subclass), atau class yang meng-implements `T` (bila `T` interface).
2. **Tipe referensi menentukan apa yang boleh dipanggil**, sedangkan **tipe objek menentukan method mana yang dijalankan**.
3. Overloading dipilih saat **kompilasi** (Jobsheet 7), sedangkan overriding melalui referensi polimorfik dipilih saat **runtime** (Jobsheet 9).

---

## B. Percobaan 1: Bentuk Dasar Polimorfisme

### 1. Deskripsi Singkat
Percobaan pertama memakai package `gambaran` untuk melihat assignment referensi yang valid dan tidak valid pada hierarki `Animal`/`Deer`/`Lion` dan interface `Vegetarian`. Selanjutnya, studi kasus pegawai pada package `pegawai` dipakai untuk menguji referensi bertipe `Employee` (superclass) dan `Payable` (interface).

Hierarki studi kasus:

- `PermanentEmployee` adalah sebuah `Employee` **sekaligus** sebuah `Payable`.
- `InternshipEmployee` hanya sebuah `Employee` (tidak digaji).
- `ElectricityBill` hanya sebuah `Payable` (bukan `Employee`).

### 2. Kode Program

- **`GambaranDasar.java`** (`gambaran/`)
```java
package gambaran;

public class GambaranDasar {
    interface Vegetarian {
    }

    static class Animal {
    }

    static class Deer extends Animal implements Vegetarian {
    }

    static class Lion extends Animal {
    }

    public static void main(String[] args) {
        Deer d = new Deer();
        Lion l = new Lion();
        Animal a = d; // valid: Deer adalah Animal
        Animal a2 = l; // valid: Lion adalah Animal
        Vegetarian v = d; // valid: Deer implements Vegetarian
        // Vegetarian v2 = l; // tidak valid: Lion tidak implements Vegetarian

        System.out.println("a menunjuk objek " + a.getClass().getSimpleName());
        System.out.println("a2 menunjuk objek " + a2.getClass().getSimpleName());
        System.out.println("v menunjuk objek " + v.getClass().getSimpleName());
    }
}
```

- **`Employee.java`**
```java
package pegawai;

public class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getEmployeeInfo() {
        return "Name = " + name;
    }
}
```

- **`Payable.java`**
```java
package pegawai;

public interface Payable {
    int getPaymentAmount();
}
```

- **`InternshipEmployee.java`**
```java
package pegawai;

public class InternshipEmployee extends Employee {
    private int length;

    public InternshipEmployee(String name, int length) {
        super(name);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getEmployeeInfo() {
        String info = super.getEmployeeInfo() + "\n";
        info += "Registered as internship employee for " + length + " month/s\n";
        return info;
    }
}
```

- **`PermanentEmployee.java`**
```java
package pegawai;

public class PermanentEmployee extends Employee implements Payable {
    private int salary;

    public PermanentEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int getPaymentAmount() {
        return (int) (salary + 0.05 * salary); // gaji + bonus 5%
    }

    @Override
    public String getEmployeeInfo() {
        String info = super.getEmployeeInfo() + "\n";
        info += "Registered as permanent employee with salary " + salary + "\n";
        return info;
    }
}
```

- **`ElectricityBill.java`**
```java
package pegawai;

public class ElectricityBill implements Payable {
    private int kwh;
    private String category;

    public ElectricityBill(int kwh, String category) {
        this.kwh = kwh;
        this.category = category;
    }

    public int getKwh() {
        return kwh;
    }

    public void setKwh(int kwh) {
        this.kwh = kwh;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getPaymentAmount() {
        return kwh * getBasePrice();
    }

    public int getBasePrice() {
        return switch (category) {
            case "R-1" -> 100;
            case "R-2" -> 200;
            default -> 0;
        };
    }

    public String getBillInfo() {
        return "kWH = " + kwh + "\n"
                + "Category = " + category + " (" + getBasePrice() + " per kWH)\n";
    }
}
```

- **`MainPercobaan1.java`**
```java
package pegawai;

public class MainPercobaan1 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "R-1");
        Employee e;
        Payable p;

        e = pEmp;
        System.out.println("e menunjuk objek " + e.getClass().getSimpleName());
        e = iEmp;
        System.out.println("e menunjuk objek " + e.getClass().getSimpleName());

        p = pEmp;
        System.out.println("p menunjuk objek " + p.getClass().getSimpleName());
        p = eBill;
        System.out.println("p menunjuk objek " + p.getClass().getSimpleName());
    }
}
```

### 3. Output Eksekusi

- **`GambaranDasar`**
```
a menunjuk objek Deer
a2 menunjuk objek Lion
v menunjuk objek Deer
```

- **`MainPercobaan1`**
```
e menunjuk objek PermanentEmployee
e menunjuk objek InternshipEmployee
p menunjuk objek PermanentEmployee
p menunjuk objek ElectricityBill
```

### 4. Tabel Pengamatan Eksperimen

| Perubahan | Pesan error |
| :--- | :--- |
| Aktifkan `Vegetarian v2 = l;` | `GambaranDasar.java:22: error: incompatible types: Lion cannot be converted to Vegetarian` |
| Tambahkan `p = iEmp;` | `MainPercobaan1.java:20: error: incompatible types: InternshipEmployee cannot be converted to Payable` |
| Tambahkan `e = eBill;` | `MainPercobaan1.java:21: error: incompatible types: ElectricityBill cannot be converted to Employee` |

### 5. Jawaban Pertanyaan Percobaan 1

1. **Class apa saja yang merupakan turunan dari class Employee?**  
   **Jawab:** `PermanentEmployee` dan `InternshipEmployee`.

2. **Class apa saja yang meng-implements interface Payable?**  
   **Jawab:** `PermanentEmployee` dan `ElectricityBill`.

3. **Pada MainPercobaan1, mengapa e dapat diisi dengan objek pEmp (PermanentEmployee) dan iEmp (InternshipEmployee)?**  
   **Jawab:** Karena `e` bertipe `Employee`, sedangkan `PermanentEmployee` dan `InternshipEmployee` keduanya merupakan **subclass** dari `Employee` (relasi *is-a*). Assignment dari subclass ke superclass adalah **upcasting** yang terjadi otomatis dan selalu valid.

4. **Pada MainPercobaan1, mengapa p dapat diisi dengan objek pEmp (PermanentEmployee) dan eBill (ElectricityBill)?**  
   **Jawab:** Karena `p` bertipe `Payable`, dan kedua class tersebut sama-sama **meng-implements** interface `Payable`. Apa pun class-nya, selama memenuhi kontrak `Payable` (mengisi `getPaymentAmount()`), objeknya boleh ditunjuk referensi `Payable`.

5. **Apa penyebab error pada `p = iEmp;` dan `e = eBill;`? Bandingkan dengan error pada `Vegetarian v2 = l;`.**  
   **Jawab:** Ketiganya adalah error **incompatible types** yang muncul saat kompilasi karena tidak ada relasi *is-a* antara tipe sumber dan tipe tujuan:
   - `p = iEmp;` → `InternshipEmployee cannot be converted to Payable`, sebab `InternshipEmployee` tidak meng-implements `Payable`.
   - `e = eBill;` → `ElectricityBill cannot be converted to Employee`, sebab `ElectricityBill` bukan turunan `Employee`.
   - `Vegetarian v2 = l;` → `Lion cannot be converted to Vegetarian`, sebab `Lion` tidak meng-implements `Vegetarian`.  
   Pola errornya identik: compiler memeriksa apakah tipe sumber merupakan subtype (turunan/implementor) dari tipe referensi tujuan.

6. **Pada GambaranDasar, variabel a bertipe Animal, tetapi getClass() menampilkan Deer. Apa beda tipe referensi dan tipe objek?**  
   **Jawab:** **Tipe referensi** adalah tipe yang dideklarasikan (`Animal`), ditentukan saat kompilasi dan membatasi anggota apa yang boleh dipanggil. **Tipe objek** adalah class sebenarnya dari instance di heap (`Deer`), ditentukan saat runtime dan menentukan method hasil overriding yang dieksekusi. `getClass()` selalu membaca tipe objek, sehingga menampilkan `Deer` walaupun referensinya `Animal`.

7. **Ambil kesimpulan tentang konsep dan bentuk dasar polimorfisme.**  
   **Jawab:** Polimorfisme memungkinkan satu referensi bertipe superclass/interface menunjuk objek dari berbagai subclass/implementor. Bentuk dasarnya adalah pewarisan (`extends`) dan kontrak interface (`implements`), yang membuat assignment ke tipe yang lebih umum valid secara otomatis (*upcasting*). Compiler hanya menerima assignment bila terdapat relasi subtype; objek tetap membawa identitas aslinya, sehingga perilaku method dapat berbeda meskipun dipanggil melalui referensi yang sama.

---

## C. Percobaan 2: Virtual Method Invocation

### 1. Deskripsi Singkat
Percobaan ini menguji method mana yang dijalankan ketika referensi bertipe superclass (`Employee`) menunjuk objek subclass (`PermanentEmployee`/`InternshipEmployee`) yang meng-override `getEmployeeInfo()`.

### 2. Kode Program

- **`MainPercobaan2.java`**
```java
package pegawai;

public class MainPercobaan2 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        Employee e;
        e = pEmp;

        System.out.println(e.getEmployeeInfo());
        System.out.println("------------------");
        System.out.println(pEmp.getEmployeeInfo());

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        e = iEmp;
        System.out.println("------------------");
        System.out.println(e.getEmployeeInfo()); // pernyataan sama, objek berbeda
    }
}
```

### 3. Output Eksekusi
```
Name = Dedik
Registered as permanent employee with salary 500

------------------
Name = Dedik
Registered as permanent employee with salary 500

------------------
Name = Sunarto
Registered as internship employee for 5 month/s

```

### 4. Tabel Pengamatan Eksperimen

| Perubahan | Pesan error |
| :--- | :--- |
| Tambahkan `System.out.println(e.getSalary());` setelah `e = iEmp;` | `MainPercobaan2.java:15: error: cannot find symbol`<br>`symbol: method getSalary()`<br>`location: variable e of type Employee` |

### 5. Jawaban Pertanyaan Percobaan 2

1. **Pada langkah 1, mengapa `e.getEmployeeInfo()` dan `pEmp.getEmployeeInfo()` menghasilkan hasil yang sama?**  
   **Jawab:** Karena keduanya menunjuk **objek yang sama**. `e = pEmp;` hanya menyalin referensi, bukan membuat objek baru. Method yang dijalankan tetap `PermanentEmployee.getEmployeeInfo()`.

2. **Mengapa pemanggilan `e.getEmployeeInfo()` disebut pemanggilan method virtual, sedangkan `pEmp.getEmployeeInfo()` tidak?**  
   **Jawab:** Pada `e`, compiler hanya tahu tipe referensinya `Employee`, sehingga versi method belum pasti dan baru dipilih JVM berdasarkan objek sebenarnya (*dynamic dispatch*) — inilah pemanggilan virtual. Pada `pEmp`, tipe referensi sudah persis `PermanentEmployee`, sehingga tidak ada pilihan versi method yang perlu ditentukan secara dinamis; hasilnya sudah pasti versi milik `PermanentEmployee`.

3. **Jelaskan maksud virtual method invocation. Mengapa disebut *virtual*?**  
   **Jawab:** *Virtual method invocation* adalah pemanggilan method hasil overriding melalui referensi polimorfik, di mana method yang **dikenali compiler** (berdasarkan tipe referensi) berbeda dari method yang **dijalankan JVM** (berdasarkan tipe objek). Disebut *virtual* karena pemilihan versi method tidak ditetapkan saat kompilasi, melainkan baru ditentukan saat program berjalan melalui *dynamic method dispatch*.

4. **Pada langkah 3, mengapa pernyataan `e.getEmployeeInfo()` yang sama menghasilkan keluaran berbeda?**  
   **Jawab:** Karena `e` sekarang menunjuk objek `InternshipEmployee` (`e = iEmp;`). Pernyataan pemanggilannya sama, tetapi tipe objek berubah, sehingga JVM menjalankan `InternshipEmployee.getEmployeeInfo()` yang menghasilkan teks magang.

5. **Pada langkah 4, mengapa `e.getSalary()` ditolak compiler padahal objek yang ditunjuk e adalah PermanentEmployee?**  
   **Jawab:** Compiler hanya memeriksa **tipe referensi**. `e` bertipe `Employee`, dan `Employee` tidak memiliki method `getSalary()`, sehingga pemanggilan ditolak dengan `cannot find symbol`. Fakta bahwa objeknya `PermanentEmployee` baru diketahui saat runtime. Agar bisa memanggil `getSalary()`, referensi harus di-downcast secara aman, misalnya `if (e instanceof PermanentEmployee pe) { pe.getSalary(); }`.

---

## D. Percobaan 3: Heterogeneous Collection

### 1. Deskripsi Singkat
Percobaan ini menyimpan objek-objek berbeda jenis dalam satu array bertipe superclass (`Employee[]`) dan satu array bertipe interface (`Payable[]`), lalu memanggil method yang sama pada setiap elemen tanpa memeriksa jenis objeknya.

### 2. Kode Program

- **`MainPercobaan3.java`**
```java
package pegawai;

public class MainPercobaan3 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "R-1");

        Employee[] e = { pEmp, iEmp };
        Payable[] p = { pEmp, eBill };
        // Employee[] e2 = { pEmp, iEmp, eBill }; // tidak valid

        for (Employee emp : e) {
            System.out.println(emp.getEmployeeInfo());
        }

        int total = 0;
        for (Payable tagihan : p) {
            total += tagihan.getPaymentAmount();
        }
        System.out.println("Total tagihan bulan ini = " + total);
    }
}
```

### 3. Output Eksekusi
```
Name = Dedik
Registered as permanent employee with salary 500

Name = Sunarto
Registered as internship employee for 5 month/s

Total tagihan bulan ini = 1025
```

Perhitungan total: `PermanentEmployee.getPaymentAmount()` = 500 + 5% × 500 = **525**, `ElectricityBill.getPaymentAmount()` = 5 kWH × 100 = **500**, sehingga 525 + 500 = **1025**.

### 4. Tabel Pengamatan Eksperimen

| Perubahan | Pesan error |
| :--- | :--- |
| Aktifkan `Employee[] e2 = { pEmp, iEmp, eBill };` | `MainPercobaan3.java:11: error: incompatible types: ElectricityBill cannot be converted to Employee` |

### 5. Jawaban Pertanyaan Percobaan 3

1. **Mengapa array e dapat berisi objek pEmp (PermanentEmployee) dan iEmp (InternshipEmployee) yang bertipe berbeda?**  
   **Jawab:** Karena array dideklarasikan bertipe `Employee[]`, dan setiap elemen harus merupakan `Employee` atau turunannya. Kedua objek tersebut adalah subclass `Employee`, sehingga masing-masing mengalami upcasting otomatis saat dimasukkan ke array.

2. **Mengapa array p dapat berisi objek pEmp (PermanentEmployee) dan eBill (ElectricityBill) yang bertipe berbeda?**  
   **Jawab:** Karena array bertipe `Payable[]`, dan elemennya hanya perlu memenuhi kontrak `Payable`. `PermanentEmployee` maupun `ElectricityBill` sama-sama meng-implements `Payable`, meskipun keduanya tidak berada dalam satu hierarki class.

3. **Mengapa terjadi error pada `Employee[] e2 = { pEmp, iEmp, eBill };`?**  
   **Jawab:** Karena `ElectricityBill` bukan turunan `Employee` (tidak punya relasi *is-a* dengan `Employee`). Compiler menolaknya dengan `incompatible types: ElectricityBill cannot be converted to Employee`. Interface `Payable` tidak membuat sebuah objek otomatis menjadi `Employee`.

4. **Pada perulangan `for (Employee emp : e)`, mengapa setiap elemen menghasilkan teks yang berbeda padahal pemanggilannya sama? Hubungkan dengan Percobaan 2.**  
   **Jawab:** Variabel loop `emp` bertipe referensi `Employee`, tetapi tipe objek tiap elemen berbeda (`PermanentEmployee` dan `InternshipEmployee`). Saat `emp.getEmployeeInfo()` dipanggil, JVM melakukan *dynamic method dispatch* dan memilih override sesuai objek sebenarnya. Ini fenomena yang sama dengan Percobaan 2: satu pernyataan, versi method mengikuti tipe objek.

---

## E. Percobaan 4: Polymorphic Argument, instanceof, dan Casting

### 1. Deskripsi Singkat
Class `Owner` memiliki dua method dengan parameter polimorfik: `pay(Payable p)` menerima pegawai tetap maupun tagihan listrik, dan `showMyEmployee(Employee e)` menerima semua jenis pegawai. Di dalamnya digunakan `instanceof` dan downcasting untuk mengakses anggota spesifik subclass.

### 2. Kode Program

- **`Owner.java`**
```java
package pegawai;

public class Owner {
    public void pay(Payable p) {
        System.out.println("Total payment = " + p.getPaymentAmount());
        if (p instanceof ElectricityBill) {
            ElectricityBill eb = (ElectricityBill) p;
            System.out.println(eb.getBillInfo());
        } else if (p instanceof PermanentEmployee) {
            PermanentEmployee pe = (PermanentEmployee) p;
            System.out.println(pe.getEmployeeInfo());
        }
    }

    public void showMyEmployee(Employee e) {
        System.out.println(e.getEmployeeInfo());
        if (e instanceof PermanentEmployee) {
            System.out.println("You have to pay her/him monthly!!!");
        } else {
            System.out.println("No need to pay him/her :)");
        }
    }
}
```

- **`MainPercobaan4.java`**
```java
package pegawai;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Owner ow = new Owner();

        ElectricityBill eBill = new ElectricityBill(5, "R-1");
        ow.pay(eBill); // bayar tagihan listrik
        System.out.println("------------------------------");

        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        ow.pay(pEmp); // bayar pegawai tetap
        System.out.println("------------------------------");

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ow.showMyEmployee(pEmp); // info pegawai tetap
        System.out.println("------------------------------");
        ow.showMyEmployee(iEmp); // info pegawai magang
    }
}
```

### 3. Output Eksekusi
```
Total payment = 500
kWH = 5
Category = R-1 (100 per kWH)

------------------------------
Total payment = 525
Name = Dedik
Registered as permanent employee with salary 500

------------------------------
Name = Dedik
Registered as permanent employee with salary 500

You have to pay her/him monthly!!!
------------------------------
Name = Sunarto
Registered as internship employee for 5 month/s

No need to pay him/her :)
```

### 4. Tabel Pengamatan Eksperimen

| Perubahan | Hasil |
| :--- | :--- |
| Tambahkan `ow.pay(iEmp);` di akhir main pada `MainPercobaan4` | **Error kompilasi:** `MainPercobaan4.java:19: error: incompatible types: InternshipEmployee cannot be converted to Payable` |
| Buat class `Eksperimen4` lalu jalankan | **Lolos kompilasi, gagal saat runtime:** `Exception in thread "main" java.lang.ClassCastException: class InternshipEmployee cannot be cast to class PermanentEmployee` pada baris `PermanentEmployee pe = (PermanentEmployee) e;`. Teks `Sesudah casting` tidak pernah tercetak. |

Kode `Eksperimen4` yang diuji:
```java
package pegawai;

public class Eksperimen4 {
    public static void main(String[] args) {
        Employee e = new InternshipEmployee("Sunarto", 5);
        System.out.println("Sebelum casting");
        PermanentEmployee pe = (PermanentEmployee) e;
        System.out.println("Sesudah casting: " + pe.getSalary());
    }
}
```

### 5. Jawaban Pertanyaan Percobaan 4

1. **Pada MainPercobaan4, mengapa `ow.pay(eBill)` dan `ow.pay(pEmp)` dapat dilakukan, padahal parameter `pay()` bertipe Payable?**  
   **Jawab:** Karena `ElectricityBill` dan `PermanentEmployee` sama-sama **meng-implements** `Payable`. Mengirim objek implementor ke parameter bertipe interface adalah upcasting otomatis yang valid. Method `pay()` hanya membutuhkan jaminan bahwa objek memiliki `getPaymentAmount()`.

2. **Apa tujuan membuat parameter bertipe Payable pada method `pay()` di class Owner?**  
   **Jawab:** Agar satu method dapat menangani **semua jenis tagihan** (gaji pegawai tetap maupun rekening listrik) tanpa membuat method terpisah untuk tiap tipe. Owner cukup bergantung pada kontrak `Payable`, sehingga kode lebih fleksibel dan mudah diperluas (*polymorphic argument*).

3. **Mengapa `ow.pay(iEmp)` menyebabkan error?**  
   **Jawab:** Karena `InternshipEmployee` hanya turunan `Employee` dan **tidak meng-implements `Payable`** (pegawai magang tidak menerima gaji). Compiler menolak dengan `incompatible types: InternshipEmployee cannot be converted to Payable`.

4. **Pada Owner, untuk apa sintaks `p instanceof ElectricityBill` diperlukan?**  
   **Jawab:** Untuk memeriksa **tipe objek sebenarnya** sebelum melakukan downcasting. Dengan begitu, casting hanya dilakukan ketika objek benar-benar `ElectricityBill`, sehingga aman dari `ClassCastException`, sekaligus menentukan informasi spesifik mana yang akan dicetak (`getBillInfo()` atau `getEmployeeInfo()`).

5. **Pada Owner, untuk apa casting `(ElectricityBill) p` diperlukan? Mengapa objek p bertipe Payable harus di-cast ke eb bertipe ElectricityBill?**  
   **Jawab:** Tipe referensi `Payable` hanya mengenal `getPaymentAmount()`. Method `getBillInfo()` tidak ada di interface `Payable`, melainkan hanya dimiliki `ElectricityBill`. Downcasting mengubah **tipe referensi** (bukan objeknya) menjadi `ElectricityBill` agar compiler mengizinkan pemanggilan `eb.getBillInfo()`.

6. **Pada Eksperimen4, mengapa kode lolos kompilasi tetapi gagal saat dijalankan? Bagaimana `instanceof` mencegah kegagalan tersebut?**  
   **Jawab:** Compiler hanya memeriksa bahwa casting **masuk akal secara hierarki**: `PermanentEmployee` adalah subclass `Employee`, jadi `(PermanentEmployee) e` boleh ditulis. Kecocokan objek sebenarnya baru diperiksa JVM saat runtime; karena objeknya `InternshipEmployee` (bukan `PermanentEmployee`), JVM melempar `ClassCastException`. Pemeriksaan `if (e instanceof PermanentEmployee)` lebih dulu memastikan objek benar-benar bertipe tujuan, sehingga casting hanya dijalankan saat aman.

---

## F. Tugas Mandiri 1: Permainan Zombie, Barrier, dan Plant

### 1. Deskripsi Singkat
Package `zombie` berisi interface `Destroyable`, abstract class `Zombie`, dua subclass (`WalkingZombie` dan `JumpingZombie`), serta `Barrier` dan `Plant`. `Zombie` meng-implements `Destroyable`, tetapi karena belum mengisi `destroyed()`, kewajiban tersebut diteruskan ke subclass konkret. `Plant.doDestroy(Destroyable d)` hanya memanggil `d.destroyed()`, sehingga dapat menghancurkan zombie maupun barrier.

Aturan perhitungan (semua persentase memakai pembagian bilangan bulat, dibulatkan ke bawah):

| Class | `heal()` | `destroyed()` |
| :--- | :--- | :--- |
| `WalkingZombie` | level 1: +10%, level 2: +30%, level 3: +40% | `health -= health * 20 / 100` |
| `JumpingZombie` | level 1: +30%, level 2: +40%, level 3: +50% | `health -= health * 10 / 100` |
| `Barrier` | tidak ada | `strength = strength * 9 / 10` |

Penyembuhan tidak boleh melebihi 100 (memakai `Math.min`), dan level di luar 1–3 tidak mengubah health.

### 2. Kode Program

- **`Destroyable.java`**
```java
package zombie;

public interface Destroyable {
    void destroyed();
}
```

- **`Zombie.java`**
```java
package zombie;

public abstract class Zombie implements Destroyable {
    protected int health;
    protected int level;

    public Zombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void heal();

    @Override
    public abstract void destroyed();

    public String getZombieInfo() {
        return "Health = " + health + "\n"
                + "Level = " + level + "\n";
    }
}
```

- **`WalkingZombie.java`**
```java
package zombie;

public class WalkingZombie extends Zombie {
    public WalkingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        switch (level) {
            case 1 -> health = Math.min(100, health + health * 10 / 100);
            case 2 -> health = Math.min(100, health + health * 30 / 100);
            case 3 -> health = Math.min(100, health + health * 40 / 100);
            default -> {
            }
        }
    }

    @Override
    public void destroyed() {
        health -= health * 20 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Data = \n" + super.getZombieInfo();
    }
}
```

- **`JumpingZombie.java`**
```java
package zombie;

public class JumpingZombie extends Zombie {
    public JumpingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        switch (level) {
            case 1 -> health = Math.min(100, health + health * 30 / 100);
            case 2 -> health = Math.min(100, health + health * 40 / 100);
            case 3 -> health = Math.min(100, health + health * 50 / 100);
            default -> {
            }
        }
    }

    @Override
    public void destroyed() {
        health -= health * 10 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Data = \n" + super.getZombieInfo();
    }
}
```

- **`Barrier.java`**
```java
package zombie;

public class Barrier implements Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void destroyed() {
        strength = strength * 9 / 10;
    }

    public String getBarrierInfo() {
        return "Barrier Strength = " + strength + "\n";
    }
}
```

- **`Plant.java`**
```java
package zombie;

public class Plant {
    public void doDestroy(Destroyable d) {
        d.destroyed();
    }
}
```

- **`MainTugas1.java`**
```java
package zombie;

public class MainTugas1 {
    public static void main(String[] args) {
        WalkingZombie wz = new WalkingZombie(100, 1);
        JumpingZombie jz = new JumpingZombie(100, 2);
        Barrier b = new Barrier(100);
        Plant p = new Plant();

        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
        System.out.println("----------------------");

        for (int i = 0; i < 4; i++) { // hancurkan 4 kali
            p.doDestroy(wz);
            p.doDestroy(jz);
            p.doDestroy(b);
        }

        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
    }
}
```

### 3. Output Eksekusi
```
Walking Zombie Data = 
Health = 100
Level = 1

Jumping Zombie Data = 
Health = 100
Level = 2

Barrier Strength = 100

----------------------
Walking Zombie Data = 
Health = 42
Level = 1

Jumping Zombie Data = 
Health = 66
Level = 2

Barrier Strength = 64

```

Perhitungan setelah 4 kali penghancuran:
- `WalkingZombie`: 100 → 80 → 64 → 52 → **42** (setiap tahap mengurangi 20% dari nilai saat itu, dibulatkan ke bawah).
- `JumpingZombie`: 100 → 90 → 81 → 73 → **66** (setiap tahap mengurangi 10%).
- `Barrier`: 100 → 90 → 81 → 72 → **64** (setiap tahap dikali 9/10).

### 4. Jawaban Pertanyaan Analisis

**(a) Mengapa `Plant.doDestroy()` dapat menerima WalkingZombie, JumpingZombie, dan Barrier padahal ketiganya tidak berada dalam satu hierarki class?**  
**Jawab:** Karena parameter `doDestroy()` bertipe **interface `Destroyable`**, bukan class tertentu. Ketiga objek sama-sama meng-implements `Destroyable`, sehingga ketiganya memenuhi kontrak `destroyed()` dan boleh ditunjuk referensi `Destroyable` (upcasting otomatis). Polimorfisme melalui interface tidak memerlukan hubungan pewarisan antar-class.

**(b) Pada `d.destroyed()` di dalam Plant, apa tipe referensi dan tipe objek, dan versi `destroyed()` mana yang dijalankan untuk tiap pemanggilan di MainTugas1?**  
**Jawab:** Tipe referensinya adalah `Destroyable` (ditentukan saat kompilasi), sedangkan tipe objeknya berubah-ubah sesuai pemanggilan:
- `p.doDestroy(wz)` → objek `WalkingZombie`, menjalankan `WalkingZombie.destroyed()` (health −20%).
- `p.doDestroy(jz)` → objek `JumpingZombie`, menjalankan `JumpingZombie.destroyed()` (health −10%).
- `p.doDestroy(b)` → objek `Barrier`, menjalankan `Barrier.destroyed()` (strength × 9/10).  
  Pemilihan ini adalah *virtual method invocation* / *dynamic method dispatch*.

**(c) Apa yang terjadi bila Anda menulis `new Zombie(100, 1)`? Tuliskan pesan errornya.**  
**Jawab:** Terjadi **error kompilasi** karena `Zombie` adalah abstract class dan tidak dapat diinstansiasi:
```
ModZombie.java:5: error: Zombie is abstract; cannot be instantiated
        Zombie z = new Zombie(100, 1);
                   ^
1 error
```

---

## G. Tugas Mandiri 2: Heterogeneous Collection, instanceof, dan Casting

### 1. Deskripsi Singkat
Satu `WalkingZombie`, satu `JumpingZombie`, dan satu `Barrier` disimpan dalam array bertipe `Destroyable`. Semua elemen dihancurkan 4 kali melalui `Plant`, lalu array ditelusuri: elemen `Zombie` di-cast ke `Zombie`, di-`heal()`, dan dicetak; elemen `Barrier` di-cast ke `Barrier` lalu dicetak.

### 2. Kode Program

- **`MainTugas2.java`**
```java
package zombie;

public class MainTugas2 {
    public static void main(String[] args) {
        Destroyable[] destroyables = {
                new WalkingZombie(100, 1),
                new JumpingZombie(100, 2),
                new Barrier(100)
        };
        Plant p = new Plant();

        for (int i = 0; i < 4; i++) {
            for (Destroyable d : destroyables) {
                p.doDestroy(d);
            }
        }

        for (Destroyable d : destroyables) {
            if (d instanceof Zombie) {
                Zombie z = (Zombie) d;
                z.heal();
                System.out.println(z.getZombieInfo());
            } else if (d instanceof Barrier) {
                Barrier b = (Barrier) d;
                System.out.println(b.getBarrierInfo());
            }
        }
    }
}
```

### 3. Output Eksekusi
```
Walking Zombie Data = 
Health = 46
Level = 1

Jumping Zombie Data = 
Health = 92
Level = 2

Barrier Strength = 64

```

Perhitungan: setelah 4 kali dihancurkan, health `WalkingZombie` = 42 lalu `heal()` level 1 menambah 10% × 42 = 4 → **46**. Health `JumpingZombie` = 66 lalu `heal()` level 2 menambah 40% × 66 = 26 (26,4 dibulatkan ke bawah) → **92**. `Barrier` tetap **64** karena tidak memiliki `heal()`.

### 4. Jawaban Pertanyaan Analisis

**(a) Mengapa array harus bertipe Destroyable, bukan Zombie?**  
**Jawab:** Karena isi array bersifat **heterogen**: ada `Zombie` dan ada `Barrier` yang **tidak memiliki hubungan pewarisan** dengan `Zombie`. Satu-satunya tipe bersama yang dapat menampung keduanya adalah interface `Destroyable` yang di-implements oleh keduanya. Jika array bertipe `Zombie[]`, objek `Barrier` tidak dapat dimasukkan.

**(b) Mengapa casting ke Zombie diperlukan sebelum memanggil `heal()`?**  
**Jawab:** Karena `heal()` dideklarasikan di abstract class `Zombie`, bukan di interface `Destroyable`. Referensi `d` bertipe `Destroyable` hanya mengenal `destroyed()`, sehingga compiler menolak `d.heal()`. Setelah dipastikan dengan `instanceof Zombie`, downcasting `(Zombie) d` mengubah tipe referensi agar `heal()` dan `getZombieInfo()` dapat dipanggil.

**(c) Apa yang terjadi bila pemeriksaan `instanceof` dihilangkan dan elemen Barrier di-cast ke Zombie?**  
**Jawab:** Kode **lolos kompilasi** karena casting dari interface `Destroyable` ke class `Zombie` masih masuk akal secara tipe (keduanya bisa dihubungkan). Namun saat runtime, elemen `Barrier` bukanlah `Zombie`, sehingga JVM melempar:
```
Exception in thread "main" java.lang.ClassCastException:
class Barrier cannot be cast to class Zombie
```
Pemeriksaan `instanceof` mencegah pemanggilan `heal()` pada objek yang tidak memiliki method tersebut.

---

## H. Tugas Mandiri 3: Prediksi Hasil Kompilasi

Prediksi dibuat berdasarkan class pada Tugas 1, lalu dibuktikan dengan mengompilasi/menjalankan tiap pernyataan secara terpisah.

| No | Pernyataan | Prediksi | Alasan |
| :---: | :--- | :--- | :--- |
| 1 | `Zombie z = new WalkingZombie(100, 1);` | **OK** | `WalkingZombie` adalah subclass `Zombie`; upcasting otomatis dan valid. |
| 2 | `Destroyable d = z;` | **OK** | `Zombie` meng-implements `Destroyable`; upcasting ke interface valid. |
| 3 | `Zombie z2 = d;` | **Error kompilasi** | Tipe referensi `d` adalah `Destroyable`, yang bukan subtype `Zombie`. Compiler: `incompatible types: Destroyable cannot be converted to Zombie`; perlu downcast eksplisit. |
| 4 | `Zombie z3 = (Zombie) d;` | **OK** | Downcast eksplisit diizinkan karena `Destroyable` dan `Zombie` berhubungan; objek sebenarnya `WalkingZombie` memang sebuah `Zombie`, sehingga aman saat runtime. |
| 5 | `Zombie z4 = new Zombie(100, 1);` | **Error kompilasi** | `Zombie` abstract; compiler: `Zombie is abstract; cannot be instantiated`. |
| 6 | `Destroyable d2 = new Plant();` | **Error kompilasi** | `Plant` **tidak meng-implements** `Destroyable`; ia hanya memiliki method dengan parameter `Destroyable`. Compiler: `incompatible types: Plant cannot be converted to Destroyable`. |
| 7 | `Zombie z5 = new JumpingZombie(100, 2);` | **OK** | `JumpingZombie` adalah subclass `Zombie`; upcasting valid. |
| 8 | `WalkingZombie w = (WalkingZombie) z5;` | **Error saat dijalankan** | Lolos kompilasi karena `WalkingZombie` dan `JumpingZombie` masih satu hierarki, tetapi objek sebenarnya adalah `JumpingZombie`, sehingga JVM melempar `ClassCastException: class JumpingZombie cannot be cast to class WalkingZombie`. |

### Bukti Eksekusi

- **Pernyataan 3** (error kompilasi):
```
CekTugas3A.java:7: error: incompatible types: Destroyable cannot be converted to Zombie
        Zombie z2 = d;
                    ^
1 error
```

- **Pernyataan 5** (error kompilasi):
```
CekTugas3B.java:5: error: Zombie is abstract; cannot be instantiated
        Zombie z4 = new Zombie(100, 1);
                   ^
1 error
```

- **Pernyataan 6** (error kompilasi):
```
CekTugas3C.java:8: error: incompatible types: Plant cannot be converted to Destroyable
        Destroyable d2 = new Plant();
                         ^
1 error
```

- **Pernyataan 1, 2, 4, dan 7** (OK, dibuktikan dengan mencetak tipe objek):
```
WalkingZombie | WalkingZombie | WalkingZombie | JumpingZombie
```

- **Pernyataan 8** (lolos kompilasi, gagal saat runtime):
```
Exception in thread "main" java.lang.ClassCastException: class JumpingZombie cannot be cast to class WalkingZombie
	at CekTugas3C.main(CekTugas3C.java:11)
```

---

## I. Kesimpulan

1. **Polimorfisme** memungkinkan satu referensi bertipe superclass atau interface menunjuk objek dari berbagai subclass/implementor. Bentuk dasarnya adalah pewarisan (`extends`) dan kontrak interface (`implements`), dengan upcasting yang terjadi otomatis.
2. **Tipe referensi** menentukan anggota yang boleh dipanggil saat kompilasi, sedangkan **tipe objek** menentukan versi method hasil overriding yang dijalankan JVM saat runtime (*virtual method invocation* / *dynamic method dispatch*).
3. **Heterogeneous collection** dapat dibuat dengan array bertipe superclass atau interface, sehingga elemen-elemen berbeda jenis dapat diproses dengan pernyataan pemanggilan yang sama.
4. **Polymorphic argument** membuat satu method dapat menerima berbagai jenis objek, seperti `Owner.pay(Payable)` dan `Plant.doDestroy(Destroyable)`, sehingga kode tidak perlu di-overload untuk setiap tipe.
5. **`instanceof` dan casting** harus dipakai berpasangan: `instanceof` memeriksa tipe objek sebenarnya, lalu downcasting eksplisit membuka akses ke anggota subclass. Tanpa pemeriksaan tersebut, downcast yang salah akan menyebabkan `ClassCastException` saat runtime.
6. **Interface** memungkinkan polimorfisme lintas hierarki class (misalnya `Zombie` dan `Barrier` melalui `Destroyable`), sedangkan **abstract class** mencegah instansiasi langsung dan mewajibkan subclass konkret mengisi method abstract.
