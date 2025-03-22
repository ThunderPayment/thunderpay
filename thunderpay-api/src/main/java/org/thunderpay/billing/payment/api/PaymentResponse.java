/**
 * @file PaymentResponse.java
 * @author Krisna Pranav
 * @brief Payment Response
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.payment.api;

public enum PaymentResponse {
    INVALID_CARD("The card number, expiry date or cvc is invalid or incorrect"),
    EXPIRED_CARD("The card has expired"),
    LOST_OR_STOLEN_CARD("The card has been lost or stolen"),

    DO_NOT_HONOR("Do not honor the card - usually a problem with account"),
    INSUFFICIENT_FUNDS("The account had insufficient funds to fulfil the payment"),
    DECLINE("Generic payment decline"),

    PROCESSING_ERROR("Error processing card"),
    INVALID_AMOUNT("An invalid amount was entered"),
    DUPLICATE_TRANSACTION("A transaction with identical amount and credit card information was submitted very recently."),

    OTHER("Some other error");

    private final String description;

    private PaymentResponse(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}