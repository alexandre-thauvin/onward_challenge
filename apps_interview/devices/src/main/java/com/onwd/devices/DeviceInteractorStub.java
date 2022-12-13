package com.onwd.devices;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DeviceInteractorStub implements IDeviceInteractor {
    private IDeviceFoundListener mDeviceFoundListener;

    @Override
    public void startSearch() {
        Random random = new Random();

        // Changed to use a range and do not get 0 in order to emit at least 1 device
        int numberOfDevicesToGenerate = ThreadLocalRandom.current().nextInt(1, 7);

        new Thread(() -> {

            try {
                Thread.sleep(random.nextInt(1000)); // Simulate search time
            } catch (InterruptedException ignored) {
            }

            // As said above if there is numberOfDevices == 0 so nothing is sent and there is no way to know the end
            // Could implement error handling in the lib but missing some time
            for (int i = 0; i < numberOfDevicesToGenerate; i++) {
                if (mDeviceFoundListener != null) {
                    mDeviceFoundListener.deviceFound(DeviceFactory.createRandomDevice(random));
                }
            }
        }).start();
    }

    @Override
    public void registerListener(IDeviceFoundListener deviceFoundListener) {
        this.mDeviceFoundListener = deviceFoundListener;
    }
}