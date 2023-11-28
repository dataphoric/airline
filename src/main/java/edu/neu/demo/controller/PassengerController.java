package edu.neu.demo.controller;

import edu.neu.demo.service.PassengerService;
import edu.neu.demo.utils.JsonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import edu.neu.demo.utils.EtagUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PassengerController {

    @Autowired
    private JsonValidator jsonValidator;

    @Autowired
    private PassengerService passengerService;

    //save passengerInfo to redis & validation
    @PostMapping("/api/passenger/{keyName}")
    public ResponseEntity<String> savePassengerInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        JSONObject jsonObject = new JSONObject(usecase);

        //validation
        if (jsonValidator.validate("/passenger/schema.json", jsonObject)) {
            System.out.println(keyName);
            passengerService.savePassengerInfo(jsonObject);

            String etag = EtagUtil.generate(jsonObject.toString().getBytes());
            System.out.println("Valid!");

            String responseBody = "Data Validation Successful. Save passenger " + keyName + " Successful.";
            return ResponseEntity.ok().eTag(etag).body(responseBody);
        } else {
            System.out.println("Data not valid!");
            return new ResponseEntity<>("Data Not Valid", HttpStatus.BAD_REQUEST);
        }
    }

    //get passengerInfo from redis & validation
    @GetMapping("/api/passenger/{keyName}")
    public ResponseEntity<String> getPassengerInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        Object passengerInfo = passengerService.getPassengerInfo(keyName);

        if (passengerInfo == null) {
            return ResponseEntity.notFound().build();
        }

        String etag = EtagUtil.generate(passengerInfo.toString().getBytes());
        String header = request.getHeader("If-None-Match");

        //validation
        if (header != null & etag.equals(header)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(etag).build();
        } else {
            return ResponseEntity.ok().eTag(etag).body(passengerInfo.toString());
        }
    }

    //Delete passengerInfo from redis
    @DeleteMapping("/api/passenger/{keyName}")
    public ResponseEntity<String> deletePassengerInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        if (!passengerService.hasKey(keyName)) {
            //if the key does not exist, return 404
            return new ResponseEntity<>("Delete passenger " + keyName + " failed.", HttpStatus.NOT_FOUND);
        } else {
            //Conditional write
            String header = request.getHeader("If-Match");
            Object passenger = passengerService.getPassengerInfo(keyName);
            String etag = EtagUtil.generate(passenger.toString().getBytes());
            System.out.println("header:" + header);
            System.out.println("Etag:" + etag);
            if (header != null && !etag.equals(header)) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).eTag(etag).build();
            }

            //if the key does exist, delete the key and return 200
            passengerService.deletePassengerInfo(keyName);
            return new ResponseEntity<>("Delete passenger " + keyName + " successful.", HttpStatus.OK);
        }
    }

    //Update passengerInfo
    @PutMapping("/api/passenger/{keyName}")
    public ResponseEntity<String> putPassengerInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        //Determine passengerInfo exist in redis or not
        Object existedPassengerInfo = passengerService.getPassengerInfo(keyName);
        if (existedPassengerInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ResponseEntity<String> response = savePassengerInfo(usecase, keyName);
        String responseBody = "Data Validation Successful. Update passenger " + keyName + " successful.";

        return response.ok().body(responseBody);
    }
}
