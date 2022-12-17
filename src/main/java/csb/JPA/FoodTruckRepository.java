package csb.JPA;

import csb.Entities.FoodTruck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM food_facility_permit order by st_distance(st_makepoint(longitude\\:\\:float, latitude\\:\\:float), st_makepoint(:longitude\\:\\:float , :latitude\\:\\:float)) asc limit :limit", nativeQuery = true)
    List<FoodTruck> findClosestFoodTrucks(@Param("longitude") float longitude, @Param("latitude") float latitude, @Param("limit") int limit);

    Page<FoodTruck> findByApplicantContains(String applicantSearch, Pageable page);

    Page<FoodTruck> findByAddressContains(String addressSearch, Pageable page);

    Page<FoodTruck> findByExpirationDateAfter(ZonedDateTime expirationDate, Pageable page);

}
