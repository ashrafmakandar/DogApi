package com.ashraf.dogapi.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashraf.dogapi.R;
import com.ashraf.dogapi.model.hound;
import com.squareup.picasso.Picasso;

import java.util.List;

public class dogadapter extends RecyclerView.Adapter<dogadapter.views> {
    Context context;
    List<String> messages;

    public dogadapter(Context context, List<String> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public views onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dogbreed, parent, false);

        return new views(v);
    }

    @Override
    public void onBindViewHolder(@NonNull views holder, int position) {


        Picasso.get().load(messages.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class views extends RecyclerView.ViewHolder {
        ImageView imageView;

        public views(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.images);
        }
    }

}
