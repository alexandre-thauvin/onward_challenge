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
import androidx.viewpager2.widget.ViewPager2
import com.onwd.challenge.R
import com.onwd.challenge.databinding.FragmentDeviceBinding
import com.onwd.challenge.ui.DevicesAdapter
import com.onwd.challenge.ui.activities.MainActivity
import com.onwd.challenge.ui.viewmodels.DeviceFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class DeviceFragment : Fragment() {

    private val viewModel: DeviceFragmentViewModel by viewModels()

    private lateinit var binding: FragmentDeviceBinding

    private lateinit var activity: MainActivity

    private lateinit var adapter: DevicesAdapter

    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeviceBinding.inflate(inflater, container, false)
        activity = requireActivity() as MainActivity
        activity.hideBackArrow()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DevicesAdapter(arrayListOf())
        initViewPager()
        initListeners()
        collectFlow()
    }

    private fun initViewPager() {
        viewPager2 = binding.viewPager
        viewPager2.adapter = adapter
        val compositePageTransformer = CompositePageTransformer()
        val margin = 56
        compositePageTransformer.addTransformer(MarginPageTransformer((margin * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 5
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun initListeners() {
        binding.buttonSearch.setOnClickListener {
            viewModel.startSearchDevices()
        }
        binding.buttonOpen.setOnClickListener {
            viewModel.currentItem = viewPager2.currentItem
            activity.showBackArrow()
            val bundle = createBundle()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, DeviceDetailFragment::class.java, bundle)
                .addToBackStack(DeviceDetailFragment::class.java.name)
                .commit()
        }
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.devicesFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect {
                binding.deviceFound.text = getString(R.string.fragment_device_nb_device_found, it.size)
                adapter.update(it)
                if (viewModel.currentItem > -1) {
                    //TODO to improve add a listener when the viewPager has finished to render
                    delay(10)
                    viewPager2.setCurrentItem(viewModel.currentItem, true)
                }
            }
        }
    }

    private fun createBundle(): Bundle {
        val device = viewModel.getSelectedDevice()
        val bundle = Bundle()
        bundle.putSerializable(DeviceFragmentViewModel.CURRENT_DEVICE, device)
        return bundle
    }
}