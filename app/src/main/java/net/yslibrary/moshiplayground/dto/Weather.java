package net.yslibrary.moshiplayground.dto;

import java.util.List;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Weather {

    public final List<Pinpoint> pinpointLocations;

    public final String link;

    public final List<Forecast> forecasts;

    public final Location location;

    public final String publicTime;

    public final Copyright copyright;

    public final String title;

    public final Description description;

    public Weather(Copyright copyright,
            List<Pinpoint> pinpointLocations, String link,
            List<Forecast> forecasts, Location location, String publicTime, String title,
            Description description) {
        this.copyright = copyright;
        this.pinpointLocations = pinpointLocations;
        this.link = link;
        this.forecasts = forecasts;
        this.location = location;
        this.publicTime = publicTime;
        this.title = title;
        this.description = description;
    }
}
