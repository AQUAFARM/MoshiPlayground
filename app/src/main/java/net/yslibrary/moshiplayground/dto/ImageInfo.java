package net.yslibrary.moshiplayground.dto;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class ImageInfo {

    public final int width;

    public final int height;

    public final String url;

    public final String title;

    public ImageInfo(int height, int width, String url, String title) {
        this.height = height;
        this.width = width;
        this.url = url;
        this.title = title;
    }
}
