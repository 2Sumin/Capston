package com.guri.loginkt_new

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.guri.loginkt_new.databinding.ActivityHomeBinding
import com.guri.loginkt_new.Fragments.*


class HomeActivity : AppCompatActivity() {
    // 뷰바인딩 사용하기 위해 추가 (3)
    private lateinit var binding: ActivityHomeBinding

    val homeF = homeFragment()
    val mapF = mapFragment()
    val calendarF = addCalendarFragment()
    val contributionF = contributionFragment()
    val mypageF = mypageFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // binding 사용하기 위해 추가 (4)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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


        // 알림 화면에서 홈화면으로 넘어온 경우
        if (intent.hasExtra("navi"))
        {
            val opt_navi = intent.getStringExtra("navi")
            if (opt_navi == "home") {
                replaceFragment(homeF)
            }
            if (opt_navi == "calendar") {
                replaceFragment(calendarF)
            }
            if ( opt_navi == "contribution") {
                replaceFragment(contributionF)
            }
            if (opt_navi == "map") {
                replaceFragment(mapF)

            }

        }

        // 기본 화면으로 홈화면 (내비게이션바 홈화면 클릭된 상태)
        else
            replaceFragment(homeF)

        // 하단 내비게이션 바 아이콘 클릭하면 다른 화면으로 전환되는 거
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> replaceFragment(homeF)
                R.id.navi_calendar -> replaceFragment(calendarF)
                R.id.navi_contribution -> replaceFragment(contributionF)
                R.id.navi_map -> replaceFragment(mapF)
            }
            true
        }

        // [홈에만 있으면 됨]툴바에서 마이페이지나 알림 버튼을 눌렀다면
        if (intent.hasExtra("toolbar"))
        {
            val opt_toolbar = intent.getStringExtra("toolbar")
            // 마이페이지 버튼 눌렀으면 mypageFragment 넣기
            if (opt_toolbar == "mypage") {
                replaceFragment(mypageF)
            }
            // 알림 눌렀으면 notificationFragment 넣기
            else if (opt_toolbar == "notification") {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
        }


    }

    private fun replaceFragment(fragment : Fragment) {
        Log.d("MainActivity","${fragment}")
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentCotainer,fragment)
                commit()
            }
    }

    // 툴바에 아이콘 나타나게
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}