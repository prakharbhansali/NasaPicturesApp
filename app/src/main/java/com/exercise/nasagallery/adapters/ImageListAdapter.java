package com.exercise.nasagallery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.nasagallery.R;
import com.exercise.nasagallery.common.Utils;
import com.exercise.nasagallery.datamodels.ImageModel;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder> {

    private Context context;
    private ArrayList<ImageModel> list;

    public ImageListAdapter(Context context, ArrayList<ImageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_image_list, parent, false);
        return new ImageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListViewHolder holder, int position) {

        holder.tvTitle.setText("");
        holder.tvCopyright.setText("");

        ImageModel model = list.get(position);
        if (model != null) {

            if (model.getTitle() != null && model.getTitle().length() > 0) {
                holder.tvTitle.setText(model.getTitle());
            }

            if (model.getCopyright() != null && model.getCopyright().length() > 0) {
                holder.tvCopyright.setText(model.getCopyright());
            }

            Utils.setGlideImage(context, model.getUrl(), holder.ivImage, R.drawable.default_image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ImageListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvCopyright;
        private ImageView ivImage;

        public ImageListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCopyright = itemView.findViewById(R.id.tvCopyright);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
