package com.chanda.employeeattendence.util;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;

import com.chanda.employeeattendence.R;

public class CommonUtil {
    public static String months[] = {"January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"};
    public static int years[] ={2019,
            2018,
            2017,
            2016,
            2015,
            2014,
            2013,
            2012,
            2011,
            2010,
            2009,
            2008,
            2007,
            2006,
            2005,
            2004,
            2003,
            2002,
            2001,
            2000,
            1999,
            1998,
            1997,
            1996,
            1995,
            1994,
            1993,
            1992,
            1991,
            1990,
            1989,
            1988,
            1987,
            1986,
            1985,
            1984,
            1983,
            1982,
            1981,
            1980,
            1979,
            1978,
            1977,
            1976,
            1975,
            1974,
            1973,
            1972,
            1971,
            1970,
            1969,
            1968,
            1967,
            1966,
            1965,
            1964,
            1963,
            1962,
            1961,
            1960,
            1959,
            1958,
            1957,
            1956,
            1955,
            1954,
            1953,
            1952,
            1951,
            1950,
            1949,
            1948,
            1947,
            1946,
            1945,
            1944,
            1943,
            1942,
            1941,
            1940,
            1939,
            1938,
            1937,
            1936,
            1935,
            1934,
            1933,
            1932,
            1931,
            1930,
            1929,
            1928,
            1927,
            1926,
            1925,
            1924,
            1923,
            1922,
            1921,
            1920,
            1919,
            1918,
            1917,
            1916,
            1915,
            1914,
            1913,
            1912,
            1911,
            1910,
            1909,
            1908,
            1907,
            1906,
            1905,
            1904,
            1903,
            1902,
            1901
    };
    public static Dialog getDialog(Context context) {

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(true);
        return dialog;

    }
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}