package io.aekarakus.exceptions;

public class DeviceNotReachableException extends RuntimeException{

    public DeviceNotReachableException(){
        super("Device could not be reached.");
    }
}
