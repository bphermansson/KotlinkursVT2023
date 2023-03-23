package com.paheco.billing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode

class MainActivity : AppCompatActivity() {
/*
Skapa billingclient
BillingClient connect
Hämta produkter
... Visa på skärmen
Köp av produkt
Gick det bra?
Godkänn köp
 */

    var theproducts : List<ProductDetails>? = null

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            Log.i("pia11debug", "Purchase update")
            if(billingResult.responseCode == BillingResponseCode.OK) {
                Log.i("pia11debug", "Köp ok")
                for(purprod in purchases!!) {
                    for (pprod in purprod.products) {
                        Log.i("pia11debug", "Köpt " + pprod)
                        if (pprod == "pia11premium") {
                            confirmPremium(purprod)
                        }
                        if (pprod == "pia11credit") {
                            confirmPremium(purprod)
                        }
                    }
                }
            }
            else {
                Log.i("pia11debug", "Köp ej ok")

            }
        }

    private lateinit var billingClient : BillingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    Log.i("pia11debug", "Billing RESPONSE OK")
                    getProducts()
                }
                else {
                    Log.i("pia11debug", billingResult.debugMessage)
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
        findViewById<Button>(R.id.prod1BTN).setOnClickListener{
            buyProduct(theproducts!![0])
        }
        findViewById<Button>(R.id.prod2buyBTN).setOnClickListener{
            buyProduct(theproducts!![1])

        }
    }
    fun buyProduct(prod : ProductDetails) {
        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                .setProductDetails(prod)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

    // Launch the billing flow
        val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)

    }

    fun confirmPremium(purs: Purchase) {
        val acknowledgePurchaseResponseListener = AcknowledgePurchaseParams.newBuilder()
        .setPurchaseToken(purs.purchaseToken)


    }
    fun confirmCredit(purs: Purchase) {
        val consumeParams =
            ConsumeParams.newBuilder()
                .setPurchaseToken(purs.getPurchaseToken())
                .build()
        billingClient.consumeAsync(consumeParams) {billingresult, resultstring ->

        }
    }

    fun getHistory(){
        val params = QueryPurchaseHistoryParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP).build()
        billingClient.queryPurchaseHistoryAsync(params) { billingresult, purchasehistory ->
            if(purchasehistory != null){
                for(phist in purchasehistory) {
                    for(prod in phist.products) {
                        Log.i("pia11debug", "Köpt produkt " + prod)
                    }
                }
            }
        }
    }

    fun getProducts() {
        // En produkt med ett id
        var prod1 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("pia11premium")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        var prod2 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("pia11credit")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        // En lista med alla produkter
        val allproducts = mutableListOf<QueryProductDetailsParams.Product>()
        allproducts.add(prod1)
        allproducts.add(prod2)

        val queryParams = QueryProductDetailsParams.newBuilder().setProductList(allproducts).build()

        billingClient.queryProductDetailsAsync(queryParams) {
                billingResult,
                productDetailsList ->
            // check billingResult
            // process returned productDetailsList

            if(billingResult.responseCode == BillingResponseCode.OK) {
                Log.i("pia11debug", "PRODUCT RESPONSE OK")
                theproducts = productDetailsList
                Log.i("pia11debug", "Nr of products: " + theproducts!!.size.toString())
                showProducts()
            }
            else {
                Log.i("pia11debug", "PRODUCT RESPONSE ERROR")
            }



        }
    }
    fun showProducts(){
        findViewById<TextView>(R.id.prod1titleTV).text = theproducts!![0].title
        findViewById<TextView>(R.id.prod2titleTV).text = theproducts!![1].title
        findViewById<TextView>(R.id.prod1descTV).text = theproducts!![0].description
        findViewById<TextView>(R.id.prod2descTV).text = theproducts!![1].description
    }
}