package com.guri.loginkt_new.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentMypageBinding

class mypageFragment : Fragment(R.layout.fragment_mypage) {
    private var _binding: FragmentMypageBinding? = null
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val view = binding.root

        loadUserData()

        binding.btnModify.setOnClickListener {
            updateUserData()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, homeFragment()).commit()
        }

        return view
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = database.getReference("Users").child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // 데이터를 text로 설정
                    binding.etMpUsername.setText(dataSnapshot.child("username").value.toString())
                    binding.etMpEmail.setText(dataSnapshot.child("email").value.toString())
                    binding.etMpSchool.setText(dataSnapshot.child("university").value.toString())
                    binding.etMpGrade.setText(dataSnapshot.child("major").value.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle possible errors.
                }
            })
        }
    }

    private fun updateUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = database.getReference("Users").child(userId)

            // 새로운 값을 읽어옴
            val newUsername = binding.etMpUsername.text.toString()
            val newEmail = binding.etMpEmail.text.toString()
            val newUniversity = binding.etMpSchool.text.toString()
            val newMajor = binding.etMpGrade.text.toString()

            // Firebase에 업데이트
            if (newUsername.isNotEmpty()) {
                userReference.child("username").setValue(newUsername)
            }
            if (newEmail.isNotEmpty()) {
                userReference.child("email").setValue(newEmail)
            }
            if (newUniversity.isNotEmpty()) {
                userReference.child("university").setValue(newUniversity)
            }
            if (newMajor.isNotEmpty()) {
                userReference.child("major").setValue(newMajor)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
