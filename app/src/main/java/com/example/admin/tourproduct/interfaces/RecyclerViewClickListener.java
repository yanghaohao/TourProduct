package com.example.admin.tourproduct.interfaces;

import android.view.View;

/**
 * Created by YH on 2018/4/25.
 */

public interface RecyclerViewClickListener {

    void onClick(View view, int position);
    void onLongClick(View view, int position);
    void onDoubleClick(View view, int position);
}
