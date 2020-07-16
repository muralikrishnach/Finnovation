package com.finnovation.task.repository;

import android.util.Log;

import com.finnovation.task.ImagesDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParsing {

    public List<ImagesDao> images(String jsonString)  {
        List<ImagesDao> alImageDao = null;

        JSONObject job;
        JSONArray jdata;
        Log.v("","jsonString"+jsonString);

        alImageDao = new ArrayList<>();

        if(jsonString!=null && !jsonString.isEmpty()) {
            try {
                job = new JSONObject(jsonString);
                jdata = job.getJSONArray("images");

                for(int i=0;i<jdata.length();i++){
                    JSONObject jdata1 = jdata.getJSONObject(i);

                    String ID = jdata1.getString("id");
                    String Images = jdata1.getString("images");

                    ImagesDao data = new ImagesDao();
                    data.setId(ID);
                    data.setImages(Images);

                    alImageDao.add(data);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return alImageDao;
    }

}
