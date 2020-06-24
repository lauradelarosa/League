package com.delarosa.splash

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.delarosa.common.utils.Target
import com.delarosa.common.utils.canNavigate
import com.delarosa.common.utils.navigateUriWithDefaultOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(2000)
            canNavigate(Target.League)?.let { deepLink ->
                view.findNavController().navigateUriWithDefaultOptions(
                    Uri.parse(deepLink)
                )
            }
        }
    }
}