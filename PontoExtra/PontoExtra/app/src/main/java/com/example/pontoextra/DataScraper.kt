package com.example.pontoextra

import com.example.pontoextra.API.Dividendo
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


class DataScraper {

    fun getCotacao(vale3: String): String? {

        return try {
            // Faz a requisição HTTP e obtém o documento HTML
            val url = "https://investidor10.com.br/acoes/vale3/"
            val document: Document = Jsoup.connect(url).get()
            val cotacaoElement = document.select("span.value").first()
            cotacaoElement?.text()
        } catch (e: Exception) {
            println("Erro ao obter cotação: ${e.message}")
            null
        }
    }

    fun getDividendos(): List<Dividendo>? {

        return try {
            val url = "https://investidor10.com.br/acoes/vale3/"
            // Faz a requisição HTTP e obtém o documento HTML
            val document: Document = Jsoup.connect(url).get()

            // Seleciona a tabela de dividendos pelo ID
            val dividendTable = document.select("#table-dividends-history").first()

            // Cria uma lista para armazenar os dividendos
            val dividendos = mutableListOf<Dividendo>()


            if (dividendTable != null) {

                val dividendElements = dividendTable.select("tr")

                for (element: Element in dividendElements) {
                    val cols = element.select("td")
                    if (cols.size >= 4) {
                        val tipo = cols[0].text()
                        val dataCom = cols[1].text()
                        val pagamento = cols[2].text()
                        val valor = cols[3].text().replace(",", ".").toDouble()
                        val dividendo = Dividendo(tipo, dataCom, pagamento, valor)
                        dividendos.add(dividendo)
                    }
                }
            } else {
                println("Tabela de dividendos não encontrada.")
                return null
            }

            dividendos
        } catch (e: Exception) {
            println("Erro ao obter dividendos: ${e.message}")
            null
        }
    }
}
