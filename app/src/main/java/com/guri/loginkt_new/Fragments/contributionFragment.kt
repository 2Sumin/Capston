package com.guri.loginkt_new.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentContributionBinding
import com.guri.loginkt_new.recyclerView.ListHorizontal
import com.guri.loginkt_new.recyclerView.ListHorizontalAdapter
import com.guri.loginkt_new.recyclerView.ListVertical
import com.guri.loginkt_new.recyclerView.ListVerticalAdapter

class contributionFragment: BottomSheetDialogFragment() {

    // RecyclerView.adapter에 지정할 Adapter
    private lateinit var listAdapter: ListAdapter

    private var _binding: FragmentContributionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    private var hour = calendar.get(Calendar.HOUR)
    private var minute = calendar.get(Calendar.MINUTE)

    // 추가되는 항목
    private var add_date: String? = null
    private var add_time: String? = null
    private var add_title: String? = null
    private var add_detail: String? = null
    private var add_item: ListVertical? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContributionBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.btnAdd.setOnClickListener {
            val bottomSheetView = layoutInflater.inflate(R.layout.fragment_todo, null)
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetDialog.setContentView(bottomSheetView)

            bottomSheetView.findViewById<View>(com.guri.loginkt_new.R.id.btn_todo_date).setOnClickListener {
                bottomSheetView.findViewById<View>(R.id.btn_todo_date).setOnClickListener {
                    val datePickerDialog =
                        DatePickerDialog(requireContext(), { _, year, month, day ->
                            val date_ =
                                year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
                            add_date = date_
                            val dd= add_date
                            bottomSheetView.findViewById<TextView>(R.id.tv_af_todo_duedate).setText(date_)
                        }, year, month, day)

                    datePickerDialog.show()
                }
                bottomSheetView.findViewById<View>(R.id.btn_todo_time).setOnClickListener {
                    val timePickerDialog =

                        TimePickerDialog(requireContext(), { _, hour, minute ->
                            if (hour>12) {
                                val newhour = hour-12
                                val pm = "오후 " + newhour.toString() + "시 " + minute.toString() + "분"
                                add_time = add_date + pm
                                bottomSheetView.findViewById<TextView>(R.id.tv_af_todo_duetime).setText(pm)
                            }
                            else {
                                val am = "오전 " + hour.toString() + "시 " + minute.toString() + "분"
                                add_time = add_date + am
                                bottomSheetView.findViewById<TextView>(R.id.tv_af_todo_duetime).setText(am)
                            }

                        }, hour, minute, false)
                   timePickerDialog.show()
                }
            }

            bottomSheetView.findViewById<View>(R.id.btn_add2).setOnClickListener {

                add_title = bottomSheetView.findViewById<TextView>(R.id.et_todo_name).toString()
                add_detail = bottomSheetView.findViewById<TextView>(R.id.et_todo_detail).toString()

                bottomSheetDialog.dismiss()
            }

            // bottomSheetDialog의 dismiss 버튼 선택시 dialog disappear

            // bottomSheetDialog 뷰 생성
            bottomSheetDialog.setContentView(bottomSheetView)

            bottomSheetDialog.show()

        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horList = arrayListOf(
            ListHorizontal("12%"),
            ListHorizontal("24%"),
            ListHorizontal("48%"),
            ListHorizontal("96%"),
            ListHorizontal("24%"),
            ListHorizontal("50%")
        )

        var verList = arrayListOf(
            ListVertical("수학", "수1/수학의 정석 : part 삼각함수 ","5월 17일 10시"),
            ListVertical("물리1", "과학/완자 : part 전기와 자기","5월 25일 15시"),
            ListVertical("수학", "수1/수학의 정석 : part 방정식과 부등식 ","6월 12일 9시"),
            ListVertical("국어", "문학 / 비상 : 현대문학","6월 15일 17시"),
            ListVertical("국어", "비문학 / 디딤돌 : 3주차 ","6월 19일 18시"),
            ListVertical("영어", "영어 / EBS 수능특강 : 6주차","6월 21일 21시")
        )
//        verList.add(ListVertical(add_title.toString(), add_detail.toString(), add_time.toString()))


        binding.recyclerHorizon.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        binding.recyclerHorizon.setHasFixedSize(true)
        // RecyclerView.adapter에 지정
        binding.recyclerHorizon.adapter = ListHorizontalAdapter(horList)

        binding.recyclerVertical.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerVertical.setHasFixedSize(true)
        // RecyclerView.adapter에 지정
        binding.recyclerVertical.adapter = ListVerticalAdapter(verList)
    }
}