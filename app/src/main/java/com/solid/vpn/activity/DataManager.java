package com.solid.vpn.activity;

import com.solid.vpn.models.Category;
import com.solid.vpn.models.ServerCategory;
import com.solid.vpn.models.ServerDetail;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public String[] Server_IPs = null;
    public String[] Server_Names = null;
    public String User_Name = "";
    public String Password = "";
    public int Version = 1;
    public int USA_Servers = 0;

    public static String username = "azakhan";
    public static String password = "azavpn0";
    public static String[] Server_UUIDS;



    //admob ads
    public static boolean ADMOB_ENABLE = true;
    public final static String ADMOB_APPID="admob_app_id";
    public final static String ADMOB_PUBLISHER="publisher_id";
    public final static String ADMOB_BANNER="banner_admob";
    public final static String ADMOB_INTERSTITIAL="interstitial_admob";
    public final static String ADMOB_NATIVE="native_admob";
    public final static String EMAIL="email";
    public final static String ADMOB_REWARD="reward_adomb";
    public final static String ADMOB_ENABLES="admobs_enable";
    public final static String ADMOB_SUBSCRIPTION="isSubscription";
    public final static String ADMOB_ISPAID="isPaid";

    public static ArrayList<ServerDetail> serverDetails=new ArrayList<>();
    public static ArrayList<ServerDetail> freeServerDetails=new ArrayList<>();


}
