package com.nao20010128nao.Insight

import com.squareup.moshi.Json

data class BitcoinBlock(
        val hash: String,
        val confirmations: Int,
        val size: Int,
        val height: Long,
        val version: Int,
        @Json(name = "merkleroot")
        val merkleRoot: String,
        val tx: List<String>,
        val time: Long,
        val nonce: Long,
        val bits: String,
        val difficulty: Double,
        @Json(name = "chainwork")
        val chainWork: String,
        @Json(name = "previousblockhash")
        val previousBlockHash: String,
        val reward: Double,
        val isMainChain: Boolean
)

data class InsightRawBlock(
        @Json(name = "rawblock")
        val rawBlock: String
)

data class InsightBlockHash(
        val blockHash: String
)

data class BitcoinBlockSummary(
        val blocks: List<BitcoinBlockSummaryBlock>,
        val length: Int,
        val pagination: BitcoinBlockSummaryPagination
)

data class BitcoinBlockSummaryBlock(
        val height: Long,
        val size: Int,
        val hash: String,
        val time: Long,
        @Json(name = "txlength")
        val txLength: Long,
        val poolInfo: BitcoinPoolInfo
)

data class BitcoinPoolInfo(
        val poolName: String,
        val url: String
)

data class BitcoinBlockSummaryPagination(
        val next: String,
        val prev: String,
        val currentTs: Long,
        val current: String,
        val isToday: Boolean,
        val more: Boolean,
        val moreTs: Long
)

data class BitcoinTransaction(
        @Json(name = "txid")
        val tx: String,
        val version: Int,
        @Json(name = "locktime")
        val lockTime: Long,
        val vin: List<BitcoinVIn>,
        val vout: List<BitcoinVOut>,
        @Json(name = "blockhash")
        val blockHash: String,
        val confirmations: Int,
        val time: Long,
        @Json(name = "blocktime")
        val blockTime: Long,
        val isCoinBase: Boolean,
        val valueOut: Double,
        val size: Int
)

data class BitcoinVIn(
        val coinbase: String,
        val sequence: Int,
        val n: Int,
        val isConfirmed: Boolean?,
        val confirmations: Int?,
        val unconfirmedInput: Boolean?
)

data class BitcoinVOut(
        val value: String,
        val n: Int,
        val scriptPubKey: BitcoinScriptPubKey
)

data class BitcoinScriptPubKey(
        val asm: String,
        val hex: String,
        val reqSigs: Int?,
        val type: String?,
        val addresses: List<String>?
)

data class InsightRawTx(
        @Json(name = "rawtx")
        val rawTx: String
)

data class BitcoinAddressInfo(
        val addrStr: String,
        val balance: Double,
        val balanceSat: Long,
        val totalReceived: Double,
        val totalReceivedSat: Long,
        val unconfirmedBalance: Double,
        val unconfirmedBalanceSat: Long,
        val unconfirmedTxApperances: Int,
        val txApperances: Int,
        val transactions: List<String>
)

data class BitcoinUtxo(
        val address: String,
        @Json(name = "txid")
        val tx: String,
        val vout: Int,
        val scriptPubKey: String,
        val amount: Double,
        val satoshis: Long,
        val confirmations: Int,
        val ts: Long
)

data class BitcoinDetailedTransactionInfo(
        val pagesTotal: Int,
        val txs: List<BitcoinTransaction>
)

data class BitcoinTransaction2(
        @Json(name = "txid")
        val tx: String,
        val version: Int,
        @Json(name = "locktime")
        val lockTime: Long,
        val vin: List<BitcoinVIn2>,
        val vout: List<BitcoinVOut>,
        @Json(name = "blockhash")
        val blockHash: String,
        val confirmations: Int,
        val time: Long,
        @Json(name = "blocktime")
        val blockTime: Long,
        val isCoinBase: Boolean,
        val valueOut: Double,
        val size: Int,
        val valueIn: Double,
        val fees: Int
)

data class BitcoinTransactionsInMultipleAddrs(
        val totalItems: Int,
        val from: Int,
        val to: Int,
        val items: List<BitcoinTransaction3>
)

data class BitcoinScriptSig(
        val asm: String,
        val hex: String
)

data class BitcoinVIn2(
        val sequence: Int,
        val n: Int,
        val coinbase: String?,
        @Json(name = "txid")
        val tx: String?,
        val vout: Int?,
        val scriptSig: BitcoinScriptSig?,
        val addr: String?,
        val valueSat: Long?,
        val value: Double?,
        @Json(name = "doubleSpentTxID")
        val doubleSpentTxId: String?
)

data class BitcoinTransaction3(
        @Json(name = "txid")
        val tx: String,
        val version: Int,
        @Json(name = "locktime")
        val lockTime: Long,
        val vin: List<BitcoinVIn2>,
        val vout: List<BitcoinVOut>,
        @Json(name = "blockhash")
        val blockHash: String,
        val confirmations: Int,
        val time: Long,
        @Json(name = "blocktime")
        val blockTime: Long,
        val isCoinBase: Boolean,
        val valueOut: Double,
        val size: Int,
        val firstSeenTs: Long?,
        val valueIn: Double,
        val fees: Double
)

data class InsightTxId(
        @Json(name = "txid")
        val txId: String
)

data class InsightSyncState(
        val status: String,
        val blockChainHeight: Int,
        val syncPercentage: String,
        val height: String,
        val error: String?,
        val type: String,
        val startTs: Long,
        val endTs: Long
)

data class InsightPeerInfo(
        val connected: Boolean,
        val host: String,
        val port: String
)
