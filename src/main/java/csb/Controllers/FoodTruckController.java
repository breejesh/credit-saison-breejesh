package csb.Controllers;

import csb.Entities.FoodTruck;
import csb.Services.FoodTruckService;
import csb.Types.Requests.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<FoodTruck> search(@RequestBody SearchRequest searchRequest) {
        return foodTruckService.findByFilters(searchRequest.getApplicantName(), searchRequest.getStreetName(), searchRequest.getExpirationDate(), PageRequest.of(searchRequest.getPageNumber() - 1, 10));
    }

    @RequestMapping(value = "/find-closest", method = RequestMethod.GET)
    public List<FoodTruck> findClosest(@RequestParam("longitude") float longitude, @RequestParam("latitude") float latitude, @RequestParam("limit") int limit) {
        return foodTruckService.findClosestFoodTrucks(longitude, latitude, limit);
    }
}
