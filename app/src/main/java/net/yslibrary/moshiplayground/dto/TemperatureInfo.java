package net.yslibrary.moshiplayground.dto;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class TemperatureInfo {

    public final float celsius;

    public final float fahrenheit;

    public TemperatureInfo(float celsius, float fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }
}
