package com.sample.example.goldenscentsample;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sweety on 12-Oct-18.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<String> itemNameList, itemDiscList, itemDescList, itemPriceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName, itemNameDesc, itemPrice, itemDisPrice;
        private ImageView imgview;


        public MyViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.itemName);
            itemNameDesc = (TextView) view.findViewById(R.id.itemDesc);
            itemPrice = (TextView) view.findViewById(R.id.itemPrice);
            itemDisPrice = (TextView) view.findViewById(R.id.itemDisPrice);
        }
    }

    public HorizontalAdapter(List<String> itemName, List<String> itemDesc, List<String> itemDisc, List<String> itemPrice) {
        itemNameList = itemName;
        itemDescList = itemDesc;
        itemPriceList = itemPrice;
        itemDiscList = itemDisc;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hr_rv_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemName.setText(itemNameList.get(position));
        holder.itemNameDesc.setText(itemDescList.get(position));
        holder.itemPrice.setText(itemPriceList.get(position));
//        if(!itemDiscList.get(position).equals("0 SR")) {
            holder.itemPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemDisPrice.setVisibility(View.VISIBLE);
            holder.itemDisPrice.setText(itemDiscList.get(position));
            holder.itemDisPrice.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }
}

