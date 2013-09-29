package com.wemakestuff.podstuff.ui.widget.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wemakestuff.podstuff.model.api.Podcast;
import com.wemakestuff.podstuff.model.navigation.Item;
import com.wemakestuff.podstuff.model.navigation.PodcastItem;
import com.wemakestuff.podstuff.model.navigation.listener.OnPodcastClickListener;
import com.wemakestuff.podstuff.ui.podcasts.PodcastListFragment;
import com.wemakestuff.podstuff.util.Ln;

import java.util.ArrayList;
import java.util.List;

public class PodcastsPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

    public PodcastsPagerAdapter(Context mContext, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PodcastListFragment podcastListFragment = new PodcastListFragment();
                podcastListFragment.setItems(getPodcastItems(podcastListFragment));
                return podcastListFragment;
            case 1:
                PodcastListFragment podcastListFragment2 = new PodcastListFragment();
                podcastListFragment2.setItems(getPodcastItems(podcastListFragment2));
                return podcastListFragment2;
            case 2:
                PodcastListFragment podcastListFragment3 = new PodcastListFragment();
                podcastListFragment3.setItems(getPodcastItems(podcastListFragment3));
                return podcastListFragment3;
        }
        Ln.d("Returning Null");
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Some";
            case 2:
                return "More";
            default:
                return null;
        }
    }

    protected List<Item> getPodcastItems(OnPodcastClickListener mListener) {
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        itemList.add(new PodcastItem(new Podcast("Startups For the Rest of Us", "http://www.startupsfortherestofus.com", "Mike Taber and Rob Walling come together to share their insights and experience from the perspective of developers who built their respective companies without Angel or Venture Capital funding. Together, they share the things they've learned and are still learning as independent developers.", "http://www.startupsfortherestofus.com/wp-content/uploads/startup-fortherest-logo.png", "http://www.startupsfortherestofus.com/wp-content/uploads/sftrou_144x144.jpg"), mListener));
        return itemList;
    }
}
