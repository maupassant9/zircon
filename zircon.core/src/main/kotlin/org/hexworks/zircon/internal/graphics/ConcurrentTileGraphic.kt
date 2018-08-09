package org.hexworks.zircon.internal.graphics

import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.BaseTileGraphic
import org.hexworks.zircon.api.graphics.StyleSet
import org.hexworks.zircon.api.resource.TilesetResource
import org.hexworks.zircon.platform.factory.ThreadSafeMapFactory

class ConcurrentTileGraphic(
        size: Size,
        tileset: TilesetResource,
        styleSet: StyleSet = StyleSet.defaultStyle())
    : BaseTileGraphic(
        size = size,
        tileset = tileset,
        contents = ThreadSafeMapFactory.create(),
        styleSet = styleSet)
