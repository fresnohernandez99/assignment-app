package com.fresnohernandez.assignmentapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fresnohernandez.assignmentapp.R
import com.fresnohernandez.assignmentapp.common.collect
import com.fresnohernandez.assignmentapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel>()

    // region LifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiBind()
        collectState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // endregion LifeCycle

    private fun uiBind() {
        with(binding) {
            tvDontHaveAccountBtn.text = generateSignUpText()

            cardLoginVK.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, "https://vk.com".toUri())
                startActivity(intent)
            }

            cardLoginOk.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, "https://ok.com".toUri())
                startActivity(intent)
            }

            etEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val value = s.toString()
                    viewModel.updateState(email = value)
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            etPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val value = s.toString()
                    viewModel.updateState(password = value)
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }
    }

    private fun collectState() {
        with(binding) {
            viewModel.uiState.collect(viewLifecycleOwner) { state ->
                loginBtn.isEnabled =
                    isValidEmail(state.emailInput) && state.passwordInput.isNotEmpty()
            }
        }
    }

    // region Utility
    private fun generateSignUpText(): SpannableString {
        val fullText = getString(R.string.dont_have_account)
        val spannable = SpannableString(fullText)

        val target = getString(R.string.sign_up)
        val startIndex = fullText.indexOf(target)

        if (startIndex != -1) {
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.primaryLight)),
                startIndex,
                startIndex + target.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannable
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // endregion Utility
}