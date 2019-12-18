package tw.org.iii.android201915;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekbar);
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
}
