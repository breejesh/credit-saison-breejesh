package csb.Services;

import csb.Entities.FoodTruck;
import csb.JPA.FoodTruckDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FoodTruckService {
    private final FoodTruckDao foodTruckDao;

    public FoodTruckService(FoodTruckDao foodTruckDao) {
        this.foodTruckDao = foodTruckDao;
    }

    public List<FoodTruck> findClosestFoodTrucks(float longitude, float latitude, int limit) {
        if(limit < 0) {
            limit = 1;
        } else if(limit > 10) {
            limit = 10;
        }
        return foodTruckDao.findClosestFoodTrucks(longitude, latitude, limit);
    }

    public Page<FoodTruck> findByFilters(String applicantSearch, String addressSearch, ZonedDateTime expirationDate, Pageable page) {
        return foodTruckDao.findByFilters(applicantSearch, addressSearch, expirationDate, page);
    }

    public FoodTruck save(FoodTruck foodTruck) {
        return foodTruckDao.save(foodTruck);
    }

}
