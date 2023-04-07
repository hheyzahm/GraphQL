package com.example.graphql.controller;

import com.example.graphql.model.Seller;

import com.example.graphql.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * @Created 07 04 2023 - 9:05 AM
 * @Author Hazeem Hassan
 */
@RestController
@RequestMapping("/restApi/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @RequestMapping(value = "/getAllSeller", method = RequestMethod.GET)
    public List<Seller> readAllSeller() {
        return sellerService.findAll();
    }

    @GetMapping("/getASeller/{sellerId}")
    public ResponseEntity<Seller> getASeller(@PathVariable UUID sellerId) {
        try {
            Seller seller = sellerService.getById(sellerId);
            return new ResponseEntity<Seller>(seller, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getSeller/{sellerId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSeller(@PathVariable(value = "sellerId") @RequestBody UUID sellerId) {
        Boolean sellerExists = sellerService.sellerExistsOperation(sellerId);
        if (sellerExists) {
            Seller seller = sellerService.getById(sellerId);
            return new ResponseEntity<Seller>(seller, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to fetch seller data because there's no seller exist in database against sellerId = " + sellerId, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addNewSeller", method = RequestMethod.POST)
    public Seller addNewSeller(@RequestBody Seller seller) {
        return sellerService.createSeller(seller);
    }

    @RequestMapping(value = "/updateOperation/{sellerId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOperation(@PathVariable(value = "sellerId") UUID sellerId, @RequestBody Seller details) {
        Boolean sellerExists = sellerService.sellerExistsOperation(sellerId);
        if (sellerExists) {
            return new ResponseEntity<Seller>(sellerService.update(sellerId, details), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to update seller because there's no seller exist in database springbootDB against sellerId = " + sellerId, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deleteOperation/{sellerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOperation(@PathVariable(value = "sellerId") @RequestBody UUID sellerID) {
        Boolean sellerExists = sellerService.sellerExistsOperation(sellerID);
        if (sellerExists) {
            sellerService.remove(sellerID);
            return new ResponseEntity<String>("User Successfully Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to delete seller because there's no seller exist in database springbootDB against sellerId = " + sellerID, HttpStatus.NOT_FOUND);
        }
    }
}
