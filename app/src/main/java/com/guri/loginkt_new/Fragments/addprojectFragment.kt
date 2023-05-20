package com.guri.loginkt_new.Fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentAddprojectBinding

class addprojectFragment : Fragment() {
    private var _binding: FragmentAddprojectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var dateText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentAddprojectBinding.inflate(inflater, container, false)
        val view = binding.root

        //
        binding.btnRegisterr.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, homeFragment()).commit()
        }

        //버튼 클릭 이벤트
        binding.todoDatepicker.setOnClickListener {

            //달력 보여주기 함수
            showDatePicker()
        }






        return view
    }


    /**
     * 달력 보여주기 함수
     */
    private fun showDatePicker(){

        //오늘 날짜 변수에 담기
        val calendar: Calendar = Calendar.getInstance()

        val iYear = calendar.get(Calendar.YEAR)//년
        val iMonth = calendar.get(Calendar.MONTH)//월
        val iDay = calendar.get(Calendar.DAY_OF_MONTH)//일

        //달력 호출
        val datePicker: DatePickerDialog = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

                //1월은 0부터 시작해서 +1 해준다
                val tMonth: Int = month + 1

                //년 월 일
                val date: String = "$year / $tMonth / $day"

                //화면에 선택한 날짜 보여주기
                binding.dateText.text = date
            }, iYear, iMonth, iDay)

        //달력 호출
        datePicker.show()
    }



}