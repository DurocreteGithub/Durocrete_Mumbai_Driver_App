package com.durocrete.root.durocretpunedriverapp.Utillity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;

/**
 * Created by root on 4/5/17.
 */
public class Utility {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void showFragment(final Class<? extends Fragment> fragmentClass, final Activity activityClass,
                                    final int frameLayoutId) {

        try {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    FragmentActivity fragmentActivity = (FragmentActivity) activityClass;
                    FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(frameLayoutId, Fragment.instantiate(fragmentActivity, fragmentClass.getCanonicalName()));
                    fragmentTransaction.commit();
                }
            }).start();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
    public static void noConnectionAlert(Context context) {
        AlertDialog.Builder connection_builder = new AlertDialog.Builder(context);
        connection_builder.setMessage("Please check internet connection");
        connection_builder.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog exit_alertDialog = connection_builder.create();
        exit_alertDialog.show();
    }


    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteFormat = stream.toByteArray();
            // get the base 64 string
            String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

            return imgString;
        }
        return null;
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            if (activity != null && activity.getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity
                        .getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void errorDialog(String message , Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }


}
