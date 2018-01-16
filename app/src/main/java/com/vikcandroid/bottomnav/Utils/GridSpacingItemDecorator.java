package com.vikcandroid.bottomnav.Utils;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by android-dev on 1/16/18.
 */

public class GridSpacingItemDecorator extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecorator(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // Item position
        int position = parent.getChildAdapterPosition(view);

        // item column
        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) {
                // top edge
                outRect.top = spacing;
            }

            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column - 1) * spacing / spanCount; // spacing - (column + 1) * ((1f / spanCount) * spacing)
            if (position >= spanCount) {
                // item top
                outRect.top = spacing;
            }
        }
    }
}
