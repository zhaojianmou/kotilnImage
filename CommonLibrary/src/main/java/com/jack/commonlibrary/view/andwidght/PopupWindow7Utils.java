package com.jack.commonlibrary.view.andwidght;


import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.jack.commonlibrary.utils.ScreenUtils;

/**
 * 修复Android 7.0、7.1中bug
 */
public class PopupWindow7Utils {

    public static void showAtLocation(PopupWindow popupWindow, Activity activity, int id) {
        if (popupWindow == null || activity == null || id == 0) {
            return;
        }
        View view = activity.findViewById(id);
        showAtLocation(popupWindow, view, 0);
    }

    public static void showAtLocation(PopupWindow popupWindow, Fragment fragment, int id) {
        if (popupWindow == null || fragment == null || id == 0) {
            return;
        }
        showAtLocation(popupWindow, fragment, id, 0);
    }


    public static void showAtLocation(PopupWindow popupWindow, Fragment fragment, int id, int bottomHeight) {
        if (popupWindow == null || fragment == null || id == 0) {
            return;
        }
        View view = fragment.getActivity().findViewById(id);
        showAtLocation(popupWindow, view, 0, bottomHeight);
    }

    public static void showAtLocation(PopupWindow popupWindow, View view) {
        if (popupWindow == null || view == null) {
            return;
        }
        showAtLocation(popupWindow, view, 0);
    }

    public static void showAtLocation(PopupWindow popupWindow, View view, int bottomHeight) {
        if (popupWindow == null || view == null) {
            return;
        }
        showAtLocation(popupWindow, view, 0, bottomHeight);
    }

    public static void showAtLocation(PopupWindow popupWindow, View view, int width, int bottomHeight) {
        if (popupWindow == null || view == null) {
            return;
        }
        int[] position = new int[2];
        view.getLocationOnScreen(position);
        showAtLocation(popupWindow, view, width, view.getHeight() + position[1], bottomHeight);
    }

    public static void showAtLocation(PopupWindow popupWindow, View view, int width, int height, int bottomHeight) {
        if (popupWindow == null || view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Android 7.x中,PopupWindow高度为match_parent时,会出现兼容性问题,需要处理兼容性
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                // Android 7.1中，PopupWindow高度为 match_parent 时，会占据整个屏幕
                // 故而需要在 Android 7.1上再做特殊处理
                int screenHeight = ScreenUtils.getScreenHeight();
                // 重新设置 PopupWindow 的高度
                popupWindow.setHeight(screenHeight - height);
            }
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, width, height + bottomHeight);
            popupWindow.update();
        } else {
            popupWindow.showAsDropDown(view, width, bottomHeight);
        }
    }


}
