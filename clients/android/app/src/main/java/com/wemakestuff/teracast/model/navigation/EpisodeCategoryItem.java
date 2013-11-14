package com.wemakestuff.teracast.model.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wemakestuff.teracast.R;
import com.wemakestuff.teracast.model.api.EpisodeCategory;

import butterknife.InjectView;
import butterknife.Views;

public class EpisodeCategoryItem implements Item {
    EpisodeCategory mEpisodeCategory;

    public EpisodeCategoryItem(EpisodeCategory mEpisodeCategory) {
        this.mEpisodeCategory = mEpisodeCategory;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView != null) {
            Object tempView = convertView.getTag();
            if (tempView instanceof ViewHolder) {
                holder = (ViewHolder) tempView;
            }
        }

        if (holder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.episode_list_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.title.setText(mEpisodeCategory.getTitle());

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView title;

        ViewHolder(View view) {
            Views.inject(this, view);
        }
    }
}
