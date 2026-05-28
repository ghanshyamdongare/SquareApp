package com.gd.squareapp.ui.repodetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gd.squareapp.ui.repodetails.screen.RepoDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

private const val REPO_NAME = "repoName"

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {
    private val viewModel: RepoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repoName = arguments?.getString(REPO_NAME)
        repoName?.let {
            viewModel.fetchRepoDetails(it)
        }

        Log.d("RepoDetailsFragment", "repoName")
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                MaterialTheme {
                    RepoDetailsScreen(viewModel = viewModel)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(repoName: String) = RepoDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(REPO_NAME, repoName)
            }
        }
    }
}
