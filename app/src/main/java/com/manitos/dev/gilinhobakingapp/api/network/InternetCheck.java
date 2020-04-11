package com.manitos.dev.gilinhobakingapp.api.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by gilberto hdz on 11/04/20.
 */
public class InternetCheck extends AsyncTask<Void, Void, Boolean> {

    private Consumer consumer;
    public interface Consumer { void accept(Boolean internet); }

    public InternetCheck(Consumer consumer) {
        this.consumer = consumer;
        execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean connection) {
        super.onPostExecute(connection);
        consumer.accept(connection);
    }
}
