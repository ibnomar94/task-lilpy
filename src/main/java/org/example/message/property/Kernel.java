package org.example.message.property;

import java.util.Arrays;
import java.util.Optional;

public enum Kernel {
    MASTERCARD("02","Mastercard"),
    VISA("03","Visa"),
    AMEX("04","Amex");

    private final String kernelValue;
    private final String kernelName;

    Kernel(String value, String name){
        this.kernelValue = value;
        this.kernelName = name;
    }

    public String getKernelValue() {
        return kernelValue;
    }

    public String getKernelName() {
        return kernelName;
    }

    public static Optional<Kernel> getByValue(String value){
        return Arrays.stream(Kernel.values()).filter(kernel -> kernel.kernelValue.equals(value)).findFirst();
    }

}
