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

    fun startSound(id: Long, url: String, volume: Float): Boolean {
        if (emptyPlayers.size > 0) {
            emptyPlayers.removeAt(0).also {
                occupiedPlayerSet[id] = it
                it.playSound(url, id, volume)
            }
            return true
        }
        return false
    }


    fun stopSound(id: Long) {
        occupiedPlayerSet.remove(id)?.let {
            it.recyclePlayer()
            emptyPlayers.add(it)
        }
    }

    fun checkSound(id: Long): Pair<Long, Float>? {
        occupiedPlayerSet[id]?.let {
            it.currentSound?.let {
                return Pair(it.soundId, it.volume)
            }
            return null
        }
        return null
    }

    fun changeVolumeSound(id: Long, volume: Float) {
        occupiedPlayerSet[id]?.let {
            it.changeVolume(volume)
        }
    }

    companion object {
        const val MAX_CONCURRENT = 9
    }
}



