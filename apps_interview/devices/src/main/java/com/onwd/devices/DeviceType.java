package com.onwd.devices;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;

@Retention(SOURCE)
public @interface DeviceType {
    int TYPE_A = 0;
    int TYPE_B = 1;
}
