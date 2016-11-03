package com.network.mofang.mfhttpexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.mfzp.network.base.HttpType;



public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("COM.MFZP.ACTION.TO_LOGIN");
        registerReceiver(receiver, intentFilter);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfHttpManager.executeByParams(HttpType.GET, "http://101.201.57.61:3261/user/loginByPassword.json", "{\"account\":\"18201133070\",\"password\":\"123456\",\"propertys\":\"userId,accountStatus,regIdentity\"}", null, null);
            }
        });
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("COM.MFZP.ACTION.TO_LOGIN".equals(intent.getAction())) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }
    };
}
