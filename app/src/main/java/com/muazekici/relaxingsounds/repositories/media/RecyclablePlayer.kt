package com.muazekici.relaxingsounds.repositories.media

import android.media.MediaPlayer

class RecyclablePlayer(private val mediaPlayerStateListener: MediaPlayerStateListener) :
    MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private val mediaPlayer = MediaPlayer()

    private var currentSound: Sound? = null

    init {
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.isLooping = true
    }

    fun playSound(soundUrl: String, soundId: Long) {
        currentSound = Sound(soundUrl, soundId)
        mediaPlayer.setDataSource(soundUrl)
        mediaPlayer.prepareAsync()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer.start()
        mediaPlayerStateListener.onStarted(currentSound!!)
    }

    override fun onCompletion(p0: MediaPlayer?) {
        mediaPlayer.reset()
        mediaPlayerStateListener.onCompleted(currentSound!!)
    }

    fun recyclePlayer() {
        currentSound = null
        mediaPlayer.reset()
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecyclablePlayer

        if (mediaPlayer != other.mediaPlayer) return false

        return true
    }

    override fun hashCode(): Int {
        return mediaPlayer.hashCode()
    }

}

