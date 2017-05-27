package com.example.yeast.bookstore.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yeast on 3/5/2560.
 */

class UrlFetcher {
    private final URL url;

    public UrlFetcher(URL url) {
        this.url = url;
    }

    public String fetch() {
        try {
            String result = "";
            URLConnection booksConn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    booksConn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            return result;
        } catch(IOException e) {
            return null;
        }
    }

}
