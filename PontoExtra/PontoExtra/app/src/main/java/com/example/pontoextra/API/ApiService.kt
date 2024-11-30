package com.example.pontoextra.API

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{<span class=\"value\">R\$ 60,55</span>}")
    suspend fun getCotacaoAtual(@Path("value") codigo: String): Cotacao

    @GET("acoes/{table-dividends-history_wrapper}/dividendos/")
    suspend fun getDividendos(@Path("table-dividends-history_wrapper") codigo: String): List<Dividendo>
}