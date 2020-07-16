package com.finnovation.task.repository;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    public static String BASE_URL = "https://www.learnovationz.com/machine-task/";

    @GET("machine-task.php")
    Call<ResponseBody> getImages();

    @FormUrlEncoded
    @POST("upload.php")
    Call<String> getImageData(
            @Field("image1") String image
    );


}
