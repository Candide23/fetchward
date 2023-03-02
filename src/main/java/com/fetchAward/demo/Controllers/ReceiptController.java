package com.fetchAward.demo.Controllers;

import com.fetchAward.demo.Model.Item;
import com.fetchAward.demo.Model.Receipt;
import com.fetchAward.demo.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @Operation(summary = "Retrieves contacts", description = "Provides a list of all contacts")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Receipt.class))))
    @GetMapping(value = "/all")
    public ResponseEntity<List<Receipt>> getReceipts() {
        List<Receipt> receipts = receiptService.getReceipts();
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @Operation(summary = "Get contact by Id", description = "Returns a contact based on an ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Contact doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of contact", content = @Content(schema = @Schema(implementation = Receipt.class))),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Receipt> getContact(@PathVariable UUID id) {
        Receipt receipt = receiptService.getReceiptById(id);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @Operation(summary = "Create Contact", description = "Creates a contact from the provided payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of contact"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/create")
    public ResponseEntity<Receipt> createContact(@Valid @RequestBody Receipt receipt) {
        receiptService.saveReceipt(receipt);
        return new ResponseEntity<>(receipt, HttpStatus.CREATED);
    }

    @PostMapping("/process")
    public String processReceipt(@RequestBody Receipt receipt) {

        // Calculate the points awarded for this receipt
        int points = receiptService.calculatePoints(receipt);
        // Generate a unique ID for the receipt
        UUID id = UUID.randomUUID();
        // Store the receipt and its ID in memory or a database
        // ...
        receiptService.storeReceipt(id,points);
        return "{ \"id\": \"" + id + "\" }";
    }



    @GetMapping("/{id}/points")
    public String getPoints(@PathVariable UUID id) {
        // Lookup the receipt by its ID in memory or a database
        Receipt receipt =  receiptService.getReceiptById(id); // ...
        int points = receiptService.calculatePoints(receipt);
        return "{ \"points\": " + points + " }";
    }




}
