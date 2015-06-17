package net.yslibrary.moshiplayground.dto;

import java.util.List;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class Copyright {

    public final List<Provider> provider;

    public final String link;

    public final String title;

    public final ImageInfo image;

    public Copyright(ImageInfo image,
            List<Provider> provider, String link, String title) {
        this.image = image;
        this.provider = provider;
        this.link = link;
        this.title = title;
    }
}
