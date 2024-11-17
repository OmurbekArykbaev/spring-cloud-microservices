package kg.nurtelecom.order_service.service;

import kg.nurtelecom.order_service.dto.InventoryResponse;
import kg.nurtelecom.order_service.dto.OrderLIneItemDto;
import kg.nurtelecom.order_service.dto.OrderRequest;
import kg.nurtelecom.order_service.model.Order;
import kg.nurtelecom.order_service.model.OrderLineItems;
import kg.nurtelecom.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLIneItemDto()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();

        order.setOrderLineItemsList(orderLineItems);

       List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        Call inventory service  and place order if product is in stock
        InventoryResponse[] inventoryResponseArray = webClientBuilder
                .build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock =
                Arrays
                        .stream(inventoryResponseArray)
                        .allMatch(InventoryResponse::isInStock);

       if(allProductsInStock) {
           System.out.println("qwe");
           orderRepository.save(order);
       } else {
           throw new IllegalArgumentException("Product is not is stock, please try again");
       }


    }

    public OrderLineItems mapToDto(OrderLIneItemDto orderLIneItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLIneItemDto.getPrice());
        orderLineItems.setQuantity(orderLIneItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLIneItemDto.getSkuCode());

        return orderLineItems;
    }
}
