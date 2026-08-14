package com.lianyu.ai.common

import android.content.Context
import org.json.JSONObject

// 远程密钥占位实现：开源构建不内置中继服务，也不下发任何密钥
// 调用方拿到空列表后，应引导用户到设置页自行填写 API Key

/**
 * 开源版对原私有中继的占位。
 * 公开构建不会访问捆绑服务器，也不会返回内嵌凭证。
 */
object RemoteKeyProvider {
    @Volatile
    var serverUrl: String = ""

    fun openSourceHandshake(ctx: Context): JSONObject = JSONObject().apply {
        put("ok", false)
        put("error", "open_source_build_requires_user_api_config")
    }

    fun getRandomModel(context: Context): String? = null
    fun clearCache(context: Context) = Unit

    // 开源握手始终 ok=false，这里只保留签名，避免设置页编译失败
    fun storeHandshakeResult(context: Context, handshakeJson: JSONObject) = Unit

    // 开源版没有远程密钥源；forceRefresh 保留是为了兼容 AiService / 设置页的调用签名
    fun fetchKeysAsync(context: Context, forceRefresh: Boolean = false): List<String> = emptyList()
}
