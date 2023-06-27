package com.fabiansc.tvmaze.ui.home

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.core.isTablet
import com.fabiansc.tvmaze.ui.compose.theme.TVMazeTheme
import com.fabiansc.tvmaze.ui.detail.DetailFragment.Companion.getBundleDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireContext().isTablet()) activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            TVMazeTheme {
                HomeScreen { id ->
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        getBundleDetail(id)
                    )
                }
            }
        }
    }
}