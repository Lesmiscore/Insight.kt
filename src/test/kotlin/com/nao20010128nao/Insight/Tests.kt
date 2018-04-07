package com.nao20010128nao.Insight

import org.junit.Test

class Tests {
    val btc = Insight.bitcoin
    val testingBlock = "00000000000000000044b81c0c0b7fa47e194239f291726f5ef5c70cb58fffea"
    val testingTx = "79e0190f177bbbb694965592b1878d5d6d8d9b6f0d7baf3eeef051608f7ba89c"

    @Test
    fun testGetBlock() {
        val block = btc.getBlockByHash(testingBlock)
        require(block.isMainChain)
        require(block.bits == "17502ab7")
        require(block.nonce == 468100908L)
    }

    @Test
    fun testGetBlockHash() {
        val blockHash = btc.getBlockHashByHeight(517051)
        require(blockHash.blockHash == testingBlock)
    }

    @Test
    fun testGetTransaction() {
        val tx = btc.getTransation(testingTx)
        require(tx.blockHash == testingBlock)
        require(tx.lockTime == 0L)
        require(tx.size == 254)
    }
}