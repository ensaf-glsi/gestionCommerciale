package ma.ensaf.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ma.ensaf.entity.Client;
import ma.ensaf.support.exceptions.TechnicalException;
import ma.ensaf.support.utils.jdbc.SqlUtils;

public class ClientDao {
	private final static String TABLE_NAME = "client";
	private final static String COLUMN_ID = "id";
	private final static String COLUMN_NOM = "nom";
	private final static String COLUMN_TEL = "tel";
	private final static String COLUMN_EMAIL = "email";
	private final static String COLUMN_ADRESSE = "adresse";
	
	protected Map<String, Object> toMap(Client client) {
		return Map.of(
				COLUMN_NOM, client.getNom(),
				COLUMN_TEL, client.getTel(),
				COLUMN_EMAIL, client.getEmail(),
				COLUMN_ADRESSE, client.getAdresse()
		);
	}
	protected Client convert(ResultSet rs) {
		try {
			return Client.builder().id(rs.getLong(COLUMN_ID))
					.nom(rs.getString(COLUMN_NOM))
					.tel(rs.getString(COLUMN_TEL))
					.email(rs.getString(COLUMN_EMAIL))
					.adresse(rs.getString(COLUMN_ADRESSE))
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	
	public Client create(Client client) {
		Long id = SqlUtils.insert(TABLE_NAME, toMap(client));
		client.setId(id);
		return client;
	}
	public int update(Client client) {
		return SqlUtils.update(TABLE_NAME, client.getId(), toMap(client));
	}
	public void delete(Long id) {
		SqlUtils.delete(TABLE_NAME, id);
	}
	public Client findById(Long id) {
		return SqlUtils.findOne(TABLE_NAME, id, this::convert);
	}
	public List<Client> findAll() {
		return SqlUtils.findAll(TABLE_NAME, this::convert);
	}

	public static void main(String[] args) {
		Client c = Client.builder().adresse("ain chkef")
				.email("email@mail.com").nom("nom1").build();
		Field[] declaredFields = Client.class.getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				field.setAccessible(true);
				System.out.println(field.getName() + ":" + field.get(c));
				field.setAccessible(false);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

