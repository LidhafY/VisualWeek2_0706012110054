package com.example.visualweek2

import Model.Ayam
import Model.Globvar
import Model.Hewan
import Model.Sapi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.visualweek2.databinding.ActivityFormBinding
import kotlin.NumberFormatException

class FormActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivityFormBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityFormBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        GetIntent()
        setUpListener()

    }

    private fun GetIntent(){
        if(intent.getIntExtra("status", 0) == Globvar.statusadd){
            viewbind.imageView2.visibility = View.INVISIBLE
        }else if(intent.getIntExtra("status", 0) == Globvar.statusedit){
            position = intent.getIntExtra("position", -1)
            viewbind.textView3.visibility = View.INVISIBLE
            viewbind.inputnama.editText?.setText(Globvar.ListdataHewan[position].namahewan)
            viewbind.inputusia.editText?.setText(Globvar.ListdataHewan[position].usiahewan.toString())
            if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton3.id){
                "Ayam"
            }else if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton2.id){
                "Sapi"
            }else if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton.id){
                "Kambing"
            }
        }
    }

    private fun setUpListener() {
        viewbind.back.setOnClickListener {
            finish()
        }
        viewbind.submit.setOnClickListener {
            try{
            if(viewbind.radioButton3.isChecked) {
                val hewan = Ayam(
                    viewbind.inputnama.editText?.text.toString().trim(),
                    viewbind.inputusia.editText?.text.toString().trim().toInt(),
                    viewbind.radioButton3.text.toString().trim()

                )
                if (FormChecker(hewan)) {
                    if (intent.getIntExtra("status", 0) == Globvar.statusadd) {
                        Globvar.ListdataHewan.add(hewan)
                    } else if (intent.getIntExtra("status", 0) == Globvar.statusedit) {
                        Globvar.ListdataHewan[position] = hewan
                    }
                    Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                }
            }else if(viewbind.radioButton2.isChecked) {
                val hewan = Sapi(
                    viewbind.inputnama.editText?.text.toString().trim(),
                    viewbind.inputusia.editText?.text.toString().trim().toInt(),
                    viewbind.radioButton2.text.toString().trim()
                )
                if (FormChecker(hewan)) {
                    if (intent.getIntExtra("status", 0) == Globvar.statusadd) {
                        Globvar.ListdataHewan.add(hewan)
                    } else if (intent.getIntExtra("status", 0) == Globvar.statusedit) {
                        Globvar.ListdataHewan[position] = hewan
                    }
                    Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                }
            }
            }catch (e: NumberFormatException) {
                viewbind.inputusia.error = "Usia belom terisi"
            }
        }
    }
    private fun FormChecker(hewan:Hewan): Boolean {

        var isCompleted = true

        if(hewan.namahewan.isEmpty()){
            viewbind.inputnama.error = "Nama hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.inputnama.error = ""
        }

        if(hewan.usiahewan == 0){
            viewbind.inputusia.error = "Umur hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.inputusia.error = ""
        }

        return isCompleted
    }
}