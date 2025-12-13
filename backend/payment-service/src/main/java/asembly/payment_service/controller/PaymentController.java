package asembly.payment_service.controller;

import asembly.dto.CreatePaymentDto;
import asembly.dto.PaymentEventDto;
import asembly.dto.PaymentResponseDto;
import asembly.payment_service.entity.Payment;
import asembly.payment_service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("payment-service")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreatePaymentDto dto){
        return paymentService.createPayment(dto);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Payment>> getPayments()
    {
        return paymentService.getPayments();
    }

    @PostMapping("/notify")
    public ResponseEntity<Payment> paymentEvent(@RequestBody PaymentEventDto dto)
    {
        return paymentService.paymentEvent(dto);
    }

}
