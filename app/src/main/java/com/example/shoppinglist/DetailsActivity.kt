package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.shoppinglist.db.ItemEntity
import com.example.shoppinglist.retrofit.RetrofitAPI
import com.example.shoppinglist.retrofit.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToLong
import kotlin.math.round

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_acitivity)



        fun symbolFinder(a : String): Int{
            when(a) {
                "Food" -> return R.drawable.outline_local_dining_24
                "Electronics"-> return R.drawable.outline_devices_24
                else -> return R.drawable.outline_library_books_24
            }

        }

        val value = intent.extras?.get("123") as ItemEntity
        val checkboxview=findViewById<CheckBox>(R.id.checkbox2)
            .apply { isChecked=value.bought }
        val nametextview=findViewById<TextView>(R.id.Name2)
            .apply { text="Name: "+value.name }
        val categoryview=findViewById<ImageView>(R.id.symbol2)
            .apply{ setImageResource( symbolFinder(value.category)) }
        val descriptionview=findViewById<TextView>(R.id.details2)
            .apply {text="Description: "+value.description  }
        val hufview=findViewById<TextView>(R.id.HUF)
            .apply { text="Price in HUF "+value.price.toString() }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.frankfurter.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        lateinit var api : RetrofitAPI
        api = retrofit.create(RetrofitAPI::class.java)
        if(api==null)
            Log.i("API","api is null")
        else
            Log.i("API","api is not null")

        val currencyCall = api.getMoney("HUF")
        currencyCall.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e("API: ",t.message!!)
            }
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                var currencyResult = response.body()

                if(currencyResult==null)
                    Log.i("API: ", "result is null")

                val gbp: Double = currencyResult?.rates!!.GBP!!.toDouble()*value.price.toDouble()
                val usd: Double = currencyResult?.rates!!.USD!!.toDouble()*value.price.toDouble()
                val tryq: Double = currencyResult?.rates!!.TRY!!.toDouble()*value.price.toDouble()




            val eurview=findViewById<TextView>(R.id.GBP)
            .apply { text="Price in GBP: "+gbp.toString() }
        val usdview=findViewById<TextView>(R.id.USD)
            .apply { text="Price in USD: "+usd.toString() }
        val tryview=findViewById<TextView>(R.id.TRY)
            .apply { text="Price in TRY: "+tryq.toString() }

    }


})}
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }}