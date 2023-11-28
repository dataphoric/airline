package edu.neu.demo.service;

import edu.neu.demo.dao.BoardingDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardingService {

    @Autowired
    private BoardingDao boardingDao;

    public void saveBoardingInfo(JSONObject object) {
        String objectId = object.getString("passNo");
        boardingDao.saveBoardingInfo(objectId, object.toString());
    }

    public Object getBoardingInfo(String keyName) {
        return boardingDao.getBoardingInfo(keyName);
    }

    public void deleteBoardingInfo(String keyName) {
        boardingDao.deleteBoardingInfo(keyName);
    }

    public boolean hasKey(String keyName) {
        return boardingDao.hasKey(keyName);
    }
}

