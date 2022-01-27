package com.example.practiceprojectv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.room.Entity
import androidx.room.Room
import com.example.practiceprojectv2.Database.Entry
import com.example.practiceprojectv2.Database.EntryDatabase
import com.example.practiceprojectv2.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(
                applicationContext,
                EntryDatabase::class.java, "Database_name"
        )
                .fallbackToDestructiveMigration()
                .build()

        val Worker : Spinner = binding.Worker
        ArrayAdapter.createFromResource(this,R.array.Worker,android.R.layout.simple_spinner_item)
                .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                Worker.adapter = adapter}
        Worker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapteView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.WorkerText.text = adapteView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val WorkList: Spinner = binding.Worklist
        ArrayAdapter.createFromResource(this, R.array.Worklist,android.R.layout.simple_spinner_item)
                .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                WorkList.adapter = adapter}
        WorkList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected (adapterView: AdapterView<*>?,view: View,position: Int, id:Long){
                binding.WorkListText.text = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
        val button = binding.button
        button.setOnClickListener{
            val name = binding.WorkerText.text
            val work = binding.WorkListText.text
            val instance = getCurrentDateTime().toString()

            binding.View1.text = "Worker " + name + "Started " + work + " " + "At " + instance

            Toast.makeText(this , " the time is " + instance , Toast.LENGTH_LONG).show()

            thread {

                db.entryDao()?.insertAll(Entry(ID = 0,
                        name.toString(), work.toString(),
                        getCurrentDateTime().toString()))
            }
        }
    }
}