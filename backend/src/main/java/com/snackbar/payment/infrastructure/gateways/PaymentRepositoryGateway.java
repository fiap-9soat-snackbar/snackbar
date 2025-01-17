package com.snackbar.payment.infrastructure.gateways;

import java.util.List;

// This should be equivalent to the previous ServiceImpl

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.infrastructure.persistence.PaymentEntity;
import com.snackbar.payment.infrastructure.persistence.PaymentMPEntity;
import com.snackbar.payment.infrastructure.persistence.PaymentRepository;
import com.snackbar.payment.infrastructure.persistence.PaymentMPRepository;

/*Logging imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

public class PaymentRepositoryGateway implements PaymentGateway {

    //Logging definition
    //private static final Logger logger = LoggerFactory.getLogger(PaymentRepositoryGateway.class);

    private final PaymentRepository paymentRepository;
    private final PaymentMPRepository paymentMPRepository;
    private final PaymentEntityMapper paymentEntityMapper;
    private final PaymentMPEntityMapper paymentMPEntityMapper;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository,
                                    PaymentMPRepository paymentMPRepository,
                                    PaymentEntityMapper paymentEntityMapper,
                                    PaymentMPEntityMapper paymentMPEntityMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMPRepository = paymentMPRepository;
        this.paymentEntityMapper = paymentEntityMapper;
        this.paymentMPEntityMapper = paymentMPEntityMapper;
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

    @Override
    public List<Payment> listPayments() {
        List<PaymentEntity> retrievedObjList = paymentRepository.findAll();
        List<Payment> retrievedPaymentsList = paymentEntityMapper.toDomainListObj(retrievedObjList);
        return retrievedPaymentsList;
        
    }

    @Override
    public PaymentMP createPaymentMP(PaymentMP paymentMPDomainObj) {
        //logger.info("Saving payment to database: {}", paymentDomainObj);
        PaymentMPEntity paymentMPEntity = paymentMPEntityMapper.toEntity(paymentMPDomainObj);
        PaymentMPEntity savedObj = paymentMPRepository.save(paymentMPEntity);
        PaymentMP createdPaymentMP = paymentMPEntityMapper.toDomainObj(savedObj);
        //logger.info("Payment saved to database: {}", createdPayment);
        return createdPaymentMP;
    }

}
