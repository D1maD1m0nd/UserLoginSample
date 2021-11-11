package com.example.userloginsample.ui.event_bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userloginsample.databinding.FragmentDislikeBinding
import com.example.userloginsample.domain.Dislike
import com.example.userloginsample.ui.App


class DislikeFragment : Fragment() {
    private val binding: FragmentDislikeBinding
        get() = _binding!!
    private var _binding: FragmentDislikeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDislikeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dislikeButton.setOnClickListener {
            App.INSTANCE.eventBus.post(Dislike())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}