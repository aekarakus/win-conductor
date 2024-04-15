package io.aekarakus.exceptions;

public class DeviceNotFoundException extends RuntimeException{

    public DeviceNotFoundException(){
        super("Device is not registered.");
    }
}
