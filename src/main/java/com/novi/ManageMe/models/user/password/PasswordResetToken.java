package com.novi.ManageMe.models.user.password;

import com.novi.ManageMe.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter // lombok shortcut for adding getters
@Setter // lombok shortcut for adding setters
@Entity // class will be stored in the database
@Table(name = "password_reset_tokens") // define name of table
public class PasswordResetToken {

    @Id // specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // provides the specification of generation strategies for the value of the primary key
    private Long id;

    @Column(nullable = false, unique = true) // defines attribute cannot be null and have to be unique
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)  // creates one-to-one relationship between the PasswordResetToken and User entity, fetched fully when their parent is fetched
    @JoinColumn(nullable = false, name = "user_id") // specifies the column used for joining the entities
    private User user;

    @Column(nullable = false) // defines attribute cannot be null
    private Date expiryDate;

    // set expiry date method
    public void setExpiryDate(int minutes){
        // get a calendar using the current time zone and locale of the system
        Calendar now = Calendar.getInstance();

        // adding minutes to th calendar
        now.add(Calendar.MINUTE, minutes);

        // set expiryDate
        this.expiryDate = now.getTime();
    }

    // check if token is expired method
    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
}
