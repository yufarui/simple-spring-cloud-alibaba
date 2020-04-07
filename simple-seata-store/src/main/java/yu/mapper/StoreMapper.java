package yu.mapper;

import org.apache.ibatis.annotations.Mapper;
import yu.model.Order;

@Mapper
public interface StoreMapper {

	void update(Order order);
}
