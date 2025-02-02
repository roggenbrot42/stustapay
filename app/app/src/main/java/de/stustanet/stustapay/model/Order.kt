package de.stustanet.stustapay.model

import kotlinx.serialization.Serializable


/**
 * OrderType enum from core model.
 */
@Serializable
enum class OrderType(val id: String) {
    sale("sale"),
    topup_cash("topup_cash"),
    topup_sumup("topup_sumup"),
}

/**
 * NewLineItem class from core model.
 * either quantity or price must be specified.
 */
@Serializable
data class NewLineItem(
    val product_id: Int,
    val quantity: Int? = null,
    val price: Double? = null,
) {
    init {
        require((quantity != null) != (price != null)) {
            "either price or quantity must be set"
        }
    }
}

/**
 * NewOrder class defined in core model.
 */
@Serializable
data class NewOrder(
    val positions: List<NewLineItem>,
    val order_type: OrderType,
    val customer_tag: ULong,
)

/**
 * Returned once we create an order.
 * PendingOrder class defined in core model.
 */
@Serializable
data class PendingOrder(
    val id: Int,
    val uuid: String,
    val old_balance: Double,
    val new_balance: Double,
)

/**
 * Returned once the order is booked.
 * CompletedOrder class defined in core mode.
 */
@Serializable
data class CompletedOrder(
    val id: Int,
    val uuid: String,
    val old_balance: Double,
    val new_balance: Double,
)