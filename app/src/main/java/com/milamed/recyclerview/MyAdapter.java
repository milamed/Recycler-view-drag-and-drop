package com.milamed.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.milamed.recyclerview.helper.ItemTouchHelperAdapter;
import com.milamed.recyclerview.helper.ItemTouchHelperViewHolder;
import com.milamed.recyclerview.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by milamed on 14/03/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {


    private ArrayList<DataModel> mDataList;
    private LayoutInflater mLayoutInflater;
    private final OnStartDragListener mDragStartListener;

    public MyAdapter(Context context, ArrayList<DataModel> dataList, OnStartDragListener dragStartListener )
    {
        mDataList = dataList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDragStartListener = dragStartListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_cell, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        DataModel dataModel = mDataList.get(position);
        holder.mTitle.setText(dataModel.getmTitle());
        holder.mDescription.setText(dataModel.getmDescription());
        holder.mImage.setImageResource(dataModel.getmImg());
        holder.mCheckBox.setChecked(dataModel.isChecked());
        holder.mImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }
    @Override
    public void onItemDismiss(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDataList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }





    class ItemViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, ItemTouchHelperViewHolder {


        TextView mTitle;
        TextView mDescription;
        ImageView mImage;
        CheckBox mCheckBox;


        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            mCheckBox.setOnCheckedChangeListener(this);

        }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundResource(R.drawable.item_background_drag);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundResource(R.drawable.item_background);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mDataList.get(getAdapterPosition()).setChecked(isChecked);
            if( mDataList.get(getAdapterPosition()).isChecked())
            {
                itemView.setBackgroundResource(R.drawable.item_background_checked);
            }
            else
            {
                itemView.setBackgroundResource(R.drawable.item_background);
            }
        }
    }
    }
