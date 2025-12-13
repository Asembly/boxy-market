package asembly.cart_service.kafka;

import asembly.cart_service.repository.CartRepository;
import asembly.cart_service.service.CartService;
import asembly.event.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "user-events", containerFactory = "userListener", groupId = "user-service-group")
public class ConsumerUser {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @KafkaHandler
    public void handler(UserEvent data){

        log.info("UserEvent: {}", data);

        switch(data.type())
        {
            case USER_DELETED -> {

                var cart = cartRepository.findCartByUserId(data.user_id()).orElseThrow(
                       //todo CartNotFound::new
                );

                cartRepository.delete(cart);
            }
            case USER_CREATED -> cartService.createCart(data.user_id());
        }
    }

}
