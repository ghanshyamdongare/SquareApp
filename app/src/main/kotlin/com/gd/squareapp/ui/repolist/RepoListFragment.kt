package com.gd.squareapp.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import com.gd.squareapp.ui.repolist.screens.RepoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                MaterialTheme {
                    val viewModel: RepoListViewModel = hiltViewModel()
                    RepoListScreen(viewModel, onBackClick = {
                        requireActivity().finish()
                    }
                    )
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepoListFragment()
    }
}
