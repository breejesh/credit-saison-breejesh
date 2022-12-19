package csb.Controllers;

import csb.Entities.FoodTruck;
import csb.Services.FoodTruckService;
import csb.Types.Requests.FoodTruckInsertRequest;
import csb.Types.Requests.SearchRequest;
import csb.Types.Responses.FoodTruckResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class FoodTruckController {

    private final FoodTruckService foodTruckService;

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {
        if(!StringUtils.hasText(searchRequest.getApplicantName()) && !StringUtils.hasText(searchRequest.getStreetName()) && Objects.isNull(searchRequest.getExpirationDate())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<FoodTruck> page = foodTruckService.findByFilters(searchRequest.getApplicantName(), searchRequest.getStreetName(), searchRequest.getExpirationDate(), PageRequest.of(searchRequest.getPageNumber() - 1, 10));
        List<FoodTruckResponse> foodTruckResponses = page.getContent().stream().map(foodTruck -> FoodTruckResponse.buildFromFoodTruck(foodTruck)).collect(Collectors.toList());
        return new ResponseEntity<>(new PageImpl<>(foodTruckResponses, PageRequest.of(page.getNumber(), page.getSize()), page.getTotalElements()), HttpStatus.OK);
    }

    @RequestMapping(value = "/find-closest", method = RequestMethod.GET)
    public ResponseEntity<?> findClosest(@RequestParam("longitude") float longitude, @RequestParam("latitude") float latitude, @RequestParam("limit") int limit) {
        return new ResponseEntity<>(foodTruckService.findClosestFoodTrucks(longitude, latitude, limit)
                .stream().map(foodTruck -> FoodTruckResponse.buildFromFoodTruck(foodTruck))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public  ResponseEntity<?> add(@RequestBody FoodTruckInsertRequest insertRequest) {
        if(!StringUtils.hasText(insertRequest.getApplicantName()) || !StringUtils.hasText(insertRequest.getStreetName()) || Objects.isNull(insertRequest.getApprovalDate()) || Objects.isNull(insertRequest.getExpirationDate()) || Objects.isNull(insertRequest.getLatitude()) || Objects.isNull(insertRequest.getLatitude())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FoodTruckResponse.buildFromFoodTruck(foodTruckService.save(FoodTruckInsertRequest.buildFoodTruck(insertRequest))), HttpStatus.OK);
    }

}
