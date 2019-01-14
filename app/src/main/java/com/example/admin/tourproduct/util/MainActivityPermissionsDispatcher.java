package com.example.admin.tourproduct.util;

import android.support.v4.app.ActivityCompat;

import com.example.admin.tourproduct.activity.HomeActivity;

import java.lang.ref.WeakReference;

import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public final class MainActivityPermissionsDispatcher {

    private static final int REQUEST_NEEDSTORAGE = 0;

    private static final String[] PERMISSION_NEEDSTORAGE = new String[] {"android.permission.WRITE_EXTERNAL_STORAGE"};

    private MainActivityPermissionsDispatcher() {
    }

    public static void needStorageWithPermissionCheck(HomeActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_NEEDSTORAGE)) {
            target.needStorage();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_NEEDSTORAGE)) {
                target.showRational(new MainActivityNeedStoragePermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_NEEDSTORAGE, REQUEST_NEEDSTORAGE);
            }
        }
    }

    public static void onRequestPermissionsResult(HomeActivity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_NEEDSTORAGE:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.needStorage();
                } else {
                    target.deniedStorage();
                }
                break;
            default:
                break;
        }
    }

    private static final class MainActivityNeedStoragePermissionRequest implements PermissionRequest {
        private final WeakReference<HomeActivity> weakTarget;

        private MainActivityNeedStoragePermissionRequest(HomeActivity target) {
            this.weakTarget = new WeakReference<HomeActivity>(target);
        }

        @Override
        public void proceed() {
            HomeActivity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_NEEDSTORAGE, REQUEST_NEEDSTORAGE);
        }

        @Override
        public void cancel() {
            HomeActivity target = weakTarget.get();
            if (target == null) return;
            target.deniedStorage();
        }
    }
}
