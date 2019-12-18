package tw.org.iii.android201915;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();
        mediaPlayer = MediaPlayer.create(this, R.raw.ear);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        int len = mediaPlayer.getDuration();
        Intent intent = new Intent("ACTION_BRAD");
        intent.putExtra("len", len);
        sendBroadcast(intent);


        timer.schedule(new MyTask(), 0, 200);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String cmd = intent.getStringExtra("cmd");
        if (cmd.equals("play") && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }else if (cmd.equals("pause") && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else if (cmd.equals("seekto")){
            int newpos = intent.getIntExtra("newpos", -1);
            if (newpos >= 0){
                mediaPlayer.seekTo(newpos);
            }
        }


        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                int pos = mediaPlayer.getCurrentPosition();
                //Log.v("brad", "pos = " + pos);

                Intent intent = new Intent("ACTION_BRAD");
                intent.putExtra("pos", pos);
                sendBroadcast(intent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }

        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.reset();
        }
        mediaPlayer.release();
    }
}
