package asembly.cart_service.kafka;

import asembly.cart_service.repository.CartRepository;
import asembly.cart_service.service.CartService;
import asembly.event.ProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "product-events", containerFactory = "productListener", groupId = "product-service-group")
public class ConsumerProduct {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @KafkaHandler
    public void handler(ProductEvent data){

        var cart = cartRepository.findCartByUserId(data.product_id()).orElse(
            cartService.createCart(data.product_id())
        );

        switch(data.type())
        {
//            case PRODUCT_DELETED -> {
//                cartRepository.delete(cart);
//            }
           //todo List of events
        }
//        chatRepository.saveAll(chats);
    }

}
