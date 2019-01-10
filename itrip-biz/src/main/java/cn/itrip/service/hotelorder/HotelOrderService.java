package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HotelOrderService {

    public ItripPersonalOrderRoomVO getPersonalOrderRoomInfo(@Param("orderId")Long orderId);

    public ValidateRoomStoreVO getPreOrderInfo(Map<String, Object> param) throws Exception;

    public  Integer getHotelOrderStore(Map<String, Object> param) throws Exception;


}
