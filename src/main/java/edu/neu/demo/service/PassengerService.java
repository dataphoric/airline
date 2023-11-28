package edu.neu.demo.service;

import edu.neu.demo.dao.PassengerDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerDao passengerDao;

    public void savePassengerInfo(JSONObject object) {
        String objectId = object.getString("passID");
        passengerDao.savePassengerInfo(objectId, object.toString());
    }
    public Object getPassengerInfo(String keyName) {
        return passengerDao.getPassengerInfo(keyName);
    }

    public void deletePassengerInfo(String keyName) {
        passengerDao.deletePassengerInfo(keyName);
    }

    public boolean hasKey(String keyName) {
        return passengerDao.hasKey(keyName);
    }
}
