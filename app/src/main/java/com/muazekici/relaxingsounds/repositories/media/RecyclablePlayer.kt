package com.muazekici.relaxingsounds.repositories.media

import android.media.MediaPlayer

class RecyclablePlayer(private val mediaPlayerStateListener: MediaPlayerStateListener) :
    MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private val mediaPlayer = MediaPlayer()

    var currentSound: Sound? = null
        private set


    init {
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.isLooping = true
    }

    fun playSound(soundUrl: String, soundId: Long, volume: Float) {
        currentSound = Sound(soundUrl, soundId, volume)
        mediaPlayer.setDataSource(soundUrl)
        mediaPlayer.prepareAsync()
        mediaPlayer.setVolume(volume, volume)
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

    fun changeVolume(volume: Float) {
        currentSound?.volume = volume
        mediaPlayer.setVolume(volume, volume)
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

