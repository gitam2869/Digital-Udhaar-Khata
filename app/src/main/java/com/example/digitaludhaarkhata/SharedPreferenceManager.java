package com.example.digitaludhaarkhata;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager
{
    private static SharedPreferenceManager sharedPreferenceManager;
    private static Context context;

    private static final String SHARED_PREF_NAME = "userinfo";

    private static final String USER_MOBILE_NUMBER = "mobilenumber";
    private static final String USER_SHOP_NAME = "shopname";
    private static final String USER_LANGUAGE = "language";
    private static final String USER_LANGUAGE_CODE = "languagecode";
    private static final String USER_ID = "userid";
    private static final String USER_CUSTOMER_COUNT = "customercount";
    private static final String USER_BALANCE = "userbalance";
    private static final String USER_BALANCE_COLOR = "userbalancecolor";




    private SharedPreferenceManager(Context context)
    {
        this.context = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context)
    {
        if(sharedPreferenceManager == null)
        {
            sharedPreferenceManager = new SharedPreferenceManager(context);
        }
        return sharedPreferenceManager;
    }

    /*
        Store userinfo in key value pair format
     */

    public boolean userMobileNumber(String stringMobileNumber)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_MOBILE_NUMBER, stringMobileNumber);
        editor.apply();

        return true;
    }

    public boolean userShopName(String stringShopName)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_SHOP_NAME, stringShopName);
        editor.apply();

        return true;
    }

    public boolean userLanguage(String stringLanguage)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_LANGUAGE, stringLanguage);
        editor.apply();

        return true;
    }

    public boolean userLanguageCode(String stringLanguageCode)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_LANGUAGE_CODE, stringLanguageCode);
        editor.apply();

        return true;
    }

    public boolean userId(String userId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_ID, userId);
        editor.apply();

        return true;
    }

    public boolean customerCount(int count)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(USER_CUSTOMER_COUNT, count);
        editor.apply();

        return true;
    }

    public boolean userBalance(String balance, String balanceColor)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_BALANCE, balance);
        editor.putString(USER_BALANCE_COLOR, balanceColor);
        editor.apply();

        return true;
    }

    /*
        retrieve userinfo in the form of key value pair
     */




    public String getUserMobileNumber()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_MOBILE_NUMBER, null);
    }

    public String getUserShopName()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_SHOP_NAME, null);
    }

    public String getUserLanguage()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_LANGUAGE, null);
    }

    public String getUserLanguageCode()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_LANGUAGE_CODE, null);
    }

    public String getUserId()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, null);
    }

    public int getUserCustomerCount()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_CUSTOMER_COUNT, 0);
    }

    public String getUserBalance()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_BALANCE, null);
    }

    public String getUserBalanceColor()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_BALANCE_COLOR, null);
    }

    /*
        Login or Logout methods
     */

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        if(sharedPreferences.getString(USER_MOBILE_NUMBER,null) != null)
        {
            return true;
        }
        return false;
    }

    public boolean logout()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
