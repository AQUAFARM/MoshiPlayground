package net.yslibrary.moshiplayground.dto;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Temperature {

    public final TemperatureInfo min;

    public final TemperatureInfo max;

    public Temperature(TemperatureInfo max, TemperatureInfo min) {
        this.max = max;
        this.min = min;
    }
}
