package extism.pdk

enum class LogLevel {
    LogInfo,
    LogDebug,
    LogWarn,
    LogError,
    LogTrace,
}

object Log {
    fun info(ptr: ExtismPointer) {
        extism_log_info(ptr)
    }

    fun debug(ptr: ExtismPointer) {
        extism_log_debug(ptr)
    }

    fun warn(ptr: ExtismPointer) {
        extism_log_warn(ptr)
    }

    fun error(ptr: ExtismPointer) {
        extism_log_error(ptr)
    }

    fun printMemory(level: LogLevel, memory: Memory) {
        when (level) {
            LogLevel.LogInfo -> info(memory.offset)
            LogLevel.LogDebug -> debug(memory.offset)
            LogLevel.LogWarn -> warn(memory.offset)
            LogLevel.LogError -> error(memory.offset)
            LogLevel.LogTrace -> info(memory.offset) // no log_trace fun
        }
    }

    fun printString(level: LogLevel, s: String) {
        printMemory(level, Memory.allocateString(s))
    }
}