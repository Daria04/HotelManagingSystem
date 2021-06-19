package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private int userId;
    private String name;
    private String lastName;
    private long passportData;
    private String email;
    private long phoneNumber;

}
