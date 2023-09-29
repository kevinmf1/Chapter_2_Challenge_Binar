package com.vinz.chapter2challengebinar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vinz.chapter2challengebinar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pelayananKeteranganEditText.hint = getString(R.string.pelayanan_hint_value)

        binding.submitButton.setOnClickListener {
            validation()
        }

    }

    private fun validation() {
        val nominal = binding.nominalTipEditText.text.toString()
        val serviceExperience = binding.serviceExperience.checkedRadioButtonId
        val ratingBar = binding.ratingBar.rating

        if (nominal.isEmpty()) {
            binding.nominalTipEditText.error = getString(R.string.nominal_tip_error)
            Toast.makeText(this, getString(R.string.nominal_tip_error), Toast.LENGTH_SHORT).show()
            binding.nominalTipEditText.requestFocus()
        } else if (serviceExperience == -1) {
            Toast.makeText(this, getString(R.string.pelayanan_error), Toast.LENGTH_SHORT).show()
            binding.serviceExperience.requestFocus()
        } else if (ratingBar == 0.0f) {
            Toast.makeText(this, getString(R.string.rating_error), Toast.LENGTH_SHORT).show()
            binding.ratingBar.requestFocus()
        } else {
            tipSubmit(nominal.toInt())
        }
    }

    private fun tipSubmit(nominal: Int) {
        AlertDialog.Builder(this)
            .setTitle("Terima kasih atas tip yang Anda berikan")
            .setMessage("Tip ada sebesar $nominal sudah kami berikan kepada pelayan kami! \n\n" + "Apakah Anda ingin memberikan tip lagi?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { _, _ ->
                finishAndRemoveTask()
            }
            .create()
            .show()
    }
}