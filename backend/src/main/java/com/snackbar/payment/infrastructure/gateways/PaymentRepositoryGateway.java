package com.snackbar.payment.infrastructure.gateways;

// This should be equivalent to the previous ServiceImpl

import com.snackbar.payment.application.gateways.PaymentGateway;
import com.snackbar.payment.domain.entity.Payment;
import com.snackbar.payment.domain.entity.PaymentMP;
import com.snackbar.payment.infrastructure.persistence.PaymentEntity;
import com.snackbar.payment.infrastructure.persistence.PaymentMPEntity;
import com.snackbar.payment.infrastructure.persistence.PaymentRepository;
import com.snackbar.payment.infrastructure.persistence.PaymentMPRepository;

import java.util.List;

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

    @Override
    public Payment getPaymentById(String id) {
        PaymentEntity retrievedObj = paymentRepository.findById(id).orElse(null);
        Payment retrievedPayment = paymentEntityMapper.toDomainObj(retrievedObj);
        return retrievedPayment;
    } 

    @Override
    public Payment updatePaymentExternalIdById(String id, String externalPaymentId) {
        PaymentEntity retrievedPayment = paymentRepository.findById(id).orElse(null);
        retrievedPayment.setExternalPaymentId(externalPaymentId);
        PaymentEntity savedObj = paymentRepository.save(retrievedPayment);
        Payment updatedPayment = paymentEntityMapper.toDomainObj(savedObj);
        return updatedPayment;
    }

    @Override
    public Payment getPaymentByExternalId(String externalId) {
        PaymentEntity retrievedObj = paymentRepository.findByExternalPaymentId(externalId).orElse(null);
        Payment retrievedPayment = paymentEntityMapper.toDomainObj(retrievedObj);
        return retrievedPayment;
    }

    @Override
    public Payment updatePaymentStatusByExternalId(String externalId, String paymentStatus) {
        PaymentEntity retrievedPayment = paymentRepository.findByExternalPaymentId(externalId).orElse(null);
        retrievedPayment.setPaymentStatus(paymentStatus);
        PaymentEntity savedObj = paymentRepository.save(retrievedPayment);
        Payment updatedPayment = paymentEntityMapper.toDomainObj(savedObj);
        return updatedPayment;
    }

}
