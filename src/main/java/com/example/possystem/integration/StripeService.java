package com.example.possystem.integration;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripeService {

    // TODO: Add your Stripe secret key here
    // @Value("${stripe.secret.key}")
    private String secretKey = "YOUR_STRIPE_SECRET_KEY";

    // @PostConstruct
    // public void init() {
    //     Stripe.apiKey = secretKey;
    // }

    public PaymentIntent createPaymentIntent(BigDecimal amount) throws StripeException {
        System.out.println("--- Placeholder: Creating Stripe Payment Intent ---");
        // PaymentIntentCreateParams params =
        //         PaymentIntentCreateParams.builder()
        //                 .setAmount(amount.longValue() * 100) // Amount in cents
        //                 .setCurrency("usd") // Or your desired currency
        //                 .build();
        // return PaymentIntent.create(params);
        return new PaymentIntent(); // Return a dummy object
    }
}
