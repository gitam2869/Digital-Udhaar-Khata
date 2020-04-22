package com.example.digitaludhaarkhata;

public class CustomerTransactionInfo
{
    private String userid;
    private String customerName;
    private String customerMobile;
    private String userGave;
    private String userGot;
    private String date;



    public CustomerTransactionInfo(
            String userid,
            String customerName,
            String customerMobile,
            String userGave,
            String userGot,
            String date
    )
    {
        this.userid = userid;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.userGave = userGave;
        this.userGot = userGot;
        this.date = date;
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

    public String getDate()
    {
        return date;
    }

}
