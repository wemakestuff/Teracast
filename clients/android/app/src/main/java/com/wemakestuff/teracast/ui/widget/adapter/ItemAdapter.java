package com.wemakestuff.teracast.ui.widget.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wemakestuff.teracast.model.navigation.Item;
import com.wemakestuff.teracast.util.ListUtils;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    Context mContext;
    List<Item> mItemList;

    public ItemAdapter(Context mContext, List<Item> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    @Override
    public int getCount() {
        return ListUtils.getCount(mItemList);
    }

    @Override
    public Object getItem(int position) {
        return ListUtils.getItem(mItemList, position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return ((Item) getItem(position)).getView(mContext, convertView, parent);
    }
}
