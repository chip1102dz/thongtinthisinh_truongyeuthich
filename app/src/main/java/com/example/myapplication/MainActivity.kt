package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userDatabase: UserDatabase
    private lateinit var list: MutableList<User>
    private lateinit var user: User
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
        val intent = result.data
        if(result.resultCode == Activity.RESULT_OK){

        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDatabase = UserDatabase(this)
        list = mutableListOf()

        binding.btnLuu.setOnClickListener {
            luuDuLieu()
        }
        binding.btnHienthi.setOnClickListener {
            intent = Intent(this@MainActivity, InforActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("key", user)
            intent.putExtras(bundle)
            resultLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun luuDuLieu() {
        val name = binding.edtName.text.toString()
        val ngaysinh = binding.edtDate.text.toString()
        val diachi = binding.edtAddress.text.toString()
        val gioitinh = when {
            binding.rdbNam.isChecked -> "Nam"
            binding.rdbNu.isChecked -> "Nu"
            else -> {""}
        }
        val cntt = binding.cbCntt.isChecked
        val kinhte = binding.cbKinhte.isChecked
        val supham = binding.cbSupham.isChecked
        user = User(name = name, date = ngaysinh, address = diachi, gioitinh = gioitinh, truong_cntt = cntt, truong_kinhte = kinhte, truong_supham = supham)
        userDatabase.insertUser(user)
    }
    private fun getAllUser(){
        userDatabase.getAllUser()
    }
}