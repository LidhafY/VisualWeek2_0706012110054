package com.example.visualweek2

import Adapter.adapter
import Interface.Listener
import Model.Globvar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.visualweek2.databinding.ActivityHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity(), Listener {
    private lateinit var viewbind: ActivityHomeBinding
    private  val adapterhewan = adapter(Globvar.ListdataHewan, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        setupRecycler()
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        if(Globvar.ListdataHewan.size > 0){
            viewbind.Mainrecycler.visibility = View.VISIBLE
            viewbind.textView.visibility = View.GONE
        }else{
            viewbind.Mainrecycler.visibility = View.GONE
            viewbind.textView.visibility = View.VISIBLE
        }
        adapterhewan.notifyDataSetChanged()
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewbind.Mainrecycler.layoutManager =layoutManager
        viewbind.Mainrecycler.adapter = adapterhewan
    }

    private fun setUpListener(){
        viewbind.Add.setOnClickListener {
            val add = Intent(baseContext, FormActivity::class.java).apply {
                putExtra("status", Globvar.statusadd)
            }
            startActivity(add)
        }
    }

    override fun editOnclick(position: Int) {

        val edit = Intent(baseContext, FormActivity::class.java).apply {
            putExtra("status", Globvar.statusedit)
            putExtra("position", position)
        }
        startActivity(edit)


    }

    override fun deleteOnClick(posistion: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda ingin menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                Globvar.ListdataHewan.removeAt(posistion)
                Toast.makeText(baseContext, "Data berhasil di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }


}