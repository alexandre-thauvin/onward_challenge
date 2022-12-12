package com.onwd.challenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.onwd.challenge.R
import com.onwd.challenge.ui.viewmodels.DeviceFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceFragment : Fragment() {

    private val viewModel: DeviceFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_device, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO viewBinding
    }

}