package com.syazwan.lab08

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.syazwan.lab08.databinding.ActivityMainBinding
import java.util.Calendar
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val pizzaSizes = arrayOf("Please Select Pizza Size","Small","Medium","Large","Extra Large")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.scheduleBtn.setOnClickListener {

            var intent = Intent(this, ThanksActivity::class.java)
            intent.putExtra("name",binding.nameEditText.text.toString())
            intent.putExtra("phone",binding.phoneEditText.text.toString())
            intent.putExtra("size",binding.sizeTextView.text.toString())
            intent.putExtra("date",binding.dateTextView.text.toString())
            intent.putExtra("time",binding.timeTextView.text.toString())

            startActivity(intent)
        }

        binding.sizeSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {


            //bila seekbar bergerak/berubah
            // nilai yg diubah 0,1,2,3 -> progree [Int]
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sizeTextView.text = pizzaSizes[progress]
            }


            //bila seekbar mula ditekan
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.sizeTextView.text = "Seekbar pressed"
            }


            //bila seekbar habis ditekan
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding.dateBtn.setOnClickListener {
            //get the current date

            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            //Kod untuk membina datepicker
            // DatePicker dialog ada 6 argument
            // 1) Di mana ia keluar (this) => akan keluar di MainActivity
            // 2) Design / style datepicker => Defaut style yang dipanggil theme.Overlay
            // 3)Listener -> Setelah tarikh dipilih what should be executed
            // 4) Default year
            // 5) Default month
            // 6) Default date
            val myDatePicker =
                DatePickerDialog(this,
                    android.R.style.ThemeOverlay,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                                                       binding.dateTextView.text = "$dayOfMonth/${month+1}/$year"
                    },
                    year,
                    month,
                    day
                )
            myDatePicker.show()
        }

        binding.timeBtn.setOnClickListener {
            //get the time

            val c = Calendar.getInstance()//get current date and time
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minutes = c.get(Calendar.MINUTE)

            //membina time picker
            //1) where the timepicker is built? this ->MainActivity
            //2) Listener -> When the time is selected excecuted the cdde
            //3) Default hour
            //4) Default minute
            //5) 24 hours or 12 hours
            val myTimePicker =
                TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        binding.timeTextView.text = String.format("%02d:%02d",hourOfDay,minutes)
                    },
                    hour,
                    minutes,
                    true
            )
            myTimePicker.show()
        }
    }
}