package com.fetchAward.demo.Controllers;

import com.fetchAward.demo.Model.Item;
import com.fetchAward.demo.Model.Receipt;
import com.fetchAward.demo.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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



    @Operation(summary = "Submits a receipt for processing", description = "Submits a receipt for processing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Returns the ID assigned to the receipt"),
            @ApiResponse(responseCode = "400", description = "The receipt is invalid", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {

        return new ResponseEntity<>(receiptService.saveReceipt(receipt), HttpStatus.CREATED);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receipt> deleteReceipt(@PathVariable UUID id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Returns the points awarded for the receipt", description = "Returns the points awarded for the receipt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " The number of points awarded"),
            @ApiResponse(responseCode = "400", description = "No receipt found for that id", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<Receipt>> getReceipts() {
        List<Receipt> receipts = receiptService.getReceipts();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
