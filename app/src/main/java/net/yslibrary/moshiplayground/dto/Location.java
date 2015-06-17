package net.yslibrary.moshiplayground.dto;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Location {

    public final String city;

    public final String area;

    public final String prefecture;

    public Location(String area, String city, String prefecture) {
        this.area = area;
        this.city = city;
        this.prefecture = prefecture;
    }
}
