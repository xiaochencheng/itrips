package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import cn.itrip.dao.hotelorder.HotelorderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HotelOrderServiceImpl implements HotelOrderService{

    @Resource
    private HotelorderMapper hotelorderMapper;


    @Override
    public ItripPersonalOrderRoomVO getPersonalOrderRoomInfo(@Param("orderId") Long orderId) {
        return hotelorderMapper.getPersonalOrderRoomInfo(orderId);
    }

    @Override
    public ValidateRoomStoreVO getPreOrderInfo(Map<String, Object> param) throws Exception {
        return hotelorderMapper.getPreOrderInfo(param);
    }

    @Override
    public Integer getHotelOrderStore(Map<String, Object> param) throws Exception {
        return hotelorderMapper.getHotelOrderStore(param);
    }
}
