package com.onwd.devices;

import java.util.Random;

class DeviceFactory {
    private DeviceFactory() {
    }

    private static final String[] mFirmwareVersions = new String[]{
            "1.2.4", "2.0.1", "1.9.2-RC1", "2.1.0a"
    };

    public static IDevice createRandomDevice(Random random) {
        String deviceName = "Device-" + random.nextInt(5000);
        String firmwareVersion = mFirmwareVersions[random.nextInt(mFirmwareVersions.length)];

        return new Device(deviceName, firmwareVersion, random.nextFloat(), getRandomDeviceStatus(random), random.nextInt(10000), getRandomDeviceType(random));
    }

    private static DeviceStatus getRandomDeviceStatus(Random random) {
        return random.nextFloat() > 0.2f ? DeviceStatus.Ok : DeviceStatus.Error;
    }

    private static @DeviceType
    int getRandomDeviceType(Random random) {
        return random.nextFloat() > 0.4 ? DeviceType.TYPE_A : DeviceType.TYPE_B;
    }

}