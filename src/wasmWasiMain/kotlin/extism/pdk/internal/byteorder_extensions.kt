package extism.pdk.internal

fun ByteArray.writeLong(startIdx: Int, value: Long) {
    this[startIdx] = (value and 0xFF).toByte()
    this[startIdx + 1] = ((value shr 8) and 0xFF).toByte()
    this[startIdx + 2] = ((value shr 16) and 0xFF).toByte()
    this[startIdx + 3] = ((value shr 24) and 0xFF).toByte()
    this[startIdx + 4] = ((value shr 32) and 0xFF).toByte()
    this[startIdx + 5] = ((value shr 40) and 0xFF).toByte()
    this[startIdx + 6] = ((value shr 48) and 0xFF).toByte()
    this[startIdx + 7] = ((value shr 56) and 0xFF).toByte()
}

fun ByteArray.readLong(startIdx: Int): Long {
    val bytes = this.copyOfRange(startIdx, 8)
    var result: Long = 0

    for (i in bytes.indices.reversed()) {
        result = result shl 8
        result = result or (bytes[i].toLong() and 0xFF)
    }

    return result
}

fun ByteArray.toInt(): Int {
    var value = 0
    for (b in this) {
        value = (value shl 8) + (b.toInt() and 0xFF)
    }
    return value
}