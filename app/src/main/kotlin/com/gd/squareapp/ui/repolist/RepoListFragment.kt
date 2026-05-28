package com.gd.squareapp.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gd.squareapp.ui.repodetails.RepoDetailsFragment
import com.gd.squareapp.ui.repolist.event.RepoListEvent
import com.gd.squareapp.ui.repolist.screens.RepoListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoListFragment : Fragment() {
    private val viewModel: RepoListViewModel by viewModels()

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
                    RepoListScreen(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is RepoListEvent.OnListItemClick -> {
                            navigateToRepoDetail(event.repoName)
                        }

                        is RepoListEvent.OnBackClick -> {
                            requireActivity().finish()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToRepoDetail(repoName: String) {
        parentFragmentManager.beginTransaction()
            .replace(
                android.R.id.content,
                RepoDetailsFragment.newInstance(repoName),
                RepoDetailsFragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepoListFragment()
    }
}
