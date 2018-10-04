package org.hexworks.zircon.api.modifier

import org.hexworks.zircon.api.color.TileColor
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.platform.util.SystemUtils

data class Delay(private val timeMs: Long = 2000) : TileTransformModifier<CharacterTile> {

    private val steps = 2
    private var currentStep = 1
    private var delay: Long = timeMs / steps
    private var lastRender: Long = Long.MIN_VALUE
    private lateinit var currentTile: CharacterTile

    override fun generateCacheKey(): String {
        return "Modifier.Delay.$currentStep"
    }

    override fun transform(tile: CharacterTile): CharacterTile {
        if (isFirstRender()) {
            lastRender = SystemUtils.getCurrentTimeMs()
            currentTile = generateTile(tile)
        }
        return if (currentStep == steps) {
            tile
        } else {
            val now = SystemUtils.getCurrentTimeMs()
            if (now - lastRender > delay) {
                lastRender = now
                currentTile = generateTile(tile)
                currentStep++
            }
            currentTile
        }
    }

    private fun generateTile(tile: CharacterTile): CharacterTile {
        val hiddenModifier = if (currentStep == 1)
            setOf(SimpleModifiers.Hidden)
        else
            setOf<Modifier>()

        return tile
                .withModifiers(hiddenModifier)
    }

    private fun isFirstRender() = lastRender == Long.MIN_VALUE

}
