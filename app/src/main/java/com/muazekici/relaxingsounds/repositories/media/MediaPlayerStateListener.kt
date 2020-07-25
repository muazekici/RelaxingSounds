package com.muazekici.relaxingsounds.repositories.media

interface MediaPlayerStateListener {
    fun onStarted(sound: Sound)
    fun onCompleted(sound: Sound)
}