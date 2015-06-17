package net.yslibrary.moshiplayground.dto;

import net.yslibrary.moshiplayground.enums.DateLabel;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Forecast {

    public final DateLabel dateLabel;

    public final String telop;

    public final String date;

    public final Temperature temperature;

    public final ImageInfo image;

    public Forecast(String date, DateLabel dateLabel, String telop,
            Temperature temperature, ImageInfo image) {
        this.date = date;
        this.dateLabel = dateLabel;
        this.telop = telop;
        this.temperature = temperature;
        this.image = image;
    }
}
