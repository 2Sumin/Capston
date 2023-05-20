package com.guri.loginkt_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.guri.loginkt_new.Fragments.*
import com.guri.loginkt_new.databinding.ActivityNotificationBinding
import com.guri.loginkt_new.recyclerView.NotificationListAdapter
import com.guri.loginkt_new.recyclerView.NotificationList

class NotificationActivity : AppCompatActivity() {
    // 뷰바인딩 사용하기 위해 추가 (3)
    private lateinit var binding: ActivityNotificationBinding

    val homeF = homeFragment()
    val mapF = mapFragment()
    val calendarF = calendarFragment()
    val contributionF = contributionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_notification)

        // binding 사용하기 위해 추가 (4)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 관련
        setSupportActionBar(binding.toolbar)
//         파일 제목 툴바에서 제거
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

        // 하단 내비게이션 바 아이콘 클릭하면 다른 화면으로 전환되는 거
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    // 알림 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("navi", "home")
                    startActivity(intent)
                    true
                }
                R.id.navi_calendar -> {
                    // 알림 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("navi", "calendar")
                    startActivity(intent)
                    true
                }
                R.id.navi_contribution -> {
                    // 알림 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("navi", "contribution")
                    startActivity(intent)
                    true
                }
                R.id.navi_map -> {
                    // 알림 화면 home에 넣기
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("navi", "map")
                    startActivity(intent)
                    true
                }
            }
            true
        }

        // 여기부터 알림 목록

        val profileList = arrayListOf(
            NotificationList("회의 일정 추가", "5월 22일 15:00 - 18:00"),
            NotificationList("장소 확정 완료", "5월 17일 13:00"),
            NotificationList("new Todo 추가", "5월 14일 14:29"),
            NotificationList("회의 일정 추가", "5월 13일 14:00 - 16:00"),
            NotificationList("장소 확정 완료", "5월 13일 12:24"),
            NotificationList("회의 일정 추가", "5월 11일 11:00 - 13:00"),
            NotificationList("new Todo 추가", "5월 8일 17:00"),
            NotificationList("회의 일정 추가", "5월 7일 15:00 - 18:00"),
            NotificationList("회의 일정 추가", "5월 6일 15:00 - 18:00"),
            NotificationList("장소 확정 완료", "5월 6일 18:59"),
            NotificationList("회의 공지", "5월 4일 15:00"),
            NotificationList("회의 일정 추가", "5월 3일 15:00 - 18:00"),
            NotificationList("회의 일정 추가", "5월 2일 13:00 - 15:00"),
            NotificationList("new Todo 추가", "5월 2일 14:39"),
            NotificationList("장소 확정 완료", "5월 1일 13:29"),
            NotificationList("회의 일정 추가", "5월 1일 11:00 - 13:00"),
            NotificationList("회의 공지", "4월 29일 15:00"),
            NotificationList("회의 일정 추가", "4월 29일 13:47"),
            NotificationList("장소 확정 완료", "4월 27일 19:00"),
            NotificationList("new Todo 추가", "4월 25일 13:59"),
            NotificationList("회의 일정 추가", "4월 24일 15:00 - 18:00"),
            NotificationList("회의 일정 추가", "4월 23일 13:00 - 16:00"),
            NotificationList("장소 확정 완료", "4월 17일 11:26"),
            NotificationList("회의 일정 추가", "4월 14일 15:00 - 18:00"),
            NotificationList("new Todo 추가", "4월 10일 15:00 - 18:00")
        )

        // 세로 방향으로 리스트 만들고있으니까 VERTICAL
        binding.rvPro1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPro1.setHasFixedSize(true)
        binding.rvPro1.adapter = NotificationListAdapter(profileList)


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