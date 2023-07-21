package com.vishal.listassigment

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.vishal.listassigment.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    var position:Int=0
    lateinit var binding: ActivityMainBinding
    var arrayList = arrayListOf<String>("Vishal","Jatin","Vikas","Vikram","Rahul")
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listView = findViewById<ListView>(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.secondcustom)
            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.show()
            var btnUpload=dialog.findViewById<Button>(R.id.btnUpdate)
            var btnDelete=dialog.findViewById<Button>(R.id.btnDelete)
            var etName = dialog.findViewById<EditText>(R.id.etName)
            etName.setText(arrayList[position])
            Toast.makeText(this,"$position",Toast.LENGTH_SHORT).show()

            btnUpload.setOnClickListener {

                arrayList[position]=etName.text.toString()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            btnDelete.setOnClickListener {
                arrayList.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }


            binding.fab.setOnClickListener {

                var dialog = Dialog(this)
                dialog.setContentView(R.layout.firstcustom)
                dialog.getWindow()?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                var btnAdd = dialog.findViewById<Button>(R.id.btnAdd)
                var etName = dialog.findViewById<EditText>(R.id.etName)
                btnAdd.setOnClickListener {
                    if (etName.text.toString().isNullOrEmpty()) {
                        etName.error = "Enter your name"
                    } else {
                        arrayList.add(etName.text.toString())
                        dialog.dismiss()
                    }
                }
                dialog.show()
                adapter.notifyDataSetChanged()
            }

            binding.listView.setOnItemClickListener { parent, view, position, id ->
                System.out.println("parent $parent view $view position $position id $id")
            }

        }
    }
}



