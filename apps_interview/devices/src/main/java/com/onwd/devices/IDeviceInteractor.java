package com.onwd.devices;

public interface IDeviceInteractor {
    void startSearch();
    void registerListener(IDeviceFoundListener deviceFoundListener);
}
