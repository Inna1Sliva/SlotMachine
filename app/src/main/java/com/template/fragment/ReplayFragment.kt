package com.template.fragment

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.template.databinding.FragmentReplayBinding


class ReplayFragment : Fragment() {

   private var _binding:FragmentReplayBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReplayBinding.inflate(inflater, container, false)
        return binding.root

       var  requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottOk.setOnClickListener {
           // startActivity(Intent(requireContext(), GameActivity::class.java))
            val manager = activity?.supportFragmentManager?.beginTransaction()
            manager?.remove(this@ReplayFragment)?.commit()
            Toast.makeText(requireContext(), "ok click", Toast.LENGTH_LONG).show()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}