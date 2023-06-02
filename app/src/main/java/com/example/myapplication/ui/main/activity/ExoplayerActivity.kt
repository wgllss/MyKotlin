package com.example.myapplication.ui.main.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class ExoplayerActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer

    private lateinit var video_view: PlayerView

    //    private val url = "https://pgcvideo-cdn.xiaodutv.com/1910403440_1018264544_20190318012008.mp4?Cache-Control%3Dmax-age%3A8640000%26responseExpires%3DWed%2C_26_Jun_2019_01%3A20%3A09_GMT=&xcode=6e1b252a3895d89330027ee04ef815ae9fde6ba2a22f6493&time=1685758789&sign=1685672389-8419912-0-1fd289ddbb7d7df475cfc6b45517d58f&_=1685674557140"
    private val url = "http://vod06.msf.ticdn.it/farmunica/2017/11/124899_15fc565cd8f965/15fc565cd8f965-11_0.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)

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
}