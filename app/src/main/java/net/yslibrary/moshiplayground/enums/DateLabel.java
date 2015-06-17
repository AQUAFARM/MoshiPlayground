package net.yslibrary.moshiplayground.enums;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public enum DateLabel {

    TODAY("今日"),
    TOMORROW("明日"),
    DAY_AFTER_TOMORROW("明後日"),
    UNKNOWN("不明");

    public String label;

    DateLabel(String label) {
        this.label = label;
    }

    public static DateLabel get(String label) {
        for (DateLabel dateLabel : DateLabel.values()) {
            if (dateLabel.label.equals(label)) {
                return dateLabel;
            }
        }

        return UNKNOWN;
    }
}
