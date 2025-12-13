package asembly.payment_service.mapper;

import asembly.dto.PaymentResponseDto;
import asembly.payment_service.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "id", source = "id")
    Payment paymentResponseToPayment(PaymentResponseDto dto);
}
