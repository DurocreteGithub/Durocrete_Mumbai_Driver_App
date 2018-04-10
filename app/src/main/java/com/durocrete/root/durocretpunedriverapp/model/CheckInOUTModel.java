package com.durocrete.root.durocretpunedriverapp.model;

/**
 * Created by root on 24/5/17.
 */
public class CheckInOUTModel {
    private String result ="";
//    private int checkInId = 0;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

   /* public int getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }*/

    @Override
    public String toString() {
        return  result ;
    }
}
