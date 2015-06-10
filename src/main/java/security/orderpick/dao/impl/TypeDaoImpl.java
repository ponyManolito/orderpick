package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import security.orderpick.dao.TypeDaoI;
import security.orderpick.datamodel.Type;
import security.orderpick.mapper.TypeMapper;

@Component(TypeDaoImpl.name)
public class TypeDaoImpl implements TypeDaoI {

	public static final String name = "typeDaoImpl";

	@Resource(name = TypeMapper.name)
	private TypeMapper typeMapper;

	@Override
	public List<Type> getAll() {
		return typeMapper.getAll();
	}

	@Override
	public Type getType(int id) {
		return typeMapper.getType(id);
	}

	@Override
	public int addType(Type type) {
		return type.isNewType() ? typeMapper.addType(type) : typeMapper.updateType(type);
	}

	@Override
	public int updateType(Type type) {
		return typeMapper.updateType(type);
	}

	@Override
	public int deleteType(int id) {
		return typeMapper.deleteType(id);
	}

	@Override
	public boolean assignType(String name, String description) throws Exception {
		Type type = typeMapper.getTypeByName(name);
		if (type != null) {
			type = typeMapper.getTypeIfAvailable(name);
			if (type != null) {
				description = description.replace("+", " ");
				type.setDescription(description);
				type.setAvailable(false);
				typeMapper.updateType(type);
				return true;
			} else {
				// Type not available
				throw new Exception("Mesa ya asignada");
			}
		} else {
			// Type not found
			throw new Exception("Numero de mesa no existente");
		}
	}

	@Override
	public boolean unassignType(String name) {
		Type type = typeMapper.getTypeByName(name);
		if (type != null) {
			type.setDescription("");
			type.setAvailable(true);
			typeMapper.updateType(type);
			return true;
		}
		return false;
	}
}
