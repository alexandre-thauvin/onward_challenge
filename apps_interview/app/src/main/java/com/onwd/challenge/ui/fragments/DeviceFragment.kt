package com.onwd.challenge.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.onwd.challenge.R
import com.onwd.challenge.databinding.FragmentDeviceBinding
import com.onwd.challenge.ui.DevicesAdapter
import com.onwd.challenge.ui.viewmodels.DeviceFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.abs

@AndroidEntryPoint
class DeviceFragment : Fragment() {

    private val viewModel: DeviceFragmentViewModel by viewModels()

    private lateinit var binding: FragmentDeviceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DevicesAdapter(arrayListOf())
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        val compositePageTransformer = CompositePageTransformer()
        val margin = 56
        compositePageTransformer.addTransformer(MarginPageTransformer((margin * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)
        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 5
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
        }
        binding.buttonSearch.setOnClickListener {
            viewModel.startSearchDevices()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.devicesFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect {
                Timber.d("devices list: $it")
                binding.deviceFound.text = getString(R.string.fragment_device_nb_device_found, it.size.toString())
                adapter.update(it)
            }
        }
    }

}