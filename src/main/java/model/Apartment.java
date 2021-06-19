package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Apartment {

    private int aptId;
    private int cost;
    private Comfort comfort;
    private Accomodation accomodation;
    private int roomNumber;

}
