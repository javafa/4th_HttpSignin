package com.veryworks.android.httpsignin;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by pc on 10/16/2017.
 */

public class Remote {

    public static String sendPost(String address, Map postdata){
        String result = "";
        try{
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            // post 데이터를 전송 -----------
            String temp = "";
            for(Object key : postdata.keySet()){
                temp += "&"; // 두번째 줄부터만 붙인다
                temp += key+"="+postdata.get(key);
            }
            temp = temp.substring(1);
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(temp.getBytes());
            os.flush();
            os.close();
            // -----------------------------

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                temp = "";
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
                br.close();
                isr.close();
            }else{

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String getData(String string){
        final StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(string);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            // 통신이 성공인지 체크
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 여기서 부터는 파일에서 데이터를 가져오는 것과 동일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result.append(temp).append("\n");
                }
                br.close();
                isr.close();
            } else {
                Log.e("ServerError", con.getResponseCode()+"");
            }
            con.disconnect();
        }catch(Exception e){
            Log.e("Error", e.toString());
        }
        return result.toString();
    }

}
