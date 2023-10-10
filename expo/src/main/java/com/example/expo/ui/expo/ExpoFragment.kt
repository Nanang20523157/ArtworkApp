package com.example.expo.ui.expo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.artworkapp.ui.di.ModuleDependencies
import com.example.core.data.Resource
import com.example.expo.R
import com.example.expo.databinding.FragmentExpoBinding
import com.example.expo.di.DaggerExpoComponent
import com.example.expo.ui.factory.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class ExpoFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val expoViewModel: ExpoViewModel by viewModels {
        factory
    }
    private var _binding: FragmentExpoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerExpoComponent.builder()
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
        _binding = FragmentExpoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            expoViewModel.artExpo.observe(viewLifecycleOwner) { artwork ->
                if (artwork != null) {
                    when (artwork) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvExpo.text =
                                getString(R.string.expo_dummy, artwork.data?.get(0)?.artTitle)
                            binding.tvExpo.setTextColor(resources.getColor(com.example.core.R.color.tosca))
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvExpo.text =
                                getString(com.example.core.R.string.something_wrong)
                            binding.tvExpo.setTextColor(resources.getColor(com.example.core.R.color.child_red))
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}