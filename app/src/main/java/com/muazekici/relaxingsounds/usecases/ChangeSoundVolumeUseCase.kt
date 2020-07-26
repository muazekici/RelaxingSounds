package com.muazekici.relaxingsounds.usecases

import com.muazekici.relaxingsounds.gateways_and_adapters.SoundItem
import com.muazekici.relaxingsounds.repositories.media.SoundPoolManager
import javax.inject.Inject

class ChangeSoundVolumeUseCase @Inject constructor(private val soundPoolManager: SoundPoolManager) :
    UseCase<SoundItem, Unit>() {

    override suspend fun execute(parameter: SoundItem) {
        soundPoolManager.changeVolumeSound(parameter.id, parameter.soundLevel)
    }
}