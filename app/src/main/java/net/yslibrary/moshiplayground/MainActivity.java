package net.yslibrary.moshiplayground;

import com.squareup.moshi.Moshi;

import net.yslibrary.moshiplayground.adapter.ForecastListAdapter;
import net.yslibrary.moshiplayground.dto.Weather;
import net.yslibrary.moshiplayground.dto.adapter.DateLabelAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    WeatherApi mApi;

    Moshi mMoshi;

    @InjectView(R.id.location_city)
    TextView mLocationCity;

    @InjectView(R.id.location_area)
    TextView mLocationArea;

    @InjectView(R.id.forecast_list)
    RecyclerView mForecastList;

    RecyclerView.LayoutManager mLayoutManager;

    ForecastListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mForecastList.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mForecastList.setLayoutManager(mLayoutManager);

        mAdapter = new ForecastListAdapter();
        mForecastList.setAdapter(mAdapter);

        mMoshi = provideMoshi();
        mApi = provideRestAdapter(mMoshi).create(WeatherApi.class);

        // get weather for tokyo
        mApi.getWeather(130010, new Callback<Weather>() {
            @Override
            public void success(Weather weather, Response response) {
                Timber.d("success:%s", weather);

                mLocationCity.setText(weather.location.city);
                mLocationArea.setText(weather.location.area);

                mAdapter.addAll(weather.forecasts);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.e("failure:%s", error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private RestAdapter provideRestAdapter(Moshi moshi) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://weather.livedoor.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new MoshiConverter(moshi))
                .build();

        return restAdapter;
    }

    private Moshi provideMoshi() {
        Moshi moshi = new Moshi.Builder()
                .add(new DateLabelAdapter())
                .build();

        return moshi;
    }
}
