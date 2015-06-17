package net.yslibrary.moshiplayground.adapter;

import net.yslibrary.moshiplayground.R;
import net.yslibrary.moshiplayground.dto.Forecast;
import net.yslibrary.moshiplayground.dto.Temperature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shimizu_yasuhiro on 15/06/17.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    List<Forecast> mDataSet = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_forecast_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Forecast data = mDataSet.get(i);

        viewHolder.mDate.setText(data.dateLabel.label);
        viewHolder.mTelop.setText(data.telop);

        Temperature temp = data.temperature;
        String tempStr = temp.min != null ? temp.min.celsius + "C" : "-";
        tempStr += " 〜 ";
        tempStr += temp.max != null ? temp.max.celsius + "C" : "-";

        viewHolder.mTemperature.setText(tempStr);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<Forecast> dataSet) {
        int position = mDataSet.size();

        mDataSet.addAll(dataSet);

        notifyItemRangeInserted(position, position + dataSet.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.forecast_date)
        TextView mDate;

        @InjectView(R.id.forecast_telop)
        TextView mTelop;

        @InjectView(R.id.forecast_temperature)
        TextView mTemperature;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }

}
