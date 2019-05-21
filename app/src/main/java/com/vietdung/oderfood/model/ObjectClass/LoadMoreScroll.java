package com.vietdung.oderfood.model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {
    int itemFirstHint = 0;
    int sumItem = 0;
    int loadFront = 10;
    private RecyclerView.LayoutManager mLayoutManager;
    private ILoadMore mILoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        mLayoutManager = layoutManager;
        mILoadMore = iLoadMore;

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        sumItem = mLayoutManager.getItemCount();
        if (mLayoutManager instanceof LinearLayoutManager) {
            itemFirstHint = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

        } else if (mLayoutManager instanceof GridLayoutManager) {
            itemFirstHint = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if (sumItem <= itemFirstHint + loadFront) {
            //Log.d("tong itemm",sumItem+" "+itemFirstHint);
            mILoadMore.LoadMore(sumItem);
        }
    }
}
