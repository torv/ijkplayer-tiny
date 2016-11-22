package com.torv.adam.ijkplayer_tiny;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.torv.adam.ijkplayer_tiny.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayerActivity extends AppCompatActivity {

    IjkVideoView mVideoView;
    TableLayout mHudView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mVideoView = (IjkVideoView) findViewById(R.id.id_videoview);
        mHudView = (TableLayout) findViewById(R.id.hud_view);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoView.setHudView(mHudView);
        mVideoView.setVideoPath(Environment.getExternalStorageDirectory() + "/Download/少数派报告BD.mkv");
//        mVideoView.setVideoPath(Environment.getExternalStorageDirectory() + "/Download/少数派报告BD.mkv");
        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                mVideoView.start();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        mVideoView.stopBackgroundPlay();
        IjkMediaPlayer.native_profileEnd();
    }
}
