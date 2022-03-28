package com.common.framework.widget;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class OnRecyclerVIewItemClickLIstener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat gestureDetectorCompat;
    private RecyclerView recyclerView;

    public OnRecyclerVIewItemClickLIstener(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        this.recyclerView = recyclerView;
        gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                trasfomerItemEvent(e, 0);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                trasfomerItemEvent(e, 1);
            }
        });
    }

    private final void trasfomerItemEvent(MotionEvent e, int type) {
        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (childView != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(childView);
            int position = recyclerView.getChildPosition(childView);
            if (type == 0) {
                onItemClickListener(viewHolder, position);
            } else {
                onItemLongClickListener(viewHolder, position);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public  void onItemClickListener(RecyclerView.ViewHolder viewHolder, int position){}

    public  void onItemLongClickListener(RecyclerView.ViewHolder viewHolder, int position){}
}
