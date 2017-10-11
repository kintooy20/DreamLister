package com.elano.dreamlisterapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Janeth on 10/8/2017.
 */

public class WishListAdapter extends RecyclerView.Adapter <WishListAdapter.MyViewHolder> {

    public static final String KEY_ADD = "key-add";
    private List<Item> items;

    public WishListAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                Intent intent = new Intent(v.getContext(), AddWishListItemActivity.class);
                intent.putExtra(KEY_ADD, item);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, description, price;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ivItem);
            name = (TextView) itemView.findViewById(R.id.tvName);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
