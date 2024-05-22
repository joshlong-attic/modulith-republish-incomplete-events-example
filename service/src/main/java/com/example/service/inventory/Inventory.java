package com.example.service.inventory;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;


@Service
class Inventory {

    @ApplicationModuleListener
    void inventoryUpdatedEvent(InventoryUpdatedEvent inventoryUpdatedEvent) throws Exception {
        System.out.println("got inventoryUpdatedEvent");
        Thread.sleep(10_000);
        System.out.println("processed inventoryUpdatedEvent! ["
                + inventoryUpdatedEvent + "] ");


    }
}
