package edu.neu.demo.controller;

import edu.neu.demo.service.BoardingService;
import edu.neu.demo.utils.JsonValidator;
import edu.neu.demo.utils.EtagUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardingController {

    @Autowired
    private JsonValidator jsonValidator;

    @Autowired
    private BoardingService boardingService;

    @PostMapping("/api/boarding/{keyName}")
    public ResponseEntity<String> saveBoardingInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        JSONObject jsonObject = new JSONObject(usecase);

        if (jsonValidator.validate("/boarding/schema.json", jsonObject)) {
            boardingService.saveBoardingInfo(jsonObject);

            String etag = EtagUtil.generate(jsonObject.toString().getBytes());
            String responseBody = "Data Validation Successful. Upload boarding pass " + keyName + " Successful.";
            return ResponseEntity.ok().eTag(etag).body(responseBody);
        } else {
            return new ResponseEntity<>("Data Not Valid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/boarding/{keyName}")
    public ResponseEntity<String> getBoardingInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        Object boardingInfo = boardingService.getBoardingInfo(keyName);

        if (boardingInfo == null) {
            return ResponseEntity.notFound().build();
        }

        String etag = EtagUtil.generate(boardingInfo.toString().getBytes());
        String header = request.getHeader("If-None-Match");

        if (header != null & etag.equals(header)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(etag).build();
        } else {
            return ResponseEntity.ok().eTag(etag).body(boardingInfo.toString());
        }
    }

    @DeleteMapping("/api/boarding/{keyName}")
    public ResponseEntity<String> deleteBoardingInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        if (!boardingService.hasKey(keyName)) {
            return new ResponseEntity<>("Delete boarding pass" + keyName + " failed.", HttpStatus.NOT_FOUND);
        } else {
            String header = request.getHeader("If-Match");
            Object boarding = boardingService.getBoardingInfo(keyName);
            String etag = EtagUtil.generate(boarding.toString().getBytes());

            if (header != null && !etag.equals(header)) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).eTag(etag).build();
            }

            boardingService.deleteBoardingInfo(keyName);
            return new ResponseEntity<>("Remove boarding pass" + keyName + " successful.", HttpStatus.OK);
        }
    }

    @PutMapping("/api/boarding/{keyName}")
    public ResponseEntity<String> putBoardingInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        Object existedBoardingInfo = boardingService.getBoardingInfo(keyName);
        if (existedBoardingInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ResponseEntity<String> response = saveBoardingInfo(usecase, keyName);
        String responseBody = "Data Validation Successful. Update boarding pass" + keyName + " successful.";

        return response.ok().body(responseBody);
    }
}
