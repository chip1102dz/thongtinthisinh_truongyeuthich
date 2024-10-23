package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityInforBinding
import java.io.Serializable

class InforActivity : AppCompatActivity() {
    lateinit var binding: ActivityInforBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInforBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user: User = intent.getSerializableExtra("key") as User
        binding.tvName.text = user.name
        binding.tvDate.text = user.date
        binding.tvAddress.text = user.address
        binding.tvGioitinh.text =user.gioitinh
        binding.tvCntt.text = user.truong_cntt.toString()
        binding.tvKinhte.text = user.truong_kinhte.toString()
        binding.tvSupham.text = user.truong_supham.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}