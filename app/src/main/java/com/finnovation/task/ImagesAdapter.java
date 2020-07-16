package com.finnovation.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

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
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final ImagesDao imagesDao = alimages.get(position);

        String url = imagesDao.getImages();

        Glide.with(ctx).load(url).into(holder.imgview);

        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = imagesDao.getImages();

                
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
