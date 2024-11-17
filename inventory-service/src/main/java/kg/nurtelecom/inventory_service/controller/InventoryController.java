package kg.nurtelecom.inventory_service.controller;

import kg.nurtelecom.inventory_service.dto.InventoryResponse;
import kg.nurtelecom.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> IsInStock(@RequestParam List<String> skuCode) {
        return inventoryService.IsInStock(skuCode);
    }
}