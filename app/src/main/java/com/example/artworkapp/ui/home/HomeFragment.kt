package com.example.artworkapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artworkapp.databinding.FragmentHomeBinding
import com.example.artworkapp.ui.detail.DetailArtActivity
import com.example.core.R
import com.example.core.data.Resource
import com.example.core.ui.ArtworkAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var artworkAdapter: ArtworkAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            artworkAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailArtActivity::class.java)
                intent.putExtra(DetailArtActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.artwork.observe(viewLifecycleOwner) { artwork ->
                if (artwork != null) {
                    when (artwork) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.viewError.root.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.GONE
                            binding.rvArtwork.visibility = View.VISIBLE
                            artworkAdapter.setData(artwork.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvArtwork.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                artwork.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvArtwork) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = artworkAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvArtwork.adapter = null
        _binding = null
    }

}