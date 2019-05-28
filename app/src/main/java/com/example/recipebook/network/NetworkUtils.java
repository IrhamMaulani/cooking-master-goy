package com.example.recipebook.network;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public static String getDataUseParameter(String url, String queryParam, String queryString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String categoriesJSONString = null;

        try {
            Uri builtURI = Uri.parse(BASE_URL + url).buildUpon()
                    .appendQueryParameter(queryParam, queryString)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {

                builder.append(line);

                builder.append("\n");
            }

            if (builder.length() == 0) {

                return null;
            }
            categoriesJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return categoriesJSONString;
    }
}
