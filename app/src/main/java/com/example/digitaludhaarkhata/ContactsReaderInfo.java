package com.example.digitaludhaarkhata;

public class ContactsReaderInfo
{
    private String name;
    private String phone;



    public ContactsReaderInfo(
            String name,
            String phone
    )
    {
        this.name = name;
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }
}
