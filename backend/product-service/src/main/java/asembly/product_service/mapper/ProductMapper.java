package asembly.product_service.mapper;

import asembly.dto.product.ProductResponse;
import asembly.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse productToProductResponse(Product product);

}
