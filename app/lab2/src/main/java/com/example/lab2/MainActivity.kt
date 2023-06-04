package com.example.lab2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    private var inputField: EditText? = null
    private var outputField: EditText? = null
    private var currencySelectorOne: Spinner? = null
    private var currencySelectorTwo: Spinner? = null
    private var radioGroup: RadioGroup? = null
    private var radioButtonBuy: RadioButton? = null
    private var radioButtonSell: RadioButton? = null
    private var radioButtonNBU: RadioButton? = null
    private var rateBaseEditText: EditText? = null
    private var convertButton: Button? = null
    private var currencyApi: CurrencyApi? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputField = findViewById(R.id.input_field)
        outputField = findViewById(R.id.output_field)
        currencySelectorOne = findViewById(R.id.selector_one)
        currencySelectorTwo = findViewById(R.id.selector_two)
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonBuy = findViewById(R.id.radioButton_buy)
        radioButtonSell = findViewById(R.id.radioButton_sell)
        radioButtonNBU = findViewById(R.id.radioButton_NBU)
        rateBaseEditText = findViewById(R.id.r_b_editText)
        convertButton = findViewById(R.id.convert)
        currencyApi = RetrofitClient.retrofit.create(CurrencyApi::class.java)

        radioButtonBuy?.isChecked = true

        val currencyOptions = resources.getStringArray(R.array.currency_options)
        val currencyAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptions)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySelectorOne?.adapter = currencyAdapter

        val currencyOptionsTwo = resources.getStringArray(R.array.currency_options_2)
        val currencyAdapterTwo =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyOptionsTwo)
        currencyAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySelectorTwo?.adapter = currencyAdapterTwo

        convertButton?.setOnClickListener {
            val inputValue = inputField?.text.toString().toDoubleOrNull()
            if (inputValue != null) {
                val selectedCurrencyOne = currencySelectorOne?.selectedItem.toString()
                val selectedCurrencyTwo = currencySelectorTwo?.selectedItem.toString()
                val currencyCodeOne = getCurrencyCode(selectedCurrencyOne)
                val currencyCodeTwo = getCurrencyCode(selectedCurrencyTwo)
                if (currencyCodeOne == currencyCodeTwo) {
                    Toast.makeText(this@MainActivity, "Валюти однакові", Toast.LENGTH_SHORT).show()
                } else{
                    scope.launch {
                        try {
                            val currencies = currencyApi?.getCurrencies()
                            val rateBuy =
                                currencies?.find {
                                    it.currencyCodeA == currencyCodeOne &&
                                            it.currencyCodeB == currencyCodeTwo
                                }?.rateBuy
                                    ?: -1.0
                            val rateSell =
                                currencies?.find {
                                    it.currencyCodeA == currencyCodeOne &&
                                            it.currencyCodeB == currencyCodeTwo
                                }?.rateSell
                                    ?: -1.0
                            val rateCross =
                                currencies?.find {
                                    it.currencyCodeA == currencyCodeOne &&
                                            it.currencyCodeB == currencyCodeTwo
                                }?.rateCross
                                    ?: -1.0

                            val conversionResult = when {
                                radioButtonBuy?.isChecked == true -> inputValue * rateSell
                                radioButtonSell?.isChecked == true -> inputValue * rateSell
                                radioButtonNBU?.isChecked == true -> inputValue * rateCross
                                else -> -1.0
                            }

                            val conversionResult2 = when {
                                radioButtonBuy?.isChecked == true -> rateBuy
                                radioButtonSell?.isChecked == true -> rateSell
                                radioButtonNBU?.isChecked == true -> rateCross
                                else -> -1.0
                            }

                            outputField?.setText(conversionResult.toString())
                            rateBaseEditText?.setText(conversionResult2.toString())
                        } catch (e: HttpException) {
                            val errorBody = e.response()?.errorBody()?.string()
                            Toast.makeText(
                                this@MainActivity,
                                "API call failed: $errorBody",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
    }

/*    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCurrency = parent?.getItemAtPosition(position).toString()
        Toast.makeText(this, "Selected Currency: $selectedCurrency", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }*/
}

private fun getCurrencyCode(currency: String): Int {
    return when (currency) {
        "USD" -> 840
        "EUR" -> 978
        "UAH" -> 980
        "ZL" -> 985
        else -> 0
    }
}
