package com.onwd.challenge.ui.fragments

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.onwd.challenge.R
import com.onwd.challenge.databinding.FragmentDeviceDetailBinding
import com.onwd.challenge.ui.activities.MainActivity
import com.onwd.challenge.ui.viewmodels.DeviceFragmentViewModel
import com.onwd.devices.DeviceStatus
import com.onwd.devices.IDevice
import kotlin.math.roundToInt

class DeviceDetailFragment : Fragment() {

    private lateinit var binding: FragmentDeviceDetailBinding

    private lateinit var activity: MainActivity

    private var device: IDevice? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDeviceDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            device =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    it.getSerializable(DeviceFragmentViewModel.CURRENT_DEVICE, IDevice::class.java)
                else
                    it.getSerializable(DeviceFragmentViewModel.CURRENT_DEVICE) as IDevice
        }
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        device?.let {
            binding.deviceName.text = it.name
            activity.supportActionBar?.title = it.name
            binding.buttonClose.setOnClickListener {
                requireActivity().onBackPressed()
            }
            val batteryDrawable = when {
                it.batteryLevel > 0.75f -> R.drawable.ic_regular_battery_full
                it.batteryLevel < 0.25f -> R.drawable.ic_regular_battery_empty
                else -> R.drawable.ic_regular_battery_three_quarters
            }
            binding.deviceBatteryIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, batteryDrawable, null))
            binding.deviceStatusText.text =  it.status.name
            binding.deviceFirmwareVersion.text = it.firmwareVersion
            binding.deviceSerialNumber.text = it.serialId.toString()
            val colorId = when (it.status){
                DeviceStatus.Error -> R.color.red_error
                else -> R.color.green_ok
            }
            binding.deviceStatus.imageTintList = ColorStateList.valueOf(requireContext().getColor(colorId))
            binding.deviceBatteryValue.text = getString(R.string.fragment_device_detail_battery_value, (it.batteryLevel * 100f).roundToInt())
            binding.devicePic.setImageDrawable(ResourcesCompat.getDrawable(resources, it.typeDrawableId, null))
        }
    }
}