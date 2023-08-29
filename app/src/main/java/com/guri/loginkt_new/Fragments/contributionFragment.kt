package com.guri.loginkt_new.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.firebase.database.*

class contributionFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentContributionBinding? = null
    private val binding get() = _binding!!

    private var calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour = calendar.get(Calendar.HOUR)
    private var minute = calendar.get(Calendar.MINUTE)

    private var add_date: String? = null
    private var add_time: String? = null
    private var add_title: String? = null
    private var add_detail: String? = null
    private var add_item: ListVertical? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContributionBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnAdd.setOnClickListener {
            val bottomSheetView = layoutInflater.inflate(R.layout.fragment_todo, null)
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetDialog.setContentView(bottomSheetView)

            bottomSheetView.findViewById<View>(R.id.btn_todo_date).setOnClickListener {
                val datePickerDialog =
                    DatePickerDialog(requireContext(), { _, year, month, day ->
                        val date_ = "$year/${month + 1}/$day"
                        add_date = date_
                        bottomSheetView.findViewById<TextView>(R.id.tv_af_todo_duedate).text = date_
                    }, year, month, day)

                datePickerDialog.show()
            }

            bottomSheetView.findViewById<View>(R.id.btn_todo_time).setOnClickListener {
                val timePickerDialog =
                    TimePickerDialog(requireContext(), { _, hour, minute ->
                        val timeStr = if (hour > 12) {
                            val newHour = hour - 12
                            "오후 $newHour 시 $minute 분"
                        } else {
                            "오전 $hour 시 $minute 분"
                        }
                        add_time = "$add_date $timeStr"
                        bottomSheetView.findViewById<TextView>(R.id.tv_af_todo_duetime).text = timeStr
                    }, hour, minute, false)

                timePickerDialog.show()
            }

            bottomSheetView.findViewById<View>(R.id.btn_add2).setOnClickListener {
                add_title = (bottomSheetView.findViewById<TextView>(R.id.et_todo_name) as? TextView)?.text.toString()
                add_detail = (bottomSheetView.findViewById<TextView>(R.id.et_todo_detail) as? TextView)?.text.toString()

                val todoMap = mapOf(
                    "title" to add_title,
                    "detail" to add_detail,
                    "time" to add_time
                )
                FirebaseDatabase.getInstance().reference.child("todos").push().setValue(todoMap)

                bottomSheetDialog.dismiss()
            }

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

        val databaseReference = FirebaseDatabase.getInstance().reference.child("todos")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val verList = arrayListOf<ListVertical>()
                for (snapshot in dataSnapshot.children) {
                    val title = snapshot.child("title").value as? String ?: ""
                    val detail = snapshot.child("detail").value as? String ?: ""
                    val time = snapshot.child("time").value as? String ?: ""

                    verList.add(ListVertical(title, detail, time))
                }

                binding.recyclerVertical.adapter = ListVerticalAdapter(verList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
            }
        })

        binding.recyclerHorizon.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        binding.recyclerHorizon.setHasFixedSize(true)
        binding.recyclerHorizon.adapter = ListHorizontalAdapter(horList)

        binding.recyclerVertical.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerVertical.setHasFixedSize(true)
    }
}
