package com.fabiansc.tvmaze.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.fabiansc.tvmaze.ui.compose.theme.TVMazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment: Fragment() {
    private var idTvShow = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ARG_ID_TV_SHOW)?.let {
            idTvShow = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            TVMazeTheme {
                EpisodesScreen(id = idTvShow) {
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }
        }
    }

    companion object {
        private const val ARG_ID_TV_SHOW = "arg_id_tv_show"

        fun getBundleEpisodes(id: String) = bundleOf(ARG_ID_TV_SHOW to id)
    }
}