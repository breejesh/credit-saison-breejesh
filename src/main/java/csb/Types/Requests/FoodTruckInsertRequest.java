package csb.Types.Requests;

import csb.Entities.FoodTruck;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class FoodTruckInsertRequest {
    private String applicantName;
    private ZonedDateTime expirationDate;
    private ZonedDateTime approvalDate;
    private Float latitude;
    private Float longitude;
    private String streetName;

    public static FoodTruck buildFoodTruck(FoodTruckInsertRequest request) {
        return FoodTruck.builder()
                .applicant(request.applicantName)
                .expirationDate(request.expirationDate)
                .address(request.streetName)
                .approved(request.approvalDate)
                .latitude(request.latitude)
                .longitude(request.longitude)
                .build();
    }
}
