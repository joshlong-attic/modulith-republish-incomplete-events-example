package com.example.service.orders;

import com.example.service.inventory.InventoryUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Set;


@Transactional
@RestController
@RequestMapping("/orders")
class OrdersController {

    private final OrderRepository repository;

    private final ApplicationEventPublisher publisher;

    OrdersController(OrderRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping
    Collection<Order> all() {
        return this.repository.findAll();
    }

    @PostMapping
    void placeOrder(@RequestBody Order order) {
        var saved = this.repository.save(order);
        System.out.println("saved [" + saved + "]");
        saved.lineItems().forEach(li ->
                publisher.publishEvent(
                        new InventoryUpdatedEvent(saved.id(), li.product(), li.quantity())));
        System.out.println("published!");
    }
}

interface OrderRepository extends ListCrudRepository<Order, Integer> {
}

@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems) {

    void addLineItem(LineItem lineItem) {
        this.lineItems().add(lineItem);
    }
}

@Table("orders_line_items")
record LineItem(@Id Integer id, int quantity, int product) {
}