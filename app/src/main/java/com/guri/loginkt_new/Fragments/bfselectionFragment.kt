package com.guri.loginkt_new.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentBfselectionBinding
import com.guri.loginkt_new.recyclerView.MyViewPager
import com.guri.loginkt_new.recyclerView.MyViewPagerAdapter


class bfselectionFragment : Fragment(R.layout.fragment_bfselection) {

    private var _binding: FragmentBfselectionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewPager_aespa: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentBfselectionBinding.inflate(inflater, container, false)
        val view = binding.root

        var vpList = arrayListOf(
            MyViewPager("물리1", "수업 복습하기\n완자 문제풀이","5월 10일 9시"),
            MyViewPager("화학1", "오답노트 작성하기\n수업 복습하기","5월 19일 16시"),
            MyViewPager("수학2", "수업 복습하기\n쎈 p80-92 문제풀이","5월 26일 10시")
        )

        binding.viewPagerAespa.adapter = MyViewPagerAdapter(vpList)

        binding.viewPagerAespa.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 저장하기 버튼 누르면 넘어가게
        binding.btnMe.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, calendarFragment()).commit()
        }

        return view
    }
}