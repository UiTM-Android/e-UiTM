/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package com.mrphd.e_uitm;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;

public class HttpRequest extends AsyncTask<String, Void, String> {

    public static final String EMPTY_RESPONSE = "";

    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> params = new HashMap<String, String>();

    private final String requestMethod;

    HttpRequest(final String requestMethod){
        this.requestMethod = requestMethod;
    }

    HttpRequest withHeader(String header, String value){
        headers.put(header, value);
        return this;
    }

    HttpRequest withParam(String key, String param){
        params.put(key, param);
        return this;
    }

    @Override
    protected String doInBackground(String...args) {
        final InputStream in;
        try{
            final URL url = new URL(args[0]);
            final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(this.requestMethod);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            for(final String key : headers.keySet()){
                conn.setRequestProperty(key, headers.get(key));
            }

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            if(requestMethod.toUpperCase().equals("POST"))
            {
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (String key : params.keySet())
                {
                    if (i++ != 0) sb.append("&");
                    sb.append(String.format("%s=%s", key, params.get(key)));
                }
                wr.writeBytes(sb.toString());
            }

            in = new BufferedInputStream(conn.getInputStream());
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) response.append(line);
            return response.toString();
        }catch(final Exception e){
            return e.getLocalizedMessage();
        }
    }

    static final class Get extends HttpRequest {

        Get(){
            super("GET");
        }

    }

    static final class Post extends HttpRequest {

        Post(){
            super("POST");
        }

    }

}
