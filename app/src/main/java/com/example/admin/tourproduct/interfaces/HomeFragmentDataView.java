package com.example.admin.tourproduct.interfaces;

import com.example.admin.tourproduct.entry.Place;

import java.util.List;

/**
 * Created by YH on 2018/4/28.
 */

public interface HomeFragmentDataView {

    void showLoadingProgress(String msg);

    void showData(List<Place> placeList);
}
