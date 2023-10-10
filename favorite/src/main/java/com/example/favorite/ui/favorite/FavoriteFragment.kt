package com.example.favorite.ui.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artworkapp.ui.detail.DetailArtActivity
import com.example.artworkapp.ui.di.ModuleDependencies
import com.example.core.ui.ArtworkAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.favorite.ui.factory.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var artworkAdapter: ArtworkAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    ModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

            favoriteViewModel.favoriteArtwork.observe(viewLifecycleOwner) { dataArtwork ->
                artworkAdapter.setData(dataArtwork)
                binding.viewEmpty.root.visibility =
                    if (dataArtwork.isNotEmpty()) View.GONE else View.VISIBLE
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