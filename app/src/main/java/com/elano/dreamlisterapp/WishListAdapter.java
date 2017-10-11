package com.elano.dreamlisterapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Gimenez and Elano on 10/8/2017.
 */

class WishListAdapter extends RecyclerView.Adapter <WishListAdapter.MyViewHolder> {

    private List<Item> items;

    WishListAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Item item = items.get(position);
        Bitmap bm = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        holder.name.setText(item.getName());
        holder.price.setText(String.format(Locale.US, "%s: %,.2f", "Price", item.getPrice()));
        holder.image.setImageBitmap(bm);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, description, price;

        MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ivItem);
            name = (TextView) itemView.findViewById(R.id.tvName);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
