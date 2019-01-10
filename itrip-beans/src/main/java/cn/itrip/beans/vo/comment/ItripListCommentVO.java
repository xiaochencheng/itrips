package cn.itrip.beans.vo.comment;

import java.util.Date;


/**
 * 返回前端-点评列表VO
 *
 */
public class ItripListCommentVO{
	private Long id;//评论id                       commment表
	private String userCode;  //发表评论的用户的姓名  user表
	private Integer hotelLevel;  //酒店的星级       hotel表
	private Date checkInDate;  //入住时间           hotel表
	private String roomTitle;  //房型名称           hotel_room表
	private String tripModeName;//出游类型中文名称    hotel表
	private String content;//评论内容               hotel表
	private Date creationDate;//评论发表时间         hotel表
	private Integer score;//综合评分                hotel表
	private Integer isHavingImg;//是否有评论图片      hotel表

    public ItripListCommentVO(Long id, String userCode, Integer hotelLevel, Date checkInDate, String roomTitle, String tripModeName, String content, Date creationDate, Integer score, Integer isHavingImg) {
		this.id = id;
		this.userCode = userCode;
		this.hotelLevel = hotelLevel;
		this.checkInDate = checkInDate;
		this.roomTitle = roomTitle;
		if (tripModeName.equals("108")){
			this.tripModeName = "商务出差";
		}else if(tripModeName.equals("109")){
			this.tripModeName = "朋友出游";
		}else if(tripModeName.equals("110")){
			this.tripModeName = "情侣出游";
		}else if(tripModeName.equals("111")){
			this.tripModeName = "家庭亲子";
		}else if(tripModeName.equals("112")){
			this.tripModeName = "独自旅行";
		}else if(tripModeName.equals("113")){
			this.tripModeName = "代入预定";
		}else{
			this.tripModeName = "其他";
		}
		this.content = content;
		this.creationDate = creationDate;
		this.score = score;
		this.isHavingImg = isHavingImg;
    }


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTripModeName() {
		return tripModeName;
	}

	public void setTripModeName(String tripModeName) {
		this.tripModeName = tripModeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsHavingImg() {
		return isHavingImg;
	}

	public void setIsHavingImg(Integer isHavingImg) {
		this.isHavingImg = isHavingImg;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getRoomTitle() {
		return roomTitle;
	}
	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}
}
