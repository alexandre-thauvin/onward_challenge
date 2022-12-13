package com.onwd.challenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.onwd.challenge.R
import com.onwd.challenge.databinding.DeviceItemBinding
import com.onwd.devices.DeviceType
import com.onwd.devices.IDevice

internal class DevicesAdapter(private val mDevices: ArrayList<IDevice>) :
    RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val viewBinding = DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(viewBinding)
    }

    override fun onBindViewHolder(viewHolder: DeviceViewHolder, position: Int) {
        val device: IDevice = mDevices[position]
        viewHolder.bind(device)
    }

    class DeviceViewHolder(private val viewBinding: DeviceItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(device: IDevice) = with(viewBinding.root) {
            viewBinding.deviceName.text = device.name
            val drawableId = when (device.type){
                DeviceType.TYPE_A -> R.drawable.ic_laptop
                DeviceType.TYPE_B -> R.drawable.ic_mobile
                else -> R.drawable.ic_watch
            }
            viewBinding.devicePic.setImageDrawable(ResourcesCompat.getDrawable(resources, drawableId, null))
        }
    }

    override fun getItemCount() = mDevices.size

    fun update(devices: List<IDevice>){
        mDevices.clear()
        mDevices.addAll(devices)
        notifyDataSetChanged()
    }

}