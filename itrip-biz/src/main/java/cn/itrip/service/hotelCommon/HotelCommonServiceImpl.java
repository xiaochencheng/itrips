package cn.itrip.service.hotelCommon;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.dao.common.HotelCommonMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HotelCommonServiceImpl implements HotelCommonService {
    @Resource
   private HotelCommonMapper hotelCommon;

    @Override
    public ItripScoreCommentVO getHotelCommon(@Param("hotelId") Integer id) throws Exception {
        return hotelCommon.getHotelCommon(id);
    }

    @Override
    public Integer getHotelCommonCount(Map<String,Object> id) throws Exception {
        return hotelCommon.getHotelCommonCount(id);
    }

    @Override
    public List<ItripImage> getCommentImgId(Integer commentId) throws Exception {
        return hotelCommon.getCommentImgId(commentId);
    }

    @Override
    public PageInfo getItripCommentByCommentList(ItripSearchCommentVO itripSearchCommentVO) throws Exception {
        System.out.println("00000>>>"+itripSearchCommentVO.getPageSize()+">>>"+itripSearchCommentVO.getPageNo());
        PageHelper.startPage(itripSearchCommentVO.getPageNo(),itripSearchCommentVO.getPageSize());
        ItripComment itripComment  = new ItripComment();
        itripComment.setHotelId(Math.abs(itripSearchCommentVO.getHotelId()));
        itripComment.setIsOk(Math.abs(itripSearchCommentVO.getIsOk()));
        itripComment.setIsHavingImg(Math.abs(itripSearchCommentVO.getIsHavingImg()));
        System.out.println("绝对值："+itripSearchCommentVO.getIsOk()+">>>>>"+itripSearchCommentVO.getIsHavingImg());
        List<ItripComment> itripComments = hotelCommon.getItripCommentByCommentList(itripComment);
        System.out.println("页面提供参数》》》》"+itripComments);
        PageInfo pageInfo = new PageInfo(itripComments);
        System.out.println("页面》》》》"+pageInfo);
        return pageInfo;
    }

    @Override
    public List<ItripLabelDic> getTravelList() throws Exception {
        return hotelCommon.getTravelList();
    }

    @Override
    public Integer insertItripUser(ItripComment itripLabelDic) throws Exception {
        return hotelCommon.insertItripUser(itripLabelDic);
    }

    @Override
    public List<ItripImage> getImageSrc(Integer roomId) throws Exception {
        return hotelCommon.getCommentImgId(roomId);
    }

    @Override
    public ItripHotel getItripHotel(Integer hoteild) {
        return hotelCommon.getItripHotel(hoteild);
    }


}
