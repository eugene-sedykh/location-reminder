package org.android.app.locationreminder.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Date: 15.08.13
 */
public class ApplicationUtil {

    public static void showToast(String message, Context context) {
        Toast toast = Toast.makeText(context, message, 5);
        toast.show();
    }

    public static void showToast(int resId, Context context) {
        Toast toast = Toast.makeText(context, resId, 5);
        toast.show();
    }
}
