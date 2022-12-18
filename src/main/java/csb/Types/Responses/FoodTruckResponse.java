package csb.Types.Responses;

import csb.Entities.FoodTruck;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class FoodTruckResponse {
    private Long id;
    private String applicantName;
    private ZonedDateTime expirationDate;
    private ZonedDateTime approvalDate;
    private Float latitude;
    private Float longitude;
    private String streetName;

    public static FoodTruckResponse buildFromFoodTruck(FoodTruck foodTruck) {
        return FoodTruckResponse.builder()
                .latitude(foodTruck.getLatitude())
                .longitude(foodTruck.getLongitude())
                .applicantName(foodTruck.getApplicant())
                .expirationDate(foodTruck.getExpirationDate())
                .approvalDate(foodTruck.getApproved())
                .id(foodTruck.getLocationId())
                .streetName(foodTruck.getAddress())
                .build();
    }

}
