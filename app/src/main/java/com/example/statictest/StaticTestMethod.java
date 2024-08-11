package com.example.statictest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;

import androidx.core.content.ContextCompat;

import java.io.File;

import dalvik.system.DexClassLoader;

public class StaticTestMethod {

    public void lab_004(){
        // [lab_004](/**/) - In-app billing: by Dylan
        // 這個是找 package 的, 連 method 都不用, 同 lab_042
    }

    public void lab_008(Context context){
        // [lab_008] - android_Permissions(Read GPS): sending GPS messages
        // 這段 code 是 GPT 生的, 謝謝 GPT

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // 處理位置更新
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {}

                @Override
                public void onProviderEnabled(String provider) {}

                @Override
                public void onProviderDisabled(String provider) {}
            };
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, Looper.getMainLooper());
        } else {
            // 請求權限或處理權限不足的情況
        }
    }


    public void lab_042(){
        // [lab_042] - Detect dynamic code loading
        // 失敗了, 不知道該怎麼才能讓他偵測到這個
        // 原本的寫法看起來是只要呼叫到任何的 dalvik/system/DexClassLoader 底下的 method 就可以被偵測到
        // 新的寫法需要針對 androguard_server.py 進行一些修改才行
        // 現在的 androguard_server.py 只是暴力的察看是否有存在特定的 method
        // 也有可能只是我單純沒寫好而已呵呵原版的也偵測不出來
        DexClassLoader loader = new DexClassLoader(
                "/dynamic.jar",
                "/opt_dex",
                null,
                this.getClass().getClassLoader()
        );
        try {
            Class<?> classes = loader.loadClass("com.example.statictest.test").getClass();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void lab_043(){
        // [lab_043] - Get External Storage Directory access invoke
        File sdCard = Environment.getExternalStorageDirectory();
        File directory_picture = new File(sdCard, "Pictures");
    }

    public void lab_063(){
        // [lab_063] - File delete alert
        File f = null;
        boolean del = false;
        f = new File("test.txt");
        del = f.delete();
    }
}
