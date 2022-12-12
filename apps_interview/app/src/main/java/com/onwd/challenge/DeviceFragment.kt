package com.onwd.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onwd.devices.IDeviceInteractor

class DeviceFragment(
    private val deviceInteractor: IDeviceInteractor
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_device, container, false)

}