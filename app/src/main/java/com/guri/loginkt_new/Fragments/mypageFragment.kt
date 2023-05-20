package com.guri.loginkt_new.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guri.loginkt_new.R
import com.guri.loginkt_new.databinding.FragmentMapBinding
import com.guri.loginkt_new.databinding.FragmentMypageBinding

class mypageFragment : Fragment(R.layout.fragment_mypage) {
    private var _binding: FragmentMypageBinding? = null

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

        binding.btnModify.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentCotainer, homeFragment()).commit()
        }

        return view
    }
}