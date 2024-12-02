package ssf.day18.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ssf.day18.config.Constants;
import ssf.day18.models.Carpark;

@Service
public class CarparkService {
    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparks() {
        String carparkJSON = restTemplate.getForObject(Constants.CARPARK_URL, String.class);

        List<Carpark> carparksList = Carpark.jsonToCarpark(carparkJSON);

        return carparksList;
    }
}
