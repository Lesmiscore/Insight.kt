package com.nao20010128nao.Insight

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.LocalDate

class Insight(val endpoint: String) {
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val client = OkHttpClient()

    fun getBlockByHash(blockHash: String): BitcoinBlock {
        val url = "$endpoint/block/$blockHash"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinBlock::class).fromJson(response)!!
    }

    fun getBlockHashByHeight(height: Int): InsightBlockHash {
        val url = "$endpoint/block-index/$height"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightBlockHash::class).fromJson(response)!!
    }

    fun getRawBlock(query: GetRawBlock): InsightRawBlock {
        val url = "$endpoint/rawblock/${query.query()}"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightRawBlock::class).fromJson(response)!!
    }

    fun getBlockSummary(limit: Int, blockDate: LocalDate): BitcoinBlockSummary {
        val url = "$endpoint/blocks?limit=$limit&blockDate=${blockDate.insightExpression}"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinBlockSummary::class).fromJson(response)!!
    }

    fun getTransation(tx: String): BitcoinTransaction {
        val url = "$endpoint/tx/$tx"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinTransaction::class).fromJson(response)!!
    }

    fun getRawTransation(tx: String): InsightRawTx {
        val url = "$endpoint/rawtx/$tx"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightRawTx::class).fromJson(response)!!
    }

    fun getAddressInfo(address: String): BitcoinAddressInfo {
        val url = "$endpoint/addr/$address"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinAddressInfo::class).fromJson(response)!!
    }

    fun getUnspent(address: String): BitcoinUtxo = getUnspent(listOf(address))

    fun getUnspent(addresses: List<String>): BitcoinUtxo {
        val url = "$endpoint/addr/${addresses.joinToString(",")}/utxo"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinUtxo::class).fromJson(response)!!
    }

    fun getTransactions(addresses: List<String>): BitcoinTransactionsInMultipleAddrs {
        val url = "$endpoint/addrs/${addresses.joinToString(",")}/txs"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinTransactionsInMultipleAddrs::class).fromJson(response)!!
    }

    fun getTransactions(query: GetTransactions): BitcoinDetailedTransactionInfo {
        val url = "$endpoint/txs/?${query.query().toQueryString()}"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(BitcoinDetailedTransactionInfo::class).fromJson(response)!!
    }

    fun broadcastTransaction(rawTx: ByteArray): InsightTxId = broadcastTransaction(rawTx.toHex())

    fun broadcastTransaction(rawTx: String): InsightTxId {
        val url = "$endpoint/tx/send"
        val post = moshi.mapAdapter<String, String>().toJson(mapOf("rawtx" to rawTx))
        val request = Request.Builder().url(url).post(requestBody("text/json", post)).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightTxId::class).fromJson(response)!!
    }

    fun getSyncState(): InsightSyncState {
        val url = "$endpoint/sync"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightSyncState::class).fromJson(response)!!
    }

    fun getPeerInfo(): InsightPeerInfo {
        val url = "$endpoint/peer"
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute().body()!!.string()
        return moshi.adapter(InsightPeerInfo::class).fromJson(response)!!
    }

    companion object {
        /** Example Insight server: BTC */
        val bitcoin = Insight("https://insight.bitpay.com/api")
        /** Example Insight server: BTC Testnet */
        val bitcoinTestnet = Insight("https://test-insight.bitpay.com/api")
        /** Example Insight server: BCH */
        val bitcoinCash = Insight("https://bch-insight.bitpay.com/api")
        /** Example Insight server: BCH Testnet */
        val bitcoinCashTestnet = Insight("https://test-bch-insight.bitpay.com/api")
    }
}