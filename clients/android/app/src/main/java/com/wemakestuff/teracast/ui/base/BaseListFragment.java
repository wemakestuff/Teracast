package com.wemakestuff.teracast.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.github.frankiesardo.icepick.bundle.Bundles;
import com.github.kevinsawicki.wishlist.ViewUtils;
import com.wemakestuff.teracast.Injector;
import com.wemakestuff.teracast.R;

import java.util.List;

import butterknife.InjectView;
import butterknife.Views;

public abstract class BaseListFragment<E> extends SherlockListFragment {
    protected List<E> mItems;
    @InjectView(R.id.pb_loading)
    protected ProgressBar mProgressBar;
    @InjectView(android.R.id.empty)
    protected TextView mEmptyView;

    public List<E> getItems() {
        return mItems;
    }

    public void setItems(List<E> mItems) {
        this.mItems = mItems;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundles.restoreInstanceState(this, savedInstanceState);
        setRetainInstance(true);
        Injector.inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundles.saveInstanceState(this, outState);
    }

    public void showProgressBar() {
        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(false);
        if (mProgressBar.getVisibility() != View.GONE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private BaseListFragment<E> fadeIn(final View view, final boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                        android.R.anim.fade_in));
            else
                view.clearAnimation();
        return this;
    }

    private BaseListFragment<E> show(final View view) {
        ViewUtils.setGone(view, false);
        return this;
    }

    private BaseListFragment<E> hide(final View view) {
        ViewUtils.setGone(view, true);
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_list, null);
    }

    @Override
    public void onDestroyView() {
        Views.reset(this);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Views.inject(this, view);
        getListView().setFastScrollEnabled(true);
    }

    /**
     * Set empty text on list fragment
     *
     * @param message
     * @return this fragment
     */
    protected BaseListFragment<E> setEmptyText(final String message) {
        if (mEmptyView != null)
            mEmptyView.setText(message);
        return this;
    }

    /**
     * Set empty text on list fragment
     *
     * @param resId
     * @return this fragment
     */
    protected BaseListFragment<E> setEmptyText(final int resId) {
        if (mEmptyView != null)
            mEmptyView.setText(resId);
        return this;
    }

    /**
     * Is this fragment still part of an activity and usable from the UI-thread?
     *
     * @return true if usable on the UI-thread, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }
}
