# utspemro_movetrack
# Dokumentasi Task Aplikasi MoveTrack

## Deskripsi Aplikasi

MoveTrack adalah aplikasi Android sederhana untuk mencatat aktivitas fisik pengguna. Aplikasi ini dikembangkan menggunakan Kotlin dan XML pada Android Studio. Pengguna dapat memasukkan aktivitas, menentukan tanggal, mengisi durasi, memilih intensitas, serta melihat daftar aktivitas dan statistiknya.

## Task 1 — Model Data Aktivitas

Membuat data class `Activity` untuk merepresentasikan informasi aktivitas fisik.

Data yang disimpan meliputi:
- ID aktivitas.
- Nama aktivitas.
- Tanggal aktivitas.
- Durasi dalam menit.
- Intensitas aktivitas.
- Catatan tambahan.

## Task 2 — Daftar Aktivitas Sementara

Membuat `activityList` menggunakan `mutableListOf<Activity>()` untuk menampung data aktivitas selama aplikasi berjalan.

Data ini masih disimpan di memori sehingga tidak otomatis bertahan setelah aplikasi ditutup.

## Task 3 — Fungsi Menambahkan Aktivitas

Membuat fungsi `addActivity()` untuk memasukkan aktivitas ke dalam daftar.

Fungsi ini memeriksa agar nama aktivitas tidak kosong dan durasi harus lebih dari nol sebelum data ditambahkan.

## Task 4 — Validasi Durasi

Membuat fungsi `validateDuration()` untuk memeriksa input durasi pengguna.

Validasi mencakup:
- Memastikan input tidak kosong.
- Memastikan input berupa angka.
- Memastikan durasi lebih dari nol.

Apabila input tidak sesuai, fungsi mengembalikan pesan kesalahan.

## Task 5 — Statistik Aktivitas

Membuat tiga fungsi untuk menghitung statistik aktivitas:
- `getActivityCount()` untuk menghitung jumlah aktivitas.
- `getTotalDuration()` untuk menghitung total durasi seluruh aktivitas.
- `getAverageDuration()` untuk menghitung rata-rata durasi aktivitas.

Hasil perhitungan digunakan untuk menampilkan ringkasan aktivitas pengguna.

## Task 6 — Status Target Aktivitas

Membuat fungsi `getActivityStatus()` untuk membandingkan total durasi aktivitas dengan target yang ditentukan.

Aplikasi menampilkan status target tercapai atau belum tercapai. Target durasi yang digunakan pada implementasi ini adalah 30 menit.

## Task 7 — Inisialisasi Tampilan Aplikasi

Menginisialisasi halaman utama melalui `MainActivity` dan menghubungkannya dengan layout XML menggunakan `setContentView()`.

Komponen tampilan dihubungkan menggunakan `findViewById()` agar dapat digunakan dalam kode Kotlin.

## Task 8 — Pilihan Intensitas Aktivitas

Menggunakan `Spinner` dan `ArrayAdapter` untuk menyediakan pilihan intensitas aktivitas, yaitu ringan, sedang, dan berat.

Pilihan intensitas tersebut disimpan bersama data aktivitas yang dimasukkan pengguna.

## Task 9 — Pengambilan Input Pengguna

Mengambil data dari formulir aktivitas, termasuk nama aktivitas, durasi, catatan, tanggal, dan intensitas.

Input teks dibersihkan dari spasi berlebih menggunakan `trim()` sebelum diproses.

## Task 10 — Penyimpanan Aktivitas melalui Formulir

Membuat fungsi `saveActivity()` untuk memproses formulir ketika tombol simpan ditekan.

Fungsi ini memvalidasi input, membentuk objek `Activity`, menambahkan data ke daftar, memperbarui tampilan, dan menampilkan notifikasi ketika aktivitas berhasil disimpan.

## Task 11 — Pembaruan Statistik dan Daftar Aktivitas

Menggunakan fungsi `updateStatistics()` untuk memperbarui jumlah aktivitas, total durasi, rata-rata durasi, dan status target.

Fungsi `updateActivityList()` digunakan untuk menampilkan aktivitas yang telah dicatat. Jika belum ada aktivitas, aplikasi menampilkan informasi bahwa daftar masih kosong.

## Task 12 — Integrasi dan Pengujian Fitur

Mengintegrasikan model data, validasi, formulir, daftar aktivitas, dan statistik agar dapat bekerja bersama dalam aplikasi.

Pengujian dilakukan atau direncanakan untuk memeriksa:
- Validasi nama dan durasi aktivitas.
- Penambahan aktivitas melalui formulir.
- Tampilan daftar aktivitas.
- Ketepatan perhitungan statistik.
- Kesesuaian status target dengan total durasi.

Status keberhasilan pengujian harus dicatat berdasarkan hasil pengujian aplikasi yang sebenarnya.

## Teknologi yang Digunakan

- Kotlin sebagai bahasa pemrograman.
- Android Studio sebagai lingkungan pengembangan.
- XML untuk menyusun layout antarmuka.
- Android SDK untuk menjalankan aplikasi.
- Git dan GitHub untuk pengelolaan versi kode sumber.


