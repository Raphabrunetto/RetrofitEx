package com.example.pontoextra.ui.theme;

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pontoextra.DataScraper
import com.example.pontoextra.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var resultadoTextView: TextView
    private lateinit var buscarAcaoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoTextView = findViewById(R.id.resultadoTextView)
        buscarAcaoButton = findViewById(R.id.buscarAcaoButton)

        buscarAcaoButton.setOnClickListener {
            buscarDadosAcao("VALE3")
        }
    }

    private fun buscarDadosAcao(codigo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val scraper = DataScraper()
            val cotacao = scraper.getCotacao(codigo)
            val dividendos = scraper.getDividendos()


            withContext(Dispatchers.Main) {

                resultadoTextView.text = "Cotação: $cotacao"
                resultadoTextView.append("\n")
                resultadoTextView.append("\n")

                if (dividendos != null && dividendos.isNotEmpty()) {

                    dividendos.forEach { dividendo ->
                        resultadoTextView.append("\n")
                        resultadoTextView.append("Tipo: ${dividendo.tipo}\n")
                        resultadoTextView.append("Data com: ${dividendo.dataCom}\n")
                        resultadoTextView.append("Pagamento: ${dividendo.pagamento}\n")
                        resultadoTextView.append("Valor: R$ ${"%.2f".format(dividendo.valor)}\n")
                        resultadoTextView.append("\n")
                    }
                } else {
                    resultadoTextView.append("Não foram encontrados dividendos para a ação.")
                }
            }
        }
    }
}