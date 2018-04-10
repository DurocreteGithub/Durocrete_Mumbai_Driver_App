package com.durocrete.root.durocretpunedriverapp.comman;

public class URLS {
    private static URLS urls = null;
    private static String baseUrl = "http://210.16.103.150:83/PuneDuroCrete/";

    public static URLS getInstance() {
        if (urls == null) {
            urls = new URLS();
        }
        return urls;
    }

    public String loginDriveURL = baseUrl + "sign_in_driver.php?driverId=";
    public String getRouteList = baseUrl + "get_routes.php";
    public String getCheckIn = baseUrl + "check_in_driver.php";
    public String getMaterialList = baseUrl + "get_materials.php";
    public String getCheckOut = baseUrl + "checked_out.php";

    public String getPickUPPoints(String driverId) {
        return baseUrl + "pick_up.php?driverId=" + driverId;
    }

    /*not in use*/
    public String getSideList = baseUrl + "get_sites.php?selectedRouteId=";
    public String setPickUpPoints = baseUrl + "daily_pickup_allocation.php";
    public String pick_up = baseUrl + "pick_up.php?";
    public String get_reports = baseUrl + "get_report_list_driver.php?user_id=";
    public String get_bills = baseUrl + "get_driver_bill_list.php?user_id=";
    public String submit_report = baseUrl + "submit_reports.php";
    public String submit_bill = baseUrl + "submit_bills.php";

}
