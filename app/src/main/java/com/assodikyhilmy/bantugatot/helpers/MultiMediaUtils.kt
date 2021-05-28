package com.assodikyhilmy.bantugatot.helpers

import android.content.Context
import android.media.MediaPlayer
import com.assodikyhilmy.bantugatot.R

class MultiMediaUtils {
    companion object {
        fun playThunderClap(context: Context) {
            val mediaPlayer =
                MediaPlayer.create(context, R.raw.thunderclap)
            mediaPlayer.setOnCompletionListener {
                it.release()
            }
            mediaPlayer.start()
        }

        fun playGetScore(context: Context) {
            val mediaPlayer =
                MediaPlayer.create(context, R.raw.getscore)
            mediaPlayer.setOnCompletionListener {
                it.release()
            }
            mediaPlayer.start()
        }
    }
}