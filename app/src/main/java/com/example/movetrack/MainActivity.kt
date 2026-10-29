package com.example.movetrack

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
// TASK 1: Model data aktivitas
data class Activity(
    val id: Int,
    val name: String,
    val date: String,
    val durationMinutes: Int,
    val intensity: String,
    val notes: String
)

// TASK 2: Daftar aktivitas sementara
val activityList = mutableListOf<Activity>()

// TASK 3: Menambahkan aktivitas
fun addActivity(activity: Activity) {
    require(activity.name.isNotBlank()) {
        "Nama aktivitas tidak boleh kosong"
    }

    require(activity.durationMinutes > 0) {
        "Durasi harus lebih dari 0 menit"
    }

    activityList.add(activity)
}

// TASK 4: Validasi durasi
fun validateDuration(input: String): String? {
    if (input.isBlank()) {
        return "Durasi tidak boleh kosong"
    }

    val duration = input.toIntOrNull()
        ?: return "Durasi harus berupa angka"

    if (duration <= 0) {
        return "Durasi harus lebih dari 0 menit"
    }

    return null
}

// TASK 5: Statistik aktivitas
fun getActivityCount(): Int {
    return activityList.size
}

fun getTotalDuration(): Int {
    return activityList.sumOf { it.durationMinutes }
}

fun getAverageDuration(): Double {
    return if (activityList.isEmpty()) {
        0.0
    } else {
        activityList.map { it.durationMinutes }.average()
    }
}

// TASK 6: Status target
fun getActivityStatus(targetMinutes: Int): String {
    if (targetMinutes <= 0) {
        return "Target harus lebih dari 0 menit"
    }

    return if (getTotalDuration() >= targetMinutes) {
        "Target tercapai!"
    } else {
        "Target belum tercapai. Ayo tetap bergerak!"
    }
}

class MainActivity : AppCompatActivity() {

    private val targetMinutes = 30

    private lateinit var etActivityName: EditText
    private lateinit var etDuration: EditText
    private lateinit var etNotes: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var spIntensity: Spinner
    private lateinit var btnAddActivity: Button

    private lateinit var tvTotalActivities: TextView
    private lateinit var tvTotalDuration: TextView
    private lateinit var tvAverageDuration: TextView
    private lateinit var tvEmptyActivities: TextView
    private lateinit var tvActivityStatus: TextView
    private lateinit var layoutActivityList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                bars.left,
                bars.top,
                bars.right,
                bars.bottom
            )

            insets
        }

        // Menghubungkan komponen XML
        etActivityName = findViewById(R.id.etActivityName)
        etDuration = findViewById(R.id.etDuration)
        etNotes = findViewById(R.id.etNotes)
        datePicker = findViewById(R.id.datePicker)
        spIntensity = findViewById(R.id.spIntensity)
        btnAddActivity = findViewById(R.id.btnAddActivity)

        tvTotalActivities = findViewById(R.id.tvTotalActivities)
        tvTotalDuration = findViewById(R.id.tvTotalDuration)
        tvAverageDuration = findViewById(R.id.tvAverageDuration)
        tvEmptyActivities = findViewById(R.id.tvEmptyActivities)
        tvActivityStatus = findViewById(R.id.tvActivityStatus)
        layoutActivityList = findViewById(R.id.layoutActivityList)

        // Pilihan intensitas
        val intensityOptions = arrayOf(
            "Ringan",
            "Sedang",
            "Berat"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            intensityOptions
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spIntensity.adapter = adapter

        updateStatistics()
        updateActivityList()

        // Tombol simpan
        btnAddActivity.setOnClickListener {
            saveActivity()
        }
    }

    private fun saveActivity() {
        val name = etActivityName.text.toString().trim()
        val durationInput = etDuration.text.toString().trim()
        val notes = etNotes.text.toString().trim()

        if (name.isBlank()) {
            etActivityName.error = "Nama aktivitas wajib diisi"
            etActivityName.requestFocus()
            return
        }

        val durationError = validateDuration(durationInput)

        if (durationError != null) {
            etDuration.error = durationError
            etDuration.requestFocus()
            return
        }

        val selectedDate = String.format(
            Locale.US,
            "%02d-%02d-%04d",
            datePicker.dayOfMonth,
            datePicker.month + 1,
            datePicker.year
        )

        val newActivity = Activity(
            id = (activityList.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            date = selectedDate,
            durationMinutes = durationInput.toInt(),
            intensity = spIntensity.selectedItem.toString(),
            notes = notes
        )

        addActivity(newActivity)

        updateStatistics()
        updateActivityList()

        Toast.makeText(
            this,
            "Aktivitas berhasil disimpan!",
            Toast.LENGTH_SHORT
        ).show()

        etActivityName.text.clear()
        etDuration.text.clear()
        etNotes.text.clear()
        spIntensity.setSelection(0)
    }

    private fun updateStatistics() {
        tvTotalActivities.text = getActivityCount().toString()
        tvTotalDuration.text = getTotalDuration().toString()

        tvAverageDuration.text = String.format(
            Locale.getDefault(),
            "%.1f",
            getAverageDuration()
        )

        tvActivityStatus.text = getActivityStatus(targetMinutes)
    }

    private fun updateActivityList() {
        layoutActivityList.removeAllViews()

        if (activityList.isEmpty()) {
            tvEmptyActivities.visibility = View.VISIBLE
            return
        }

        tvEmptyActivities.visibility = View.GONE

        for (activity in activityList.reversed()) {
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 20, 24, 20)
                setBackgroundColor(
                    android.graphics.Color.WHITE
                )
                elevation = 3f
            }

            val title = TextView(this).apply {
                text = activity.name
                textSize = 18f
                setTextColor(
                    android.graphics.Color.rgb(24, 59, 86)
                )
                setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
                )
            }

            val details = TextView(this).apply {
                text = "${activity.date} • " +
                        "${activity.durationMinutes} menit\n" +
                        "Intensitas: ${activity.intensity}"
                textSize = 14f
                setTextColor(
                    android.graphics.Color.rgb(80, 100, 120)
                )
                setPadding(0, 8, 0, 0)
            }

            card.addView(title)
            card.addView(details)

            if (activity.notes.isNotBlank()) {
                val note = TextView(this).apply {
                    text = "Catatan: ${activity.notes}"
                    textSize = 14f
                    setTextColor(
                        android.graphics.Color.rgb(80, 100, 120)
                    )
                    setPadding(0, 6, 0, 0)
                }

                card.addView(note)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.bottomMargin = 16

            layoutActivityList.addView(card, params)
        }
    }
}
