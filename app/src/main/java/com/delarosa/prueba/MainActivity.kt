package com.delarosa.prueba

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.delarosa.common.presentation.Navigation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavHostFragment.findNavController(navHostFragment).navigate(R.id.splashFragment)
    }

    override fun onBackPressed() {
        when (NavHostFragment.findNavController(navHostFragment).currentDestination?.id) {
            R.id.leagueFragment -> finish()
            else -> super.onBackPressed()
        }
    }

    override fun navigate(deepLink: String?) {
        NavHostFragment.findNavController(navHostFragment).navigate( Uri.parse(deepLink))
    }
}

