package com.fabiansc.tvmaze.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.COLOR_SCHEME_DARK
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.core.loadImage
import com.fabiansc.tvmaze.databinding.DetailFragmentBinding
import com.fabiansc.tvmaze.domain.model.TvShowDetailModel
import com.fabiansc.tvmaze.ui.compose.composables.PersonItem
import com.fabiansc.tvmaze.ui.compose.composables.TvShowItem
import com.fabiansc.tvmaze.ui.compose.theme.TVMazeTheme
import com.fabiansc.tvmaze.ui.episodes.EpisodesFragment.Companion.getBundleEpisodes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var mBinding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModels()
    private var link: String = ""
    private val customTabIntent = CustomTabsIntent.Builder()
    private var idTvShow = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ARG_TV_SHOW_ID)?.let { id ->
            viewModel.getDetailTvShow(id)
            idTvShow = id
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DetailFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiState()
        initElements()
    }

    private fun initElements() {
        with(mBinding) {
            toolBar.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
            btnSite.setOnClickListener {
                if (link.isNotEmpty()) customTabIntent.build()
                    .launchUrl(mBinding.root.context, Uri.parse(link))
                else Toast.makeText(
                    requireContext(),
                    getString(R.string.txt_message_site),
                    Toast.LENGTH_SHORT
                ).show()
            }
            btnEpisodes?.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_episodesFragment, getBundleEpisodes(idTvShow))
            }
        }
        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(requireContext(), R.color.grayDark))
            .build()
        customTabIntent.setColorSchemeParams(COLOR_SCHEME_DARK, colorSchemeParams)
    }

    private fun initUiState() {
        viewModel.uiDetailState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { state ->
            when (state) {
                is DetailViewState.Loading -> {
                    mBinding.pbLoading.visibility = View.VISIBLE
                }
                is DetailViewState.Success -> {
                    initInformation(state.data)
                    link = state.data.link
                }
                is DetailViewState.Error -> {

                }
                else -> {

                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initInformation(tvShowDetailModel: TvShowDetailModel) {
        with(mBinding) {
            pbLoading.visibility = View.GONE
            container.visibility = View.VISIBLE
            ivPoster.loadImage(tvShowDetailModel.image)
            tvName.text = tvShowDetailModel.name
            tvNetwork.text = tvShowDetailModel.network
            tvRating.text = tvShowDetailModel.rating.toString()
            tvSummary.text = tvShowDetailModel.summary
            tvGenders.text = tvShowDetailModel.genders
            tvSchedule.text = tvShowDetailModel.dates

            cvCast.setContent {
                TVMazeTheme {
                    if(tvShowDetailModel.persons.isNotEmpty()) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(tvShowDetailModel.persons) { person ->
                                PersonItem(person)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val ARG_TV_SHOW_ID = "arg_tv_show_id"
        fun getBundleDetail(id: String) = bundleOf(ARG_TV_SHOW_ID to id)
    }
}