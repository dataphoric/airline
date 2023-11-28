package edu.neu.demo.controller;

import edu.neu.demo.service.CrewService;
import edu.neu.demo.utils.EtagUtil;
import edu.neu.demo.utils.JsonValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CrewController {

    @Autowired
    private JsonValidator jsonValidator;

    @Autowired
    private CrewService crewService;

    //save crewInfo to redis & validation
    @PostMapping("/api/crew/{keyName}")
    public ResponseEntity<String> saveCrewInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        JSONObject jsonObject = new JSONObject(usecase);

        //validation
        if (jsonValidator.validate("/crew/schema.json", jsonObject)) {
            System.out.println(keyName);
            crewService.saveCrewInfo(jsonObject);

            String etag = EtagUtil.generate(jsonObject.toString().getBytes());
            System.out.println("Valid!");

            String responseBody = "Data Validation Successful. Save crew " + keyName + " successful.";
            return ResponseEntity.ok().eTag(etag).body(responseBody);
        } else {
            System.out.println("Data not valid!");
            return new ResponseEntity<>("Data Not Valid", HttpStatus.BAD_REQUEST);
        }
    }

    //get crewInfo from redis & validation
    @GetMapping("/api/crew/{keyName}")
    public ResponseEntity<String> getCrewInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        Object crewInfo = crewService.getCrewInfo(keyName);

        if (crewInfo == null) {
            return ResponseEntity.notFound().build();
        }

        String etag = EtagUtil.generate(crewInfo.toString().getBytes());
        String header = request.getHeader("If-None-Match");

        //validation
        if (header != null & etag.equals(header)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(etag).build();
        } else {
            return ResponseEntity.ok().eTag(etag).body(crewInfo.toString());
        }
    }

    //Delete crewInfo from redis
    @DeleteMapping("/api/crew/{keyName}")
    public ResponseEntity<String> deleteCrewInfo(@PathVariable("keyName") String keyName, HttpServletRequest request) {
        if (!crewService.hasKey(keyName)) {
            //if the key does not exist, return 404
            return new ResponseEntity<>("Delete crew " + keyName + " failed.", HttpStatus.NOT_FOUND);
        } else {
            //Conditional write
            String header = request.getHeader("If-Match");
            Object crew = crewService.getCrewInfo(keyName);
            String etag = EtagUtil.generate(crew.toString().getBytes());
            System.out.println("header:" + header);
            System.out.println("Etag:" + etag);
            if (header != null && !etag.equals(header)) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).eTag(etag).build();
            }

            //if the key does exist, delete the key and return 200
            crewService.deleteCrewInfo(keyName);
            return new ResponseEntity<>("Delete crew " + keyName + " successful.", HttpStatus.OK);
        }
    }

    //Update crewInfo
    @PutMapping("/api/crew/{keyName}")
    public ResponseEntity<String> putCrewInfo(@RequestBody String usecase, @PathVariable("keyName") String keyName) {
        //Determine crewInfo exist in redis or not
        Object existedCrewInfo = crewService.getCrewInfo(keyName);
        if (existedCrewInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ResponseEntity<String> response = saveCrewInfo(usecase, keyName);
        String responseBody = "Data Validation Successful. Update crew " + keyName + " successful.";

        return response.ok().body(responseBody);
    }


}
