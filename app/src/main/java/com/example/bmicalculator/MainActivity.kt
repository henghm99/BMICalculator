package com.example.bmicalculator

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calc.setOnClickListener {
            try {
                calcBtnOnclick(it)
            } catch (e: Exception) {
                Toast.makeText(this,"Invalid input!", Toast.LENGTH_LONG).show()
            }
        }

        button_reset.setOnClickListener {
            resetBtnOnclick(it)
        }
    }

    private fun calcBtnOnclick(view: View) {
        val heightText = editText_height.text.toString()
        val weightText = editText_weight.text.toString()

        val height: Double = heightText.toDouble()
        val weight: Double = weightText.toDouble()
        val bmi: Double = calcBmi(height, weight)
        val status: String

        val bmiText = findViewById<TextView>(R.id.textView_bmi)
        val bmiImage: ImageView = findViewById(R.id.imageView)

        if (bmi < 18.5) {
            status = "Underweight"
            bmiImage.setImageResource(R.drawable.under)
        } else if (bmi < 25) {
            status = "Normal"
            bmiImage.setImageResource(R.drawable.normal)
        } else {
            status = "Overweight"
            bmiImage.setImageResource(R.drawable.over)
        }

        bmiText.text = "%.2f (%s)".format(bmi, status)
    }

    private fun calcBmi(height: Double, weight: Double): Double {
        return (weight / (height * height))
    }

    private fun resetBtnOnclick(view: View) {
        val heightText: TextView = findViewById(R.id.editText_height)
        val weightText: TextView = findViewById(R.id.editText_weight)
        val bmiText: TextView = findViewById(R.id.textView_bmi)
        val bmiImage: ImageView = findViewById(R.id.imageView)

        heightText.text = ""
        heightText.requestFocus()
        weightText.text = ""
        bmiText.text = ""
        bmiImage.setImageResource(R.drawable.empty)
    }
}
