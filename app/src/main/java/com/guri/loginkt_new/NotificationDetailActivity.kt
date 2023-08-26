package com.guri.loginkt_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.guri.loginkt_new.Fragments.calendarFragment
import com.guri.loginkt_new.Fragments.contributionFragment
import com.guri.loginkt_new.Fragments.homeFragment
import com.guri.loginkt_new.Fragments.mapFragment
import com.guri.loginkt_new.databinding.ActivityNotificationDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class NotificationDetailActivity : AppCompatActivity() {

    // 뷰바인딩 사용하기 위해 추가 (3)
    private lateinit var binding: ActivityNotificationDetailBinding
    // firebase
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val homeF = homeFragment()
    val mapF = mapFragment()
    val calendarF = calendarFragment()
    val contributionF = contributionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_notification_detail)

        // binding 사용하기 위해 추가 (4)
        binding = ActivityNotificationDetailBinding.inflate(layoutInflater)
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

// firebase
// getIntent()를 사용하여 전달된 ID 값을 가져옴.
        val selectedId = intent.getStringExtra("selectedId")

        // Firestore에서 해당 ID를 사용하여 데이터를 가져옵니다.
        if (selectedId != null) {
            fetchDataFromFirestore(selectedId)
        }
    }

// firebase

    private fun fetchDataFromFirestore(notificationId: String) {
        firestore.collection("Telegram") // Collection name
            .document(notificationId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // 데이터를 화면에 표시
                    binding.teleTitle.text = document.getString("Title")
//                    binding.teleDate.text = document.getDate("Date").toString()
                    val date = document.getDate("Date")
                    if (date != null) {
                        val formatter = SimpleDateFormat("a h:mm", Locale.getDefault())
                        binding.teleDate.text = formatter.format(date)
                    }
                    binding.teleContents.text = document.getString("Contents")
                } else {
                    Log.d("NotificationDetail", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("NotificationDetail", "get failed with ", exception)
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