package csb.JPA;

import csb.Entities.FoodTruck;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodTruckDao {
    private final FoodTruckRepository foodTruckRepository;

    public FoodTruckDao(FoodTruckRepository foodTruckRepository) {
        this.foodTruckRepository = foodTruckRepository;
    }

    public List<FoodTruck> findClosestFoodTrucks(float longitude, float latitude, int limit) {
        return foodTruckRepository.findClosestFoodTrucks(longitude, latitude, limit);
    }

    public Page<FoodTruck> findByFilters(String applicantSearch, String addressSearch, ZonedDateTime expirationDate, Pageable page) {
        Specification<FoodTruck> spec =  (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(applicantSearch)) {
                predicates.add(cb.like(root.get("applicant"), applicantSearch));
            }
            if(StringUtils.hasText(addressSearch)) {
                predicates.add(cb.like(root.get("address"), addressSearch));
            }

            if(Objects.nonNull(expirationDate)) {
                predicates.add(cb.greaterThan(root.get("expirationDate"), expirationDate));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
        return foodTruckRepository.findAll(spec, page);
    }

    public FoodTruck save(FoodTruck foodTruck) {
        return foodTruckRepository.save(foodTruck);
    }
}
