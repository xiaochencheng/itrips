package cn.itrip.service.hotelCommon;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HotelCommonService {

    public ItripScoreCommentVO getHotelCommon(@Param("hotelId")Integer id)throws Exception;

    public Integer getHotelCommonCount(Map<String,Object> id)throws Exception;

    public List<ItripImage> getCommentImgId(@Param("targetId")Integer commentId)throws Exception;

    public PageInfo getItripCommentByCommentList(ItripSearchCommentVO itripSearchCommentVO) throws Exception;

    public List<ItripLabelDic> getTravelList()throws Exception;

    public Integer insertItripUser(ItripComment itripLabelDic)throws Exception;

    public List<ItripImage>  getImageSrc(@Param("targetId") Integer roomId)throws Exception;

    public ItripHotel getItripHotel(@Param("id")Integer hoteild);


}
