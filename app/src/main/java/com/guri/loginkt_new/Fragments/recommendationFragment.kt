package com.guri.loginkt_new.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentRecommendationBinding
import com.guri.loginkt_new.recyclerView.*

class recommendationFragment : Fragment() {
    private var _binding: FragmentRecommendationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        val view = binding.root

        // 저장하기 버튼 누르면 넘어가게
        binding.btnToRec.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, selectionwithpopupFragment()).commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var vpList = arrayListOf(
            MyViewPager("물리1", "수업 복습하기\n완자 문제풀이","5월 10일 9시"),
            MyViewPager("화학1", "오답노트 작성하기\n수업 복습하기","5월 19일 16시"),
            MyViewPager("수학2", "수업 복습하기\n쎈 p80-92 문제풀이","5월 26일 10시")        )

        binding.viewPagerAespa.adapter = MyViewPagerAdapter(vpList)

        binding.viewPagerAespa.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        var verList = arrayListOf(
            ListVertical("05/18", "14:00 - 18:00",""),
            ListVertical("05/18", "14:00 - 18:00",""),
            ListVertical("05/18", "14:00 - 18:00",""),
            ListVertical("05/18", "14:00 - 18:00",""),
            ListVertical("05/18", "14:00 - 18:00",""),
            ListVertical("05/18", "14:00 - 18:00","")
        )

        binding.recyclerVertical.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerVertical.setHasFixedSize(true)
        // RecyclerView.adapter에 지정
        binding.recyclerVertical.adapter = ListVerticalAdapter(verList)
    }
}