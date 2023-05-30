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
            MyViewPager("물리1", "수업 복습하기\n완자 문제풀이","5월 10일 9시"),
            MyViewPager("화학1", "오답노트 작성하기\n수업 복습하기","5월 19일 16시"),
            MyViewPager("수학2", "수업 복습하기\n쎈 p80-92 문제풀이","5월 26일 10시")        )

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