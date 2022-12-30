package com.chagok.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chagok.domain.AbookVO;
import com.chagok.domain.ReportVO;

@Repository
public class ReportDAOImpl implements ReportDAO {

	private static final Logger mylog = LoggerFactory.getLogger(ReportDAOImpl.class);

	// DB연결
	@Inject
	private SqlSession sqlSession;
	
	// mapper의 NAMESPACE
	private static final String NAMESPACE = "com.chagok.mapper.reportMapper";
	
	@Override
	public List<AbookVO> rptTest(Integer mno) {
		mylog.debug("rptTest(mno) 호출");
		return sqlSession.selectList(NAMESPACE+".rptTest", mno);
	}
	
	@Override
	public List<ReportVO> getCateCnt(Integer mno) throws Exception {
		mylog.debug("getCateCnt() 호출");
		List<ReportVO> cateCntList = sqlSession.selectList(NAMESPACE+".getCateCnt", mno);
		mylog.debug("cateCntList : "+cateCntList.size());
		return cateCntList;
	}


	
	
	
}
