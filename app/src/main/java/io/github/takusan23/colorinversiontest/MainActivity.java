package io.github.takusan23.colorinversiontest;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.provider.Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED;

public class MainActivity extends AppCompatActivity {

    Button button;
    Settings.Secure secure_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //色反転テスト
        button = findViewById(R.id.button);
        //クリックイベント
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //色反転チェッカー]
                //android/provider/setting　あたりで見つけた
                //このSettings.SecureってADBで権限を与える（ADBが使えるPC必須）かシステムアプリ（root必須）に移動する必要あり
                /*
                *
                *  adb shell pm grant io.github.takusan23.colorinversiontest android.permission.WRITE_SECURE_SETTINGS
                *
                * */
                try {
                    if (Settings.Secure.getInt(getContentResolver(),"accessibility_display_inversion_enabled") == 1){
                        //ON
                        Settings.Secure.putInt(getContentResolver(),"accessibility_display_inversion_enabled",0);
                    }else{
                        //OFF
                        Settings.Secure.putInt(getContentResolver(),"accessibility_display_inversion_enabled",1);
                    }
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
