package com.mashup.presentation.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentSignalContentBinding
import com.mashup.presentation.signal.compose.SignalContentScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class SignalContentFragment :
    BaseFragment<FragmentSignalContentBinding>(R.layout.fragment_signal_content) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            SignalContentScreen(
                navigateToSignalKeyword = { navigate(R.id.action_signalContent_to_signalKeyword) },
                navigateUp = { navigateUp() }
            )
        }
    }
}