package com.example.pontoextra.API

import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class TabelaExtractor {

    fun extrairTabelaDeHtml(url: String): String {
        val document: Document = Jsoup.connect(url).get()
        val tabela: Elements = document.select("table")
        val dados = mutableListOf<Dividendo>()

        for (linha: Element in tabela.select("tr")) {
            val colunas: Elements = linha.select("td")

            if (colunas.size == 4) {
                val dado = Dividendo(
                    tipo = colunas[0].text(),
                    dataCom = colunas[1].text(),
                    pagamento = colunas[2].text(),
                    valor = colunas[3].text().replace(",", ".").toDouble()
                )
                dados.add(dado)
            }
        }

        return Gson().toJson(dados)
    }
}