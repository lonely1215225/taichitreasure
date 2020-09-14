package edu.hunau.crowd.service.api;


import edu.hunau.crowd.entity.vo.AddressVO;
import edu.hunau.crowd.entity.vo.OrderProjectVO;

import java.util.List;

public interface OrderService {

	OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

	List<AddressVO> getAddressVOList(Integer memberId);

	void saveAddress(AddressVO addressVO);

}
