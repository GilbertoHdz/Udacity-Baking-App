package com.manitos.dev.gilinhobakingapp.features;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.manitos.dev.gilinhobakingapp.api.models.Bake;
import com.manitos.dev.gilinhobakingapp.api.network.AppExecutors;
import com.manitos.dev.gilinhobakingapp.api.network.InternetCheck;
import com.manitos.dev.gilinhobakingapp.api.utilities.BakeListJsonUtils;
import com.manitos.dev.gilinhobakingapp.api.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

/**
 * Created by gilberto hdz on 11/04/20.
 */
public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<List<Bake>> _mutableBakes = new MutableLiveData<>();
    private LiveData<List<Bake>> _bakes = _mutableBakes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadBakesFromServer();
    }

    private void loadBakesFromServer() {
        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if (internet) {
                    AppExecutors.getInstance().getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL weatherRequestUrl = NetworkUtils.buildUrl();
                                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
                                _mutableBakes.postValue(BakeListJsonUtils.getBakeListFromJson(jsonResponse));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });


    }

    public LiveData<List<Bake>> getBakes() {
        return _bakes;
    }
}
