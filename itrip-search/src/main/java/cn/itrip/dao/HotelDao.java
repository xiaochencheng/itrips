package cn.itrip.dao;

import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.Page;
import cn.itrip.entity.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public interface HotelDao {

    //,String flword,String fpword
    public List<ItripHotelVO> queryHotelList(Integer cityId, Integer count);

    public Page searchHotelPage(SolrQuery solrQuery, Integer sart, Integer end)throws Exception;
}
