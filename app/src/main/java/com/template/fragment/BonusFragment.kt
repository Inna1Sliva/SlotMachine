package com.template.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.template.databinding.FragmentBonusBinding


class BonusFragment : Fragment() {

private var _binding :FragmentBonusBinding?=null
    private  val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentBonusBinding.inflate(inflater, container, false)
        return binding.root
        var requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottOk.setOnClickListener {

           val manager = activity?.supportFragmentManager?.beginTransaction()
            manager?.remove(this@BonusFragment)?.commit()
            Toast.makeText(requireContext(), "ok click", Toast.LENGTH_LONG).show()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}