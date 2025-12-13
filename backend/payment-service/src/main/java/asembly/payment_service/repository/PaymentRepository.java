package asembly.payment_service.repository;

import asembly.payment_service.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, String> {

}
