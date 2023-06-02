package com.example.myapplication.ui.main.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class ExoplayerActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer

//    private lateinit var playerView: StyledPlayerView
    private lateinit var video_view: PlayerView

    //    private val url = "https://pgcvideo-cdn.xiaodutv.com/1910403440_1018264544_20190318012008.mp4?Cache-Control%3Dmax-age%3A8640000%26responseExpires%3DWed%2C_26_Jun_2019_01%3A20%3A09_GMT=&xcode=6e1b252a3895d89330027ee04ef815ae9fde6ba2a22f6493&time=1685758789&sign=1685672389-8419912-0-1fd289ddbb7d7df475cfc6b45517d58f&_=1685674557140"
    private val url = "http://vod06.msf.ticdn.it/farmunica/2017/11/124899_15fc565cd8f965/15fc565cd8f965-11_0.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)

//        playerView = findViewById(R.id.player_view)
//        playerView.requestFocus()

        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this),
            DefaultTrackSelector(),
            DefaultLoadControl()
        )
        video_view = findViewById(R.id.video_view)
        video_view.setPlayer(player)


        val playUri: Uri =
            Uri.parse(url)
        val mediaSource: MediaSource = buildMediaSource(playUri)
        player.prepare(mediaSource, true, false)
        player.playWhenReady = true
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "com.example.exoplayerdemo")
        return ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    override fun onStart() {
        super.onStart()
//        if (Build.VERSION.SDK_INT > 23) {
//            initializePlayer()
//            if (playerView != null) {
//                playerView.onResume()
//            }
//        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
//        if(video_view!=null){
//            video_view.
//        }
    }

    override fun onStop() {
        super.onStop()
    }
}