package extism.pdk

data class Memory(
    val offset: ExtismPointer,
    val length: Long,
) {
    fun readBytes(): ByteArray {
        val buf = ByteArray(length.toInt()).apply {
            // todo: ensure byteorder is LE
        }

        load(buf)
        return buf
    }

    fun output() {
        extism_output_set(offset, length)
    }

    fun free() {
        extism_free(offset)
    }

    fun store(data: ByteArray) {
        Companion.store(offset, data)
    }

    fun load(buf: ByteArray) {
        Companion.load(offset, buf)
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

        fun findMemory(offset: ExtismPointer): Memory {
            val length = extism_length(offset)
            return Memory(offset, length)
        }

        fun allocate(length: Long): Memory {
            val offset = extism_alloc(length)
            return Memory(offset, length)
        }

        /**
         * Allocate memory on-stack for [data] and [store] it.
         */
        fun allocateBytes(data: ByteArray): Memory {
            val length = data.size.toLong()
            val offset = extism_alloc(length)

            store(offset, data)

            return Memory(offset, length)
        }

        fun allocateString(s: String): Memory {
            val bytes = ByteArray(s.length)
            for (i in s.indices) {
                bytes[i] = s[i].code.toByte()
            }
            return allocateBytes(bytes)
        }

        fun store(offset: ExtismPointer, buf: ByteArray) {
            val length = buf.size
            val chunkCount = length shr 3

            var chunkIdx = 0
            while(chunkIdx < chunkCount) {
                val i = chunkIdx shl 3
                val x = buf[i].toLong()
                extism_store_u64(offset + i, x)
                chunkIdx++
            }

            val remainder = length and 7
            val remainderOffset = chunkCount shl 3

            var index = remainderOffset;
            while (index < (remainder + remainderOffset)) {
                extism_store_u8(offset + index, buf[index])
                index++
            }
        }
    }
}
