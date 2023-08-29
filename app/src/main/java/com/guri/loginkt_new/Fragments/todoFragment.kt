package com.guri.loginkt_new.Fragments

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.guri.loginkt_new.databinding.FragmentTodoBinding
import com.google.firebase.database.FirebaseDatabase

class todoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour = calendar.get(Calendar.HOUR)
    private var minute = calendar.get(Calendar.MINUTE)

    // 변수 선언
    private var add_date: String? = null
    private var add_time: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTodoDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, day ->
                add_date = "$year/${month + 1}/$day"
                binding.tvAfTodoDuedate.text = add_date
            }, year, month, day)
            datePickerDialog.show()
        }

        binding.btnTodoTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(requireContext(), { _, hour, minute ->
                add_time = when {
                    hour == 0 -> "오전 12시 $minute 분"
                    hour < 12 -> "오전 $hour 시 $minute 분"
                    hour == 12 -> "오후 12시 $minute 분"
                    else -> "오후 ${hour - 12}시 $minute 분"
                }

                binding.tvAfTodoDuetime.text = add_time
            }, hour, minute, false)
            timePickerDialog.show()
        }

        binding.btnAdd2.setOnClickListener {
            val add_title = binding.etTodoName.text.toString().trim()
            val add_detail = binding.etTodoDetail.text.toString().trim()

            // Firebase Realtime Database에 저장
            if (add_title.isNotEmpty() && add_detail.isNotEmpty() && add_date != null && add_time != null) {
                val todoMap = mapOf(
                    "title" to add_title,
                    "detail" to add_detail,
                    "date" to add_date,
                    "time" to add_time
                )
                FirebaseDatabase.getInstance().reference.child("todos").push().setValue(todoMap)
            }

            dismiss()
        }

    }
}
