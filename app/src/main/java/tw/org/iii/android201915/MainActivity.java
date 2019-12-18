package tw.org.iii.android201915;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void pauseMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void resetMusic(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
