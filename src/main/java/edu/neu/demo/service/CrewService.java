package edu.neu.demo.service;

import edu.neu.demo.dao.CrewDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrewService {

    @Autowired
    private CrewDao crewDao;

    public void saveCrewInfo(JSONObject object) {
        String objectId = object.getString("crewID");
        crewDao.saveCrewInfo(objectId, object.toString());
    }
    public Object getCrewInfo(String keyName) {
        return crewDao.getCrewInfo(keyName);
    }

    public void deleteCrewInfo(String keyName) {
        crewDao.deleteCrewInfo(keyName);
    }

    public boolean hasKey(String keyName) {
        return crewDao.hasKey(keyName);
    }
}
