package yu.mapper;

import org.apache.ibatis.annotations.Mapper;
import yu.model.Order;

@Mapper
public interface OrderMapper {

	void insert(Order order);
}
