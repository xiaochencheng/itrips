package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.service.hotelorder.HotelOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelorder")
public class HotelOrderController {

    private Logger logger = Logger.getLogger(UserInfoController.class);

    @Resource
    private HotelOrderService hotelOrderService;

    /**
     * 查询个人订单房型信息
     * @return
     */
    @ApiOperation(value = "查询个人订单房型信息接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询个人订单房型信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getpersonalorderroominfo/{orderId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripPersonalOrderRoomVO> getUserList(@PathVariable Long orderId, HttpServletRequest requests) {
        System.out.println("queryhotcity>>>>>>>>>>>>>>>>>>");
        ItripPersonalOrderRoomVO vo=new ItripPersonalOrderRoomVO();
        try {
            vo=hotelOrderService.getPersonalOrderRoomInfo(orderId);
            vo.setCheckInWeek(vo.getCheckInWeek());
            vo.setCheckOutWeek(vo.getCheckOutWeek());
            return DtoUtil.returnSuccess("查询个人订单房型信息获取成功！", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }

    /**
     * 预生成订单信息
     * @return
     */
    @ApiOperation(value = "预生成订单信息", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "预生成订单信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getpreorderinfo", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripPersonalOrderRoomVO> getUserListMap(@RequestBody PreAddOrderVO vo, HttpServletRequest requests) {
        System.out.println("queryhotcity>>>>>>>>>>>>>>>>>>");
       ValidateRoomStoreVO va=new ValidateRoomStoreVO();
        try {
             Map map=new HashMap();
             map.put("roomId",vo.getRoomId());
             map.put("hotelId",vo.getHotelId());
             map.put("count",vo.getCount());
             map.put("checkOutDate",vo.getCheckOutDate());
             map.put("checkInDate",vo.getCheckInDate());
             va=hotelOrderService.getPreOrderInfo(map);

            Map param1 = new HashMap();
            param1.put("hotelId", vo.getHotelId());
            param1.put("roomId", vo.getRoomId());
            Integer score = hotelOrderService.getHotelOrderStore(param1);

            return DtoUtil.returnSuccess("预生成订单信息成功！", va);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }
}
