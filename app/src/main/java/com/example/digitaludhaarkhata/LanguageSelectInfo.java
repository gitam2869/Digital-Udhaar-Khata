package com.example.digitaludhaarkhata;

public class LanguageSelectInfo
{
    private String icon;
    private String languageName;



    public LanguageSelectInfo(
            String icon,
            String languageName
    )
    {
        this.icon = icon;
        this.languageName = languageName;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getLanguageName()
    {
        return languageName;
    }

}
