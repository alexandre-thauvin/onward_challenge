package com.onwd.devices;

import java.util.Random;

public class DeviceInteractorStub implements IDeviceInteractor {
    private IDeviceFoundListener mDeviceFoundListener;

    @Override
    public void startSearch() {
        Random random = new Random();

        int numberOfDevicesToGenerate = random.nextInt(4);

        new Thread(() -> {

            try {
                Thread.sleep(random.nextInt(1000)); // Simulate search time
            } catch (InterruptedException ignored) {
            }

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
