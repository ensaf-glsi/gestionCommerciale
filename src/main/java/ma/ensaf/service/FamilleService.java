package ma.ensaf.service;

import lombok.Getter;
import ma.ensaf.entity.Famille;
import ma.ensaf.support.service.GenericService;

public class FamilleService extends GenericService<Famille> {
	@Getter
	private static final FamilleService instance = new FamilleService();
	private FamilleService() {
		super();
	}

}
