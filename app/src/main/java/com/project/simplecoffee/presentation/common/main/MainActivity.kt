package com.project.simplecoffee.presentation.common.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.project.simplecoffee.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.Fragment
import com.project.simplecoffee.R
import com.project.simplecoffee.utils.common.makeToast
import com.project.simplecoffee.presentation.auth.AuthActivity
import javax.inject.Inject


@SuppressLint("UseCompatLoadingForDrawables")
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContainer {

    @Inject
    lateinit var mainVM: MainVM
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            mainVM.loadFragment(AllMainFragment.Menu)
        }
        val parent = this
        binding.apply {
            viewModel = mainVM
            lifecycleOwner = parent
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_menu -> {
                        mainVM.loadFragment(AllMainFragment.Menu)
                        return@setOnItemSelectedListener true
                    }
                    R.id.action_order -> {
                        mainVM.loadFragment(AllMainFragment.CurrentOrder)
                        return@setOnItemSelectedListener true
                    }
                    R.id.action_more -> {
                        mainVM.loadFragment(AllMainFragment.Setting)
                        return@setOnItemSelectedListener true
                    }
                    else -> {
                        return@setOnItemSelectedListener false
                    }
                }
            }
        }
    }

    override fun loadDrawable(id: Int): Drawable =
        resources.getDrawable(id, null)


    override fun showMessage(message: String) {
        makeToast(message)
    }

    override fun loadFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container, fragment)
            if (isAddToBackStack) addToBackStack(null)
            commit()
        }
    }

    override fun backToPreviousFragment() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 0)
                popBackStack()
        }
    }

    override fun onSignIn() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun toggleNavigationBottom(hide: Boolean) {
        binding.bottomNavigation.visibility = if (hide) View.GONE else View.VISIBLE
    }

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 0) {
                popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }
}