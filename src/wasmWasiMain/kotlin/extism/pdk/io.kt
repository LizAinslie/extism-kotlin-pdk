package extism.pdk

import extism.pdk.internal.writeLong

fun loadInput(): ByteArray {
    val length = extism_input_length()
    val buf = ByteArray(length.toInt()).apply {

    }

    val chunkCount = length shr 3

    var chunkIdx = 0
    while (chunkIdx < chunkCount) {
        val i = chunkIdx shl 3
        buf.writeLong(i, extism_input_load_u64(i.toLong()))
        chunkIdx++
    }
}