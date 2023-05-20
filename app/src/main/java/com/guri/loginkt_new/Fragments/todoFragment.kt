package com.guri.loginkt_new.Fragments

import android.R
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.guri.loginkt_new.databinding.FragmentTodoBinding


class todoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTodoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetView = layoutInflater.inflate(com.guri.loginkt_new.R.layout.bottomsheetlayout, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.findViewById<View>(com.guri.loginkt_new.R.id.btn_add2).setOnClickListener {
            binding.btnTodoDate.setOnClickListener {
                val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, day ->
                    val date_ = year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
                    binding.tvAfTodoDuedate.setText(date_)

//                        year.toString() + "/" + (month + 1).toString() + "/" + day.toString()
                }, year, month, day)
                datePickerDialog.show()
            }
            View.OnClickListener { dismiss()}

//            bottomSheetDialog.dismiss()
        }
//        view.findViewById(R.id.btn_close).setOnClickListener(View.OnClickListener { dismiss() })
    }


}