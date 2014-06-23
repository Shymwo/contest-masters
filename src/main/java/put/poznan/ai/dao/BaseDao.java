package put.poznan.ai.dao;

import java.util.List;

public interface BaseDao<T> {

	public int insert(T object);

	public int update(T object);

	public int delete(T object);

	public List<T> selectAll();

}
