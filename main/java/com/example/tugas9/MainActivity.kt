package com.example.tugas9

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var dutyViewModel: DutyViewModel
    private val newDutyActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = DutyListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        dutyViewModel = ViewModelProvider(this).get(DutyViewModel::class.java)
        dutyViewModel.allWords.observe(this, Observer { dutys ->
            //Update the cached copy of the words in the adapter.
            dutys?.let { adapter.setDutys(it) }
        })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewDutyActivity::class.java)
            startActivityForResult(intent, newDutyActivityRequestCode)
        }
        val del = findViewById<Button>(R.id.button_delete)
        del.setOnClickListener{ dutyViewModel.deleteAll()
        Toast.makeText(applicationContext, R.string.button_deleteAll, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newDutyActivityRequestCode &&
            resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewDutyActivity.EXTRA_REPLY)?.let {
                val duty = Duty(it)
                dutyViewModel.insert(duty)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}