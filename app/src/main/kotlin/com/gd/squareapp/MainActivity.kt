package com.gd.squareapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.gd.squareapp.ui.repolist.RepoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    android.R.id.content,
                    RepoListFragment.newInstance(),
                    RepoListFragment.Companion::class.simpleName
                )
                .commit()
        }
    }
}
