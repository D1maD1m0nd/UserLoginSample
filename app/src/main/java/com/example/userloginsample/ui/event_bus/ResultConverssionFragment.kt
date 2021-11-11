package com.example.userloginsample.ui.event_bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userloginsample.databinding.FragmentResultConverssionBinding
import com.example.userloginsample.domain.Dislike
import com.example.userloginsample.domain.Like
import com.example.userloginsample.ui.App
import io.reactivex.disposables.Disposable

class ResultConverssionFragment : Fragment() {
    private val binding: FragmentResultConverssionBinding
        get() = _binding!!
    private var _binding: FragmentResultConverssionBinding? = null
    private var disposable: Disposable? = null
    private var dislikeCounter = 0
    private var likeCounter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultConverssionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscriber()
    }

    private fun initSubscriber() {
        disposable = App.INSTANCE.eventBus.bus.subscribe {
            when (it) {
                is Dislike -> {
                    dislikeCounter++
                    binding.dislikeCounterTextView.text = dislikeCounter.toString()
                }
                is Like -> {
                    likeCounter++
                    binding.likeCounterTextView.text = likeCounter.toString()
                }
            }
        }
    }

    override fun onDestroyView() {
        disposable = null
        _binding = null
        super.onDestroyView()
    }

}