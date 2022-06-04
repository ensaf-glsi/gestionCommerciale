package ma.ensaf.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class EntityId<ID> {
	
	private ID id;

}
