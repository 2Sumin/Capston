package com.guri.loginkt_new

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.guri.loginkt_new.databinding.ActivityLoginBinding

// 툴바를 넣기 위해 BaseActivity 상속
class LoginActivity : AppCompatActivity() {

    // 뷰바인딩 사용하기 위해 추가 (3)
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding 사용하기 위해 추가 (4)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 관련
        setSupportActionBar(binding.toolbar)
        // 파일 제목 툴바에서 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //왼쪽 버튼 사용설정(기본은 뒤로가기)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //왼쪽 버튼 아이콘 변경
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.arrow_back)

        // 툴바 메뉴 클릭시
        binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId) {
//                 mypage 클릭 시
                R.id.toolbar_mypage -> {
                    // 마이페이지 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("toolbar", "mypage")
                    startActivity(intent)
                    true
                }

                // 알림 클릭 시
                R.id.toolbar_notification -> {
                    // 알림 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("toolbar", "notification")
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }


        // 로그인 버튼 누르면 addprojectFragment 이동
        binding.btnLogin.setOnClickListener {
//             메인 화면 home에 넣기
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    // 툴바에 아이콘 나타나게
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



}


