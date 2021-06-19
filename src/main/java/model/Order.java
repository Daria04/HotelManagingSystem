package model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Order {

    private int id;
    private int userId;
    private int aptId;
    private LocalDate dateIn;
    private LocalDate dateOut;
}
