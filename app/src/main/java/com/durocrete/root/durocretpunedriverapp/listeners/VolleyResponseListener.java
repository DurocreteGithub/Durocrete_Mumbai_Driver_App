package com.durocrete.root.durocretpunedriverapp.listeners;

/**
 * Created by root on 16/7/16.
 */
public interface VolleyResponseListener<T> {
    void onResponse(T[] object);

    void onError(String message);
}
