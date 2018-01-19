package kt.leonbec.youtubeplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*

val YOUTUBE_VIDEO_ID = "zhszwkcay2A"
val YOUTUBE_PLAYLIST = "PLa1qzTn3bkJjhA9Trhb0S5ZSUEgOBwhe4"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val YOUTUBE_ACTIVITY = "YOUTUBE_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        Log.d(YOUTUBE_ACTIVITY, "provider is ${provider?.javaClass}")
        Log.d(YOUTUBE_ACTIVITY, "player is ${player?.javaClass}")
        Log.d(YOUTUBE_ACTIVITY,"Initialised Youtube Player successfully")

        if (!wasRestored)
            player?.loadVideo(YOUTUBE_VIDEO_ID)
        else
            player?.play()
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0
        if (result?.isUserRecoverableError == true)
            result.getErrorDialog(this, REQUEST_CODE).show()
        else {
            val errorMsg = "There was an error initialising the YoutubePlayer: $result"
            Log.e(YOUTUBE_ACTIVITY, errorMsg)
        }
    }
//
//    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
//        override fun onSeekTo(p0: Int) {
//
//        }
//
//        override fun onBuffering(p0: Boolean) {
//        }
//
//        override fun onPlaying() {
//            Toast.makeText(this@YoutubeActivity,"video is playing",Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onStopped() {
//        }
//
//        override fun onPaused() {
//            Toast.makeText(this@YoutubeActivity,"Video has paused",Toast.LENGTH_LONG).show()
//        }
//    }
}
