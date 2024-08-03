package com.adista.savemyname

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adista.savemyname.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchMode: Switch

    private lateinit var binding: ActivityMainBinding
    private lateinit var myPref : MySharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        switchMode = binding.switchNight
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            switchMode(isChecked)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myPref = MySharedPref(this)
        binding.tvName.text = myPref.getName()

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            myPref.saveName(name)
            binding.tvName.text = myPref.getName()
        }

    }
    private fun switchMode(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}