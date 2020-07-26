package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.repositories.media.SoundPoolManager
import javax.inject.Inject

class PlayStopSoundUseCase @Inject constructor(private val soundPoolManager: SoundPoolManager) :
    UseCase<Pair<SoundItem, Boolean>, Unit>() {

    override suspend fun execute(parameter: Pair<SoundItem, Boolean>) {
        if (parameter.second) {
            soundPoolManager.startSound(
                parameter.first.id,
                parameter.first.sourceUrl,
                parameter.first.soundLevel
            )
        } else {
            soundPoolManager.stopSound(
                parameter.first.id
            )
        }
    }
}