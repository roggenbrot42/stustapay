package de.stustanet.stustapay.ec

import android.app.Activity
import android.os.Bundle
import com.sumup.merchant.reader.api.SumUpAPI
import com.sumup.merchant.reader.api.SumUpLogin
import com.sumup.merchant.reader.api.SumUpPayment
import com.sumup.merchant.reader.api.SumUpState
import com.sumup.merchant.reader.models.TransactionInfo
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SumUpHandler @Inject constructor(var activity: Activity, var intentRequestCode: Int) {

    fun init(){
        SumUpState.init(activity)
    }

    fun pay() {
        if (!SumUpAPI.isLoggedIn()) {
            val sumupLogin = SumUpLogin.builder("affiliatetoken").build()
            SumUpAPI.openLoginActivity(activity, sumupLogin, 1) // todo use intent request code
        }
        // after successful login

        val payment = SumUpPayment.builder() // mandatory parameters
            .total(BigDecimal("1.12")) // minimum 1.00
            .currency(SumUpPayment.Currency.EUR) // optional: include a tip amount in addition to the total
            .tip(BigDecimal("0.10")) // optional: add details
            .title("Taxi Ride")
            .receiptEmail("customer@mail.com")
            .receiptSMS("+3531234567890") // optional: Add metadata
            .addAdditionalInfo("AccountId", "taxi0334")
            .addAdditionalInfo("From", "Paris")
            .addAdditionalInfo("To", "Berlin") // optional: foreign transaction ID, must be unique!
            .foreignTransactionId(
                UUID.randomUUID().toString()
            ) // stustapay-id! can not exceed 128 chars
            // optional: skip the success screen
            .skipSuccessScreen() // optional: skip the failed screen
            .skipFailedScreen()
            .build()


        // TODO: use more modern registerForActivityResult
        SumUpAPI.checkout(activity, payment, intentRequestCode)
    }

    fun settings() {
        // open reader settings
        SumUpAPI.openCardReaderPage(activity, 4);
    }

    fun paymentResult(resultCode: Int, extras: Bundle?) {
        // Handle the response here
        if (resultCode == Activity.RESULT_OK) {
            // There are no request codes
            // doSomeOperations()
            if (extras == null) {
                error("no sumup intent extras")
            }

            val resultCode = extras.getInt(SumUpAPI.Response.RESULT_CODE)
            if (resultCode != SumUpAPI.Response.ResultCode.SUCCESSFUL) {
                // error
            }
            val resultString = extras.getString(SumUpAPI.Response.MESSAGE)
            val txCode = extras.getString(SumUpAPI.Response.TX_CODE)
            val receiptSent = extras.getBoolean(SumUpAPI.Response.RECEIPT_SENT)
            val txInfo = extras.getParcelable<TransactionInfo>(SumUpAPI.Response.TX_INFO)
            // api 33: val txInfo = extras.getParcelable(SumUpAPI.Response.TX_INFO, TransactionInfo::class.java)
        }
    }
}