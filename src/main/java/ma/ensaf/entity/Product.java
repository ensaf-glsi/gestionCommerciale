package ma.ensaf.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ma.ensaf.support.EntityId;
import ma.ensaf.support.dao.annotation.Column;
import ma.ensaf.support.dao.annotation.Table;
import ma.ensaf.support.dao.annotation.Transient;

@SuperBuilder
@RequiredArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
// table PRODUCTS
@Table("produit")
public class Product extends EntityId<Long> {
	
	@Column(name = "libelle")
	private String name;
	@Column(name = "pu")
	private BigDecimal price;
	private String unit;
	
	@Transient
	// champ calculé
	private BigDecimal stock;

}
