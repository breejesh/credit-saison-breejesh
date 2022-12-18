package csb.Controllers;

import csb.Entities.FoodTruck;
import csb.Services.FoodTruckService;
import csb.Types.Requests.FoodTruckInsertRequest;
import csb.Types.Requests.SearchRequest;
import csb.Types.Responses.FoodTruckResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<FoodTruckResponse> search(@RequestBody SearchRequest searchRequest) {
        Page<FoodTruck> page = foodTruckService.findByFilters(searchRequest.getApplicantName(), searchRequest.getStreetName(), searchRequest.getExpirationDate(), PageRequest.of(searchRequest.getPageNumber() - 1, 10));
        List<FoodTruckResponse> foodTruckResponses = page.getContent().stream().map(foodTruck -> FoodTruckResponse.buildFromFoodTruck(foodTruck)).collect(Collectors.toList());
        return new PageImpl<>(foodTruckResponses, PageRequest.of(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    @RequestMapping(value = "/find-closest", method = RequestMethod.GET)
    public List<FoodTruckResponse> findClosest(@RequestParam("longitude") float longitude, @RequestParam("latitude") float latitude, @RequestParam("limit") int limit) {
        return foodTruckService.findClosestFoodTrucks(longitude, latitude, limit)
                .stream().map(foodTruck -> FoodTruckResponse.buildFromFoodTruck(foodTruck))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public FoodTruckResponse add(@RequestBody FoodTruckInsertRequest insertRequest) {
        return FoodTruckResponse.buildFromFoodTruck(foodTruckService.save(FoodTruckInsertRequest.buildFoodTruck(insertRequest)));
    }

}
