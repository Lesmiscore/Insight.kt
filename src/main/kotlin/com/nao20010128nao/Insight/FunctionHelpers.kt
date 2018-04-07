package com.nao20010128nao.Insight

sealed class GetRawBlock {
    abstract fun query(): String
    data class ByHash(val hash: String) : GetRawBlock() {
        override fun query(): String = hash
    }

    data class ByHeight(val height: Int) : GetRawBlock() {
        override fun query(): String = height.toString()
    }
}

sealed class GetTransactions {
    abstract fun query(): Map<String, String>
    data class ByBlock(val block: String) : GetTransactions() {
        override fun query(): Map<String, String> = mapOf("block" to block)
    }

    data class ByAddress(val address: String) : GetTransactions() {
        override fun query(): Map<String, String> = mapOf("address" to address)
    }
}
