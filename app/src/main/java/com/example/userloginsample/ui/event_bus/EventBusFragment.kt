package com.example.userloginsample.ui.event_bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userloginsample.databinding.FragmentEventBusBinding
import io.reactivex.disposables.Disposable

class EventBusFragment : Fragment() {
    private val binding: FragmentEventBusBinding
        get() = _binding!!
    private var _binding: FragmentEventBusBinding? = null
    private var disposable: Disposable? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBusBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        disposable = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = EventBusFragment()
    }
}