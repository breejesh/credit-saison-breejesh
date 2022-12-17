package csb.Types.Requests;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class FoodTruckInsertRequest {
    private String applicantName;
    private ZonedDateTime expirationDate;
    private String streetName;
}
