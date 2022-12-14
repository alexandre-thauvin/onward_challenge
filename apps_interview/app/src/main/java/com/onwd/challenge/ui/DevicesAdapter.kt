package com.onwd.challenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.onwd.challenge.databinding.DeviceItemBinding
import com.onwd.devices.IDevice

internal class DevicesAdapter(private val mDevices: ArrayList<IDevice>) :
    RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DeviceViewHolder, position: Int) {
        val device: IDevice = mDevices[position]
        viewHolder.bind(device)
    }

    class DeviceViewHolder(private val viewBinding: DeviceItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(device: IDevice) = with(viewBinding.root) {
            viewBinding.deviceName.text = device.name
            viewBinding.devicePic.setImageDrawable(ResourcesCompat.getDrawable(resources, device.typeDrawableId, null))
        }
    }

    override fun getItemCount() = mDevices.size

    fun update(devices: List<IDevice>){
        mDevices.clear()
        mDevices.addAll(devices)
        notifyDataSetChanged()
    }

}