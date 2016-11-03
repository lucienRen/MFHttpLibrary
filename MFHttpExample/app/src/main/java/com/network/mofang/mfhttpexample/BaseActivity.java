package com.network.mofang.mfhttpexample;

import android.app.Activity;
import android.os.Bundle;

import com.mfzp.network.MFHttpManager;


/**
 * Created by mofang on 2016/10/21.
 */

public class BaseActivity extends Activity {

    protected MFHttpManager mfHttpManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mfHttpManager = MFHttpManager.instance(this);
    }
}
