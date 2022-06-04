package ma.ensaf.support.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ma.ensaf.entity.ProductCustomer;
import ma.ensaf.support.exceptions.TechnicalException;

public class ReflectUtils {

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	public static Object getValue(String fieldName, Object object) {
		return getValue(getField(object.getClass(), fieldName), object);
	}
	
	public static Object getValue(Field field, Object object) {
		try {
			field.setAccessible(true);
			Object value = field.get(object);
			return value;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		} finally {
			field.setAccessible(false);
		}
	}

	public static void setValue(Object object, Field field, Object fieldValue) {
		try {
			field.setAccessible(true);
			field.set(object, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		} finally {
			if (field != null) {
				field.setAccessible(false);
			}
		}
	}
	public static void setValue(Object object, String fieldName, Object fieldValue) {
		setValue(object, getField(object.getClass(), fieldName), fieldValue);
	}
	public static Field getField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			if (Objects.equals(clazz.getSuperclass(), Object.class)) {
				e.printStackTrace();
				throw new TechnicalException("Erreur interne, merci de contacter l'admin");				
			}
			return getField(clazz.getSuperclass(), fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	
	public static List<Field> getAllDeclaredFields(Class<?> clazz) {
		if (Objects.equals(clazz.getSuperclass(), Object.class)) {
			return Arrays.asList(clazz.getDeclaredFields());
		}
		List<Field> result = new ArrayList<>(getAllDeclaredFields(clazz.getSuperclass()));
		List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
		result.addAll(fields);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(Object.class.getSuperclass());
		
		System.out.println(getField(ProductCustomer.class, "customer"));
		System.out.println(getField(ProductCustomer.class, "name"));
		System.out.println(getField(ProductCustomer.class, "id"));
		System.out.println(getField(ProductCustomer.class, "other")); // nullpointerexception
		//1 ProductCustomer
		// result = [id, name, price, unit]
		// fields = [customer]
		// result = [id, name, price, unit, customer]
		
		//2 Product
		// result = [id]
		// fields = [name, price, unit]
		// result = [id, name, price, unit]
		
		//3 EntityId
		// result = []
		// fields = [id]
		// result = [id]
		
		//4 Object
		//[]
	}
}
