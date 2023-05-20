package com.guri.loginkt_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guri.loginkt_new.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뷰바인딩 사용하기 위해 추가 (3)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding 사용하기 위해 추가 (4)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 로그인 버튼 누르면 홈화면으로 이동
        binding.btnRegister.setOnClickListener {
            // 메인 화면 home에 넣기
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}