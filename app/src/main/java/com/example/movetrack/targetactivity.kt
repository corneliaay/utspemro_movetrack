
package com.example.movetrack

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TargetActivity : AppCompatActivity() {

    private var targetMinutes = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        val etTargetMinutes =
            findViewById<EditText>(R.id.etTargetMinutes)

        val btnSaveTarget =
            findViewById<Button>(R.id.btnSaveTarget)

        val tvTargetResult =
            findViewById<TextView>(R.id.tvTargetResult)

        val btnBackTarget =
            findViewById<Button>(R.id.btnBackTarget)

        etTargetMinutes.setText(targetMinutes.toString())

        btnSaveTarget.setOnClickListener {
            val input = etTargetMinutes.text.toString().trim()
            val duration = input.toIntOrNull()

            if (duration == null || duration <= 0) {
                etTargetMinutes.error =
                    "Masukkan angka lebih dari 0"
                return@setOnClickListener
            }

            targetMinutes = duration

            tvTargetResult.text =
                "Target harianmu: $targetMinutes menit"

            Toast.makeText(
                this,
                "Target berhasil disimpan!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnBackTarget.setOnClickListener {
            finish()
        }
    }
}
