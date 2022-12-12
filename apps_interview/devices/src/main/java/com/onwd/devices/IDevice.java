package com.onwd.devices;

public interface IDevice {
    String getName();

    String getFirmwareVersion();

    float getBatteryLevel();

    DeviceStatus getStatus();

    @DeviceType int getType();
}
