package ma.ensaf.dao;

import lombok.Getter;
import ma.ensaf.entity.Famille;
import ma.ensaf.support.dao.GenericDao;

public class FamilleDao extends GenericDao<Famille> {
	@Getter
	private static final FamilleDao instance = new FamilleDao();
	private FamilleDao() {}

}
