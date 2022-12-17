package csb.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity(name = "food_facility_permit")
public class FoodTruck {
    @Id
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @Column(name = "locationid")
    private long locationId;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "address")
    private String address;

    @Column(name = "permit")
    private String permit;

    @Column(name = "status")
    private String status;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "approved")
    private ZonedDateTime approved;

    @Column(name = "expirationdate")
    private ZonedDateTime expirationDate;
}
