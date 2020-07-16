package com.finnovation.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.finnovation.task.repository.ApiService;
import com.finnovation.task.repository.RetrofitClass;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private Context ctx;
    private List<ImagesDao> alimages;

    public ImagesAdapter(Context ctx, List<ImagesDao> alimages){
        this.ctx = ctx;
        this.alimages = alimages;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.cardelements, parent, false);
        return new ImagesAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
        final ImagesDao imagesDao = alimages.get(position);

        String url = imagesDao.getImages();

        Glide.with(ctx).load(url).into(holder.imgview);

        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = imagesDao.getImages();

                String image1 = "";
                try {

                    URL url1 = new URL(url);
                    Bitmap image = BitmapFactory.decodeStream(url1.openConnection().getInputStream());

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    image1 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    String name = String.valueOf(Calendar.getInstance().getTimeInMillis());

                }catch (Exception e){
                    e.printStackTrace();
                }

                ApiService retrofitClient =  RetrofitClass.getClient().create(ApiService.class);
                Call<String> call = retrofitClient.uploadImageData(image1);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Toast.makeText(ctx, "Image Uploaded Successfully!!", Toast.LENGTH_SHORT).show();
                                holder.imgview.setEnabled(false);
                            } else {
                                Toast.makeText(ctx, "No response from the server!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ctx, "Response not successful "+response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });





            }
        });

    }

    @Override
    public int getItemCount() {
        if(alimages!=null){
            return alimages.size();
        }
        else {
            return 0;
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        private CardView card;
        private AppCompatImageView imgview;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card_view);
            imgview = itemView.findViewById(R.id.imgData);

        }
    }
}
