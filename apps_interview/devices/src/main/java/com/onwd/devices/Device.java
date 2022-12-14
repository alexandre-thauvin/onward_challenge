package com.onwd.devices;

class Device implements IDevice {

    private final String mName;
    private final String mFirmwareVersion;
    private final float mBatteryLevel;
    private final DeviceStatus mStatus;

    private final int mSerialId;
    private final int mType;

    Device(String name, String firmwareVersion, float batteryLevel, DeviceStatus status, int serialId, @DeviceType int type) {

        this.mName = name;
        this.mFirmwareVersion = firmwareVersion;
        this.mBatteryLevel = batteryLevel;
        this.mStatus = status;
        this.mSerialId = serialId;
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
    public int getSerialId() {
        return mSerialId;
    }

    @Override
    @DeviceType
    public int getType() {
        return mType;
    }

    @Override
    public int getTypeDrawableId() {
        if (mType == DeviceType.TYPE_A) {
            return R.drawable.ic_regular_laptop;
        } else {
            return R.drawable.ic_regular_mobile;
        }
    }
}