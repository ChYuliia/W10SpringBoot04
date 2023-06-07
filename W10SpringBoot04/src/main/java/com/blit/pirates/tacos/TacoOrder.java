package com.blit.pirates.tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.Date;


@Data
@Entity
public class TacoOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date placedAt = new Date();

	@NotBlank(message="Delivery name is required")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	
	@NotBlank(message="City is required")
	private String deliveryCity;
	
	@NotBlank(message="State is required")
	private String deliveryState;
	
	@NotBlank(message="Zip is required")
	private String deliveryZip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	// 0[1-9] 	month 2 digits must be 01, 02, 03, 04,05,06,07,08,09
	// | 		OR
	// 1[0-2] 	month 2 digits must b 10, 11, 12
	// [\\/]	/
	// [2-9]	year 1st digit must be 2 3 4 5 6 7 8 9 
	// [0-9]	year 2nd digit must be 0 1 2 3 4 5 6 7 8 9 
	// so date range is 01/20 to 12/99
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
			message="must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		tacos.add(taco);
	}
	
	// no getters, no setters or constructors or toString
	// no hashCode or equals
	// BECAUSE @Data annotation from lombok will do it 
	// behind the scenes
	
}
