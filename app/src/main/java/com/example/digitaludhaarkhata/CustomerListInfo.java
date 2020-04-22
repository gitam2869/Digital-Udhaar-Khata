package com.example.digitaludhaarkhata;

public class CustomerListInfo
{
    private String userid;
    private String customerName;
    private String customerMobile;
    private String userGave;
    private String userGot;



    public CustomerListInfo(
            String userid,
            String customerName,
            String customerMobile,
            String userGave,
            String userGot
    )
    {
        this.userid = userid;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.userGave = userGave;
        this.userGot = userGot;
    }

    public String getUserid()
    {
        return userid;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerMobile()
    {
        return customerMobile;
    }

    public String getUserGave()
    {
        return userGave;
    }

    public String getUserGot()
    {
        return userGot;
    }
}
