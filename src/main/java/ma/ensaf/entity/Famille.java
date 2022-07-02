package ma.ensaf.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ma.ensaf.support.EntityId;
import ma.ensaf.support.dao.annotation.Column;
import ma.ensaf.support.dao.annotation.Table;

@SuperBuilder
@RequiredArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
@Table("familles")
public class Famille extends EntityId<Long> {
	
	@Column(name = "libelle")
	private String name;

}
