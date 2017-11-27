package com.e2esp.fashioncentral.adapters;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * Created by Zain on 1/30/2017.
 */

public class VerticalSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public VerticalSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = spacing;
        }
    }

}
