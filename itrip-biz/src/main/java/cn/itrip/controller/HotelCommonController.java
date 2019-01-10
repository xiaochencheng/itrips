package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.*;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.hotelCommon.HotelCommonService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/comment")
public class HotelCommonController {

    private Logger logger = Logger.getLogger(UserInfoController.class);

    @Resource
    private HotelCommonService hotelCommonService;
    @Resource
    private ValidationToken validationToken;

    /**
     * 查询酒店评分
     * @param request
     * @return
     */
    @ApiOperation(value = "查询酒店评分接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class)
    @RequestMapping(value = "/gethotelscore/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> getUserList(@PathVariable Integer cityId, HttpServletRequest request) {
        System.out.println("queryhotcity>>>>>>>>>>>>>>>>>>");
        try {
            ItripScoreCommentVO list =hotelCommonService.getHotelCommon(cityId);
            System.out.println(list+">>>>>>>");

            System.out.println(list.getAvgFacilitiesScore()+">>>>>>>>>>"+list.getAvgHygieneScore()+">>"+list.getAvgPositionScore()+">>>"+list.getAvgScore()+">>>"+list.getAvgServiceScore());
            return DtoUtil.returnSuccess("查询酒店评分获取成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }


    /**
     * 查询评论数量
     * @param request
     * @return
     */
    @ApiOperation(value = "查询评论数量接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class)
    @RequestMapping(value = "/getcount/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto getHotelCommentCount(@PathVariable Integer cityId, HttpServletRequest request) {
        System.out.println("queryhotcity>>>>>>>>>>>>>>>>>>");
        ItripScoreCountVo count=new ItripScoreCountVo();
        try {
            Map map=new HashMap();
            map.put("hotelId",cityId);
            count.setAllcomment(hotelCommonService.getHotelCommonCount(map));
            map.put("isOk",0);
            count.setImprove(hotelCommonService.getHotelCommonCount(map));
            map.put("isOk",1);
            count.setIsok(hotelCommonService.getHotelCommonCount(map));
            map.remove("isok");

            map.put("isHavingImg","1");
            count.setHavingimg(hotelCommonService.getHotelCommonCount(map));
//            map.put("content",cityId);
//            list=hotelCommonService.getHotelCommonCount(map);
//            count.setAllcomment(list);
            return DtoUtil.returnSuccess("查询评论数量获取成功", count);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }


    /**
     * 查询评论附带的图片
     * @param request
     * @return
     */
    @ApiOperation(value = "查询评论附带的图片接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class)
    @RequestMapping(value = "/getimg/{commentId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getItripImgId(@PathVariable Integer commentId, HttpServletRequest request) {
        System.out.println("/getimg/{commentId}>>>>>>>>>>>>>>>>>>");
        List<ItripImageVO> imgage=new ArrayList<>();
        try {
            List<ItripImage> list =hotelCommonService.getCommentImgId(commentId);
            System.out.println(list+">>>>>>>");
            if (EmptyUtils.isNotEmpty(list)) {
                imgage = list.stream()
                        .map(e -> new ItripImageVO(e.getPosition(),e.getImgUrl()))
                        .collect(Collectors.toList());
        }else{
            DtoUtil.returnFail("cityId不能为空", "10201");
        }
            System.out.println(list+">>>>>>>>>>");
            return DtoUtil.returnSuccess("查询评论附带的图片成功", imgage);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }


    /**
     * 获取评论内容列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getcommentlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripListCommentVO>> getCommentList(@RequestBody ItripSearchCommentVO itripSearchCommentVO, HttpServletRequest request) {
        List<ItripListCommentVO> itripListCommentVOS = new ArrayList<>();
        List<ItripComment> itripComments = new ArrayList<>();
        System.out.println("VO>>"+itripSearchCommentVO.getIsHavingImg()+">>"+itripSearchCommentVO.getIsOk());
        Page<ItripListCommentVO> page = new Page<>();
        try {
            PageInfo pageInfo = hotelCommonService.getItripCommentByCommentList(itripSearchCommentVO);
            System.out.println("pageInfo>>>>>>"+pageInfo);
            itripComments = pageInfo.getList();
            System.out.println("itripComments>>>>"+itripComments);
            if (EmptyUtils.isNotEmpty(itripComments)) {
                itripListCommentVOS = itripComments.stream()
                        .map(e -> new ItripListCommentVO(e.getId(),null,null,new Date(),null,e.getTripMode(),e.getContent(),e.getCreationDate(),e.getScore(),e.getIsHavingImg()))
                        .collect(Collectors.toList());
            }
            page.setBeginPos(pageInfo.getNavigateFirstPage());
            page.setCurPage(pageInfo.getPageNum());
            page.setTotal((int)pageInfo.getTotal());
            page.setPageSize(itripSearchCommentVO.getPageSize());
            page.setPageCount(pageInfo.getSize());
            System.out.println("page页数》》》》"+page.getBeginPos()+">>>>"+page.getCurPage()+">>>"+page.getPageSize()+">>>>"+page.getRows()+">>>>"+page.getPageCount()+">>>"+page.getTotal());
            for (ItripListCommentVO itripListCommentVO : itripListCommentVOS) {
                logger.info(itripListCommentVO.getContent());
            }
            System.out.println("itripListCommentVOS>>>"+itripListCommentVOS);
            page.setRows(itripListCommentVOS);
            System.out.println(">>>"+page);

        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取评论内容列表成功",page);
    }


    /**
     * 查询旅游类型列表
     * @return
     */
    @ApiOperation(value = "查询旅游类型列表接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class)
    @RequestMapping(value = "/gettraveltype", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> getTravelList() {
        System.out.println("查询旅游类型列表接口>>>");
        List<ItripLabelDicVO> imgage=new ArrayList<>();
        try {
            List<ItripLabelDic> list =hotelCommonService.getTravelList();
            System.out.println(list+">>>>>>>");
            if (EmptyUtils.isNotEmpty(list)) {
                imgage = list.stream()
                        .map(e -> new ItripLabelDicVO(e.getDescription(),e.getId(),e.getName(),e.getPic()))
                        .collect(Collectors.toList());
            }else{
                DtoUtil.returnFail("城市列表读取错误", "10201");
            }
            System.out.println(list+">>>>>>>>>>");
            return DtoUtil.returnSuccess("查询旅游类型列表成功", imgage);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }


    /**
     * 新增评论
     * @param vo
     * @param request
     * @return
     */
    @ApiOperation(value = "新增评论接口",httpMethod = "POST",produces = "application/json",protocols = "HTTP",
            response = Dto.class,notes = "新增评论")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripLabelDicVO> addLinkUser(@RequestBody ItripAddCommentVO vo, HttpServletRequest request){
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        logger.info("linkUserName>>>>>>>>>" + vo.getTripMode());
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        System.out.println("currentUser>>>>>>>>>>"+currentUser);
        ItripComment linkUser=new ItripComment();
        linkUser.setHotelId(vo.getHotelId());
        linkUser.setOrderId(vo.getOrderId());
        linkUser.setProductId(vo.getProductId());
        linkUser.setProductType(vo.getProductType());
        linkUser.setIsHavingImg(vo.getIsHavingImg());
        linkUser.setUserId(currentUser.getId());
        vo.getItripImages();
        linkUser.setPositionScore(vo.getPositionScore());
        linkUser.setFacilitiesScore(vo.getFacilitiesScore());
        linkUser.setHygieneScore(vo.getHygieneScore());
        linkUser.setServiceScore(vo.getServiceScore());
        linkUser.setIsOk(vo.getIsOk());
        linkUser.setContent(vo.getContent());
        linkUser.setTripMode(vo.getTripMode());
        linkUser.setCreationDate(new Date());
        try {
            Integer num=hotelCommonService.insertItripUser(linkUser);
            if(num>0){
                System.out.println("添加成功！");
                return  DtoUtil.returnSuccess("添加成功！",num);
            }else {
                return DtoUtil.returnFail("添加失败","100000");
            }
        }catch (Exception e){
            e.printStackTrace();
            return DtoUtil.returnFail("新增评论信息失败", "100401");
        }
    }


    /**
     * 查询酒店详情（评论）
     * @param request
     * @return
     */
    @ApiOperation(value = "查询酒店详情（评论）接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class)
    @RequestMapping(value = "/gethoteldesc/{orderId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotel> getItripHotel(@PathVariable Integer orderId, HttpServletRequest request) {
        System.out.println("queryhotcity>>>>>>>>>>>>>>>>>>");
        ItripHotelDescVO vo=new ItripHotelDescVO();
        try {
            ItripHotel list =hotelCommonService.getItripHotel(orderId);
            System.out.println(list+">>>>>>>");
            vo.setHotelId((long)list.getId());
            vo.setHotelLevel(list.getHotelLevel());
            vo.setHotelName(list.getHotelName());
             return DtoUtil.returnSuccess("查询酒店详情（评论）成功", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常", "100513");
        }
    }



}


