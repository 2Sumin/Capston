package com.guri.loginkt_new.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentCalendarBinding
import com.guri.loginkt_new.recyclerView.MyViewPager
import com.guri.loginkt_new.recyclerView.MyViewPagerAdapter

class calendarFragment : Fragment(R.layout.fragment_calendar) {
    // RecyclerView.adapter에 지정할 Adapter
    private lateinit var listAdapter: ListAdapter

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun btn_clicked(btn: Button) {
        btn.setOnClickListener {
            btn.isSelected = btn.isSelected != true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val view = binding.root

        var vpList = arrayListOf(
            MyViewPager("피그마 제작하기", "스플래시 화면에 넣을 로고 완성하기\n상단바, 하단바 만들고 프래그먼트로 연결하기","5월 10일 9시"),
            MyViewPager("코틀린 프론트엔드 구현하기", "메인화면, 지도 기능 프래그먼트 연결\n로그인, 회원가입 페이지 구현하기","5월 19일 16시"),
            MyViewPager("코틀린 프론트엔드 완성하기", "일정, 기여도 확인 기능 연결\n마이페이지, 알림 기능 구현하기","5월 26일 10시")
        )

        binding.viewPagerAespa.adapter = MyViewPagerAdapter(vpList)

        binding.viewPagerAespa.orientation = ViewPager2.ORIENTATION_HORIZONTAL


//        버튼(시간) 한번 클릭되면 클릭된 채로 두기
        btn_clicked(binding.btn1)
        btn_clicked(binding.btn2)
        btn_clicked(binding.btn3)
        btn_clicked(binding.btn4)
        btn_clicked(binding.btn5)
        btn_clicked(binding.btn6)
        btn_clicked(binding.btn7)
        btn_clicked(binding.btn8)
        btn_clicked(binding.btn9)
        btn_clicked(binding.btn10)
        btn_clicked(binding.btn11)
        btn_clicked(binding.btn12)

        // 저장하기 버튼 누르면 넘어가게
        binding.btnSave.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, afselectionFragment()).commit()
        }

        return view
    }




}