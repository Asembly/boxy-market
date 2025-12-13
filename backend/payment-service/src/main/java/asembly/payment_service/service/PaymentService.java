package asembly.payment_service.service;

import asembly.dto.CreatePaymentDto;
import asembly.dto.PaymentEventDto;
import asembly.dto.PaymentResponseDto;
import asembly.payment_service.client.PaymentClient;
import asembly.payment_service.entity.Payment;
import asembly.payment_service.mapper.PaymentMapper;
import asembly.payment_service.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private PaymentRepository paymentRepository;


    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    public ResponseEntity<PaymentResponseDto> createPayment(CreatePaymentDto dto){

        var paymentResponse = paymentClient.createPayment(dto);

        var payment = paymentMapper.paymentResponseToPayment(paymentResponse);
        var save = paymentRepository.save(payment);

        log.info("Save payment object: {}", save);

        return ResponseEntity.ok(paymentResponse);
    }


    public ResponseEntity<Payment> paymentEvent(PaymentEventDto dto)
    {
        log.info("Payment event: {}",dto);

        var payment = paymentRepository.findById(dto.object().id()).orElseThrow(
                //todo Payment not found exception
        );

        payment.setStatus(dto.object().status());

        var save = paymentRepository.save(payment);

        return ResponseEntity.ok(save);
    }

    public ResponseEntity<Iterable<Payment>> getPayments()
    {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    public void cancelPayment(){}

}
