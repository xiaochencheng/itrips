package cn.itrip.dao.common;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HotelCommonMapper {

    public ItripScoreCommentVO getHotelCommon(@Param("hotelId")Integer id)throws Exception;

    public Integer getHotelCommonCount(Map<String,Object> id)throws Exception;

    public List<ItripImage> getCommentImgId(@Param("targetId")Integer commentId)throws Exception;

    public List<ItripComment> getItripCommentByCommentList(ItripComment itripComment) throws Exception;

    public List<ItripLabelDic> getTravelList()throws Exception;

    public Integer insertItripUser(ItripComment itripLabelDic)throws Exception;

    public ItripHotel getItripHotel(@Param("id")Integer hoteild);

}
