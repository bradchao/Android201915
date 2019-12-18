package tw.org.iii.android201915;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekbar);
        myReceiver = new MyReceiver();


    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter("ACTION_BRAD");
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("cmd", "play");
        startService(intent);
    }

    public void pauseMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("cmd", "pause");
        startService(intent);
    }

    public void resetMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int len = intent.getIntExtra("len", -1);
            int pos = intent.getIntExtra("pos", -1);
            if (len >= 0){
                seekBar.setMax(len);
            }
            if (pos >= 0){
                seekBar.setProgress(pos);
            }
        }
    }

}
