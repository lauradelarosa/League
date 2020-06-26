package com.delarosa.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.delarosa.common.presentation.Navigation
import com.delarosa.common.utils.Target
import com.delarosa.common.utils.canNavigate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private var navigation: Navigation? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as? Navigation
            ?: throw IllegalStateException("Context needs to implement Navigation: $context")
    }

    override fun onDetach() {
        super.onDetach()
        navigation = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(2000)
            canNavigate(Target.League)?.let { deepLink ->
                navigation?.navigate(deepLink)
            }
        }
    }
}