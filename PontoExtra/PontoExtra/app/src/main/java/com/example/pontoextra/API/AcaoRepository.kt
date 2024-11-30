package com.example.pontoextra.API

import org.example.network.RetrofitClient



class AcaoRepository {

    suspend fun getDadosAcao(): Pair<Cotacao?, List<Dividendo>?> {
        val cotacao: Cotacao?
        val dividendos: List<Dividendo>?

        try {
            cotacao = RetrofitClient.service.getCotacaoAtual("VALE3")
            dividendos = RetrofitClient.service.getDividendos("VALE3")
        } catch (e: Exception) {
            println("Erro ao obter dados da ação: ${e.message}")
            return Pair(null, null)
        }

        return Pair(cotacao, dividendos)
    }
}