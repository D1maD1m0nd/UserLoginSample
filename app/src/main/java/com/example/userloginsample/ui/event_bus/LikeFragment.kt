package com.example.userloginsample.ui.event_bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userloginsample.databinding.FragmentLikeBinding
import com.example.userloginsample.domain.Like
import com.example.userloginsample.ui.App


class LikeFragment : Fragment() {
    private val binding: FragmentLikeBinding
        get() = _binding!!
    private var _binding: FragmentLikeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.likeButton.setOnClickListener {
            App.INSTANCE.eventBus.post(Like())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}