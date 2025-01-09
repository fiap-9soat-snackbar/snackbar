package com.snackbar.payment.infrastructure.gateways;

// This should be equivalent to the previous ServiceImpl

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entities.Payment;
import com.snackbar.payment.infrastructure.persistence.PaymentEntity;
import com.snackbar.payment.infrastructure.persistence.PaymentRepository;

/*Logging imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

public class PaymentRepositoryGateway implements PaymentGateway {

    //Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(PaymentRepositoryGateway.class);

    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository, PaymentEntityMapper paymentEntityMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentEntityMapper = paymentEntityMapper;
    }

    @Override
    public Payment createPayment(Payment paymentDomainObj) {
        //logger.info("Saving payment to database: {}", paymentDomainObj);
        PaymentEntity paymentEntity = paymentEntityMapper.toEntity(paymentDomainObj);
        PaymentEntity savedObj = paymentRepository.save(paymentEntity);
        Payment createdPayment = paymentEntityMapper.toDomainObj(savedObj);
        //logger.info("Payment saved to database: {}", createdPayment);
        return createdPayment;
    }

}
