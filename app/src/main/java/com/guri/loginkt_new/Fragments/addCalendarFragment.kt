package com.guri.loginkt_new.Fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentAddCalendarBinding
import com.guri.loginkt_new.recyclerView.InReturnStateRecyclerAdapter

class addCalendarFragment : Fragment(R.layout.fragment_add_calendar) {

    private var _binding: FragmentAddCalendarBinding? = null
    private val binding get() = _binding!!

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    private val NO_SEARCH : String = ""
    private var startDate : String = NO_SEARCH // 선택 날짜 1
    private var endDate : String = NO_SEARCH // 선택 날짜 2
    private var itemNumstring : String = NO_SEARCH // 품목번호
    private var customerNumString : String = NO_SEARCH // 거래처번호
    private var searchData : MutableList<InReturnStateRecyclerAdapter> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCalendarBinding.inflate(inflater, container, false)
        val view = binding.root

        // 버튼 누르면 bfselecitonfragment로 넘어가게
        binding.meetingAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, bfselectionFragment()).commit()

        }

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED

        binding.searchDate.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, day ->
                    val date_ =
                        year.toString() + "/" + (month + 1).toString() + "/" + day.toString()

                    binding.dateText.setText(date_)
                }, year, month, day)

            datePickerDialog.show()
        }

        binding.searchDate2.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, day ->
                    val date_2 =
                        year.toString() + "/" + (month + 1).toString() + "/" + day.toString()

                    binding.dateText2.setText(date_2)
                }, year, month, day)

            datePickerDialog.show()
        }



        return view

    }
}

