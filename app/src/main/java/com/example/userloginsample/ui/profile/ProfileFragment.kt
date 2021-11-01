package com.example.userloginsample.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.userloginsample.databinding.FragmentProfileBinding
import com.example.userloginsample.domain.User
import com.example.userloginsample.impl.UserRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val binding: FragmentProfileBinding
        get() = _binding!!

    private val userRepo = UserRepositoryImpl()

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.repoButton.setOnClickListener {
            userRepo.addUsers()
        }
        compositeDisposable.add(
            userRepo.users
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.loginTextView.text = it.toString()
                }
        )
    }

    private fun init() {
        binding.loginTextView.text = user?.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_USER = "user"
        @JvmStatic
        fun newInstance(user: User) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
    }
}