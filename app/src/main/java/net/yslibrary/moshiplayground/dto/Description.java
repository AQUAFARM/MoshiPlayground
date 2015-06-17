package net.yslibrary.moshiplayground.dto;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Description {

    public final String text;

    public final String publicTime;

    public Description(String publicTime, String text) {
        this.publicTime = publicTime;
        this.text = text;
    }
}
