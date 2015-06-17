package net.yslibrary.moshiplayground.dto.adapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import net.yslibrary.moshiplayground.enums.DateLabel;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class DateLabelAdapter {

    @FromJson
    DateLabel fromJson(String dateLabel) {
        return DateLabel.get(dateLabel);
    }

    @ToJson
    String toJson(DateLabel dateLabel) {
        return dateLabel.label;
    }
}
