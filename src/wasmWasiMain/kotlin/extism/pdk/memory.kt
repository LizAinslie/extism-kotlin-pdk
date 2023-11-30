package extism.pdk

data class Memory(
    val offset: ExtismPointer,
    val length: Int,
) {
    fun readBytes(): ByteArray {
        val buff = ByteArray(length).apply {
            // todo: ensure byteorder is LE
        }

        load(offset, buff)
        return buff
    }

    companion object {
        fun load(offset: ExtismPointer, buf: ByteArray) {
            val length = buf.size
            val chunkCount = length shr 3

            var chunkIdx = 0
            while(chunkIdx < chunkCount) {
                val i = chunkIdx shl 3
                buf[i] = extism_load_u8(offset + i)
                chunkIdx++
            }
        }
    }
}

fun allocate(length: Int): Memory {
    val offset = extism_alloc(length.toLong())
    return Memory(offset, length)
}