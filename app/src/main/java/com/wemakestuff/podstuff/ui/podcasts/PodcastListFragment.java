package com.wemakestuff.podstuff.ui.podcasts;

import android.os.Bundle;
import android.view.View;

import com.wemakestuff.podstuff.model.api.Podcast;
import com.wemakestuff.podstuff.model.navigation.Item;
import com.wemakestuff.podstuff.model.navigation.listener.OnPodcastClickListener;
import com.wemakestuff.podstuff.ui.base.BaseListFragment;
import com.wemakestuff.podstuff.ui.widget.adapter.ItemAdapter;

public class PodcastListFragment extends BaseListFragment<Item> implements OnPodcastClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(getAdapter());
        hideProgressBar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("No Podcasts Here, Sucka!");
    }

    protected ItemAdapter getAdapter() {
        return new ItemAdapter(getActivity(), getItems());
    }

    @Override
    public void onPodcastClick(Podcast podcast, Action action) {

    }
}
