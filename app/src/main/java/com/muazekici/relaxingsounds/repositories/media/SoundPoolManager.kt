package com.muazekici.relaxingsounds.repositories.media

import com.muazekici.relaxingsounds.di.scopes.AppScope
import javax.inject.Inject

@AppScope
class SoundPoolManager @Inject constructor() : MediaPlayerStateListener {


    private val emptyPlayers = MutableList(MAX_CONCURRENT) {
        RecyclablePlayer(this)
    }

    private val occupiedPlayerSet = HashMap<Long, RecyclablePlayer>()

    override fun onCompleted(sound: Sound) {
        occupiedPlayerSet.remove(sound.soundId)?.let {
            it.recyclePlayer()
            emptyPlayers.add(it)
        }
    }

    override fun onStarted(sound: Sound) {

    }

    fun startSound(sound: Sound): Boolean {
        if (emptyPlayers.size > 0) {
            emptyPlayers.removeAt(0).also {
                occupiedPlayerSet[sound.soundId] = it
                it.playSound(sound.soundUrl, sound.soundId)
            }
            return true
        }
        return false
    }


    fun stopSound(sound: Sound) {
        occupiedPlayerSet.remove(sound.soundId)?.let {
            it.recyclePlayer()
            emptyPlayers.add(it)
        }
    }

    companion object {
        const val MAX_CONCURRENT = 9
    }
}



