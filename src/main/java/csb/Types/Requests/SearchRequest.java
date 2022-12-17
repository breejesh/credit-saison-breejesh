package csb.Types.Requests;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class SearchRequest {
    private String applicantName;
    private ZonedDateTime expirationDate;
    private String streetName;
    private Integer pageNumber;
}
