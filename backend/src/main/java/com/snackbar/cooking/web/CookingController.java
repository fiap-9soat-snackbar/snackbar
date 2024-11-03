package com.snackbar.cooking.web;

import org.springframework.web.bind.annotation.*;

import com.snackbar.cooking.domain.Cooking;
import com.snackbar.cooking.application.CookingService;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CookingController {

    @Autowired
    private CookingService cookingService;

    @GetMapping("/cooking")
    public ResponseEntity<List<Cooking>> getAllCookings() {
        List<Cooking> cookings = cookingService.findByStatusOrder("PREPARACAO");
        
        if (cookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return ResponseEntity.ok(cookings);
    }

    @GetMapping("/cooking/get-order-by-id/{id}")
    public ResponseEntity<Cooking> getCookingById(@PathVariable String id) {
        Cooking cooking = this.cookingService.getById(id);

        defineRemainingAwatingTime(cooking);

        return ResponseEntity.ok(cooking);
    }

    @GetMapping("/cooking/get-order-by-number/{order_number}")
    public ResponseEntity<Cooking> getCookingByNumber(@PathVariable String order_number) {
        Cooking cooking = this.cookingService.getByOrderNumber(order_number);

        defineRemainingAwatingTime(cooking);

        return ResponseEntity.ok(cooking);
    }

    @PutMapping("/cooking/receive-order/{id}")
    public ResponseEntity<String> receiveOrder(@PathVariable String id) {

        Cooking cooking = this.cookingService.getById(id);

        if(cooking.getStatusOrder().equals("PAGO")) {
            cooking.setStatusOrder("RECEBIDO");
            cookingService.updateOrder(cooking);
            return ResponseEntity.ok("Pedido recebido");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Impossível receber o pedido. O status do pedido solicitado é " + cooking.getStatusOrder());
        }

    }

    @PutMapping("/cooking/start-preparation/{id}")
    public ResponseEntity<String> startPreparation(@PathVariable String id) {

        Cooking cooking = this.cookingService.getById(id);

        if(cooking.getStatusOrder().equals("RECEBIDO")) {
            cooking.setStatusOrder("PREPARACAO");
            cookingService.updateOrder(cooking);
            return ResponseEntity.ok("Preparo iniciado");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Impossível iniciar preparo. O status do pedido solicitado é " + cooking.getStatusOrder());
        }

    }

    @PutMapping("/cooking/finish-preparation/{id}")
    public ResponseEntity<String> finishPreparation(@PathVariable String id) {
        Cooking cooking = this.cookingService.getById(id);

        if(cooking.getStatusOrder().equals("PREPARACAO")) {
            cooking.setStatusOrder("PRONTO");
            cookingService.updateOrder(cooking);
            return ResponseEntity.ok("Preparo pronto");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Impossível finalizar preparo. O status do pedido solicitado é " + cooking.getStatusOrder());
        }
    }

    private void defineRemainingAwatingTime (Cooking cooking) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dateTime = cooking.getOrderDateTime();
        int minutesToAdd = cooking.getWaitingTime();
        LocalDateTime deliverTime = dateTime.plusMinutes(minutesToAdd);
        long remainingWaitingTime = Duration.between(currentDateTime, deliverTime).toMinutes();
        remainingWaitingTime = remainingWaitingTime <= 0 ? 0 : remainingWaitingTime;
        cooking.setRemainingWaitingTime(remainingWaitingTime);

        return;
    }
}
