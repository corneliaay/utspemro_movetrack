
package com.example.movetrack

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity

class ActivityListActivity : AppCompatActivity() {

    private lateinit var tvEmptyList: TextView
    private lateinit var layoutHistoryList: LinearLayout
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan nama XML milikmu
        setContentView(R.layout.activity_activitylistactivity)

        // Menghubungkan komponen XML
        tvEmptyList = findViewById(R.id.tvEmptyList)
        layoutHistoryList = findViewById(R.id.layoutHistoryList)
        btnBack = findViewById(R.id.btnBack)

        // Tombol kembali ke halaman sebelumnya
        btnBack.setOnClickListener {
            finish()
        }

        // Menampilkan daftar aktivitas
        showActivities()
    }

    private fun showActivities() {
        layoutHistoryList.removeAllViews()

        // Jika belum ada aktivitas
        if (activityList.isEmpty()) {
            tvEmptyList.visibility = View.VISIBLE
            return
        }

        // Jika ada aktivitas, sembunyikan pesan kosong
        tvEmptyList.visibility = View.GONE

        // Aktivitas terbaru ditampilkan paling atas
        for (activity in activityList.reversed()) {

            // Membuat kartu aktivitas
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 24, 24, 24)
                setBackgroundColor(Color.WHITE)
                elevation = 4f
            }

            // Nama aktivitas
            val tvName = TextView(this).apply {
                text = activity.name
                textSize = 20f
                setTextColor(Color.rgb(24, 59, 86))
                setTypeface(null, Typeface.BOLD)
            }

            // Tanggal dan durasi
            val tvDateDuration = TextView(this).apply {
                text = "Tanggal: ${activity.date}\n" +
                        "Durasi: ${activity.durationMinutes} menit"
                textSize = 15f
                setTextColor(Color.DKGRAY)
                setPadding(0, 12, 0, 0)
            }

            // Intensitas
            val tvIntensity = TextView(this).apply {
                text = "Intensitas: ${activity.intensity}"
                textSize = 15f
                setTextColor(Color.DKGRAY)
                setPadding(0, 8, 0, 0)
            }

            // Catatan
            val tvNotes = TextView(this).apply {
                text = if (activity.notes.isBlank()) {
                    "Catatan: Tidak ada"
                } else {
                    "Catatan: ${activity.notes}"
                }

                textSize = 15f
                setTextColor(Color.DKGRAY)
                setPadding(0, 8, 0, 0)
            }

            // Memasukkan informasi ke dalam kartu
            card.addView(tvName)
            card.addView(tvDateDuration)
            card.addView(tvIntensity)
            card.addView(tvNotes)

            // Memberi jarak antar kartu
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.bottomMargin = 20

            layoutHistoryList.addView(card, params)
        }
    }

    override fun onResume() {
        super.onResume()

        // Memperbarui daftar saat halaman dibuka kembali
        if (::layoutHistoryList.isInitialized) {
            showActivities()
        }
    }
}
