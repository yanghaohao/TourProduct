package com.example.admin.tourproduct.interfaces;


import com.example.admin.tourproduct.model.HomeFragmentDataModel;

/**
 * Created by YH on 2018/4/28.
 */

public interface HomeFragmentDataTransmit {
    void getData(HomeFragmentDataModel.IsLoadingData loadingData, int control, String postHead, int postOrGet);
}
