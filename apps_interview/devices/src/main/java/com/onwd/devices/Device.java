package com.onwd.devices;

class Device implements IDevice {

    private final String mName;
    private final String mFirmwareVersion;
    private final float mBatteryLevel;
    private final DeviceStatus mStatus;
    private final int mType;

    Device(String name, String firmwareVersion, float batteryLevel, DeviceStatus status, @DeviceType int type) {

        this.mName = name;
        this.mFirmwareVersion = firmwareVersion;
        this.mBatteryLevel = batteryLevel;
        this.mStatus = status;
        this.mType = type;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getFirmwareVersion() {
        return mFirmwareVersion;
    }

    @Override
    public float getBatteryLevel() {
        return mBatteryLevel;
    }

    @Override
    public DeviceStatus getStatus() {
        return mStatus;
    }

    @Override
    @DeviceType
    public int getType() {
        return mType;
    }
}
