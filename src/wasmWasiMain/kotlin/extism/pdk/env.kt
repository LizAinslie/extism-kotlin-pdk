package extism.pdk

import kotlin.wasm.WasmImport

typealias ExtismPointer = Long

@WasmImport("extism:host/env", "input_length")
external fun extism_input_length(): Long

@WasmImport("extism:host/env", "length")
external fun extism_length(p: ExtismPointer): Long

@WasmImport("extism:host/env", "alloc")
external fun extism_alloc(p: Long): ExtismPointer

@WasmImport("extism:host/env", "free")
external fun extism_free(p: ExtismPointer)

@WasmImport("extism:host/env", "input_load_u8")
external fun extism_input_load_u8_(p: ExtismPointer): Int

fun extism_input_load_u8(p: ExtismPointer): Byte {
    return extism_input_load_u8_(p).toByte()
}

@WasmImport("extism:host/env", "input_load_u64")
external fun extism_input_load_u64(p: ExtismPointer): Long

@WasmImport("extism:host/env", "output_set")
external fun extism_output_set(p: ExtismPointer, v: Long)

@WasmImport("extism:host/env", "error_set")
external fun extism_error_set(p: ExtismPointer)

@WasmImport("extism:host/env", "config_get")
external fun extism_config_get(p: ExtismPointer): ExtismPointer

@WasmImport("extism:host/env", "var_get")
external fun extism_var_get(p: ExtismPointer): ExtismPointer

@WasmImport("extism:host/env", "var_set")
external fun extism_var_set(p: ExtismPointer, v: ExtismPointer)

@WasmImport("extism:host/env", "store_u8")
external fun extism_store_u8_(p: ExtismPointer, v: Int)
fun extism_store_u8(p: ExtismPointer, v: Byte) {
    extism_store_u8_(p, v.toInt())
}

@WasmImport("extism:host/env", "load_u8")
external fun extism_load_u8_(p: ExtismPointer): Int
fun extism_load_u8(p: ExtismPointer): Byte {
    return extism_load_u8_(p).toByte()
}

@WasmImport("extism:host/env", "store_u64")
external fun extism_store_u64(p: ExtismPointer, v: Long)

@WasmImport("extism:host/env", "load_u64")
external fun extism_load_u64(p: ExtismPointer): Long

@WasmImport("extism:host/env", "http_request")
external fun extism_http_request(p: ExtismPointer, r: ExtismPointer):  ExtismPointer

@WasmImport("extism:host/env", "http_status_code")
external fun extism_http_status_code(): Int

@WasmImport("extism:host/env", "log_info")
external fun extism_log_info(p: ExtismPointer)

@WasmImport("extism:host/env", "log_debug")
external fun extism_log_debug(p: ExtismPointer)

@WasmImport("extism:host/env", "log_warn")
external fun extism_log_warn(p: ExtismPointer)

@WasmImport("extism:host/env", "log_error")
external fun extism_log_error(p: ExtismPointer)