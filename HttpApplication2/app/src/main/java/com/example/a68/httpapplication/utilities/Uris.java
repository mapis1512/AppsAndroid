package com.example.a68.httpapplication.utilities;

import android.net.Uri;

import com.example.a68.httpapplication.Providers.MyContentProvide;

import static com.example.a68.httpapplication.databases.MyDataBase.TABLE_NAME;

/**
 * Created by 71 on 7/22/2017.
 */

public class Uris {
    public static final Uri GET_ALL_URI=Uri.parse("content://"+ MyContentProvide.AUTHORITY+"/"+TABLE_NAME);
    public static final Uri BULK_INSERT_URI=Uri.parse("content://"+ MyContentProvide.AUTHORITY+"/"+TABLE_NAME+"/bulk-insert/");

}
