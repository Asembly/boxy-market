package asembly.cart_service.mapper;

import asembly.cart_service.entity.Cart;
import asembly.dto.cart.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "id", source = "id")
    CartResponse cartToCartResponse(Cart cart);
}
