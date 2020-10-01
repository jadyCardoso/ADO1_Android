package com.example.resolucaoado1_4tadsa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("produtos", Context.MODE_PRIVATE)

        btSalvar.setOnClickListener { v: View? ->

            if(txtNomeProduto.text.isNotEmpty() && txtValorCompra.text.isNotEmpty() && txtValorVenda.text.isNotEmpty()){
                var chave = txtNomeProduto.text.toString()
                var valores = txtValorCompra.text.toString() + ";" + txtValorVenda.text.toString()

                sh.edit().putString(chave,valores)
                Toast.makeText(this,"Salvo!",Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Faltam dados para gravar",Toast.LENGTH_SHORT).show()
            }

        }

        btPesquisar.setOnClickListener { v: View? ->
            if(txtNomeProduto.text.isNotEmpty()){
                var chave = txtNomeProduto.text.toString()
                var valores = sh.getString(chave,"")

                if(valores.isNullOrEmpty()) {
                    Toast.makeText(this,"Valores não encontrados",Toast.LENGTH_SHORT).show()
                }
                else{
                    var st = StringTokenizer(valores,";")
                    var strValorCompra:String = st.nextToken()
                    var strValorVenda:String = st.nextToken()

                    txtValorCompra.setText(strValorCompra)
                    txtValorVenda.setText(strValorVenda)

                    Toast.makeText(this,"Produto encontrado",Toast.LENGTH_SHORT).show()
                }


            }
            else{
                Toast.makeText(this,"O nome do produto está vazio",Toast.LENGTH_SHORT).show()
            }

        }

        btCalcular.setOnClickListener { v: View? ->
            if(txtValorCompra.text.isNotEmpty() && txtValorVenda.text.isNotEmpty()){

                var strValorCompra:String = txtValorCompra.text.toString()
                var strValorVenda:String = txtValorVenda.text.toString()

                var resultado = strValorVenda.toDouble() - strValorCompra.toDouble()

                if(resultado > 0.0){
                    Toast.makeText(this,"Lucro de " + (resultado).toString(),Toast.LENGTH_SHORT).show()
                }
                else if(resultado < 0.0){
                    Toast.makeText(this,"Prejuízo de " + (-resultado).toString(),Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Sem lucro ou prejuízo",Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this,"Faltam dados para calcular",Toast.LENGTH_SHORT).show()
            }





        }

    }
}