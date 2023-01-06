package com.chagok.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chagok.domain.AbookVO;
import com.chagok.domain.CategoryVO;

public interface AbookDAO {
	
	// 가계부 목록
	public List<AbookVO> getAbookList(int mno) throws Exception;
	
	// 카테고리 목록 
	public List<CategoryVO> CateList() throws Exception;
	
	// 그리드 수정
	public Integer updateAbook(AbookVO vo) throws Exception;
	
    public List<Map<String, AbookVO>> AbookList() throws Exception;
	
	public List<AbookVO> setAbookList(AbookVO vo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	///////////////////MJ/////////////////////
	// ct_top
	public List<String> getctTop() throws Exception;
	
	// 1. 예산 유무
	public int chkBud(int mno, String pMonth) throws Exception;
	
	// 2. 예산 조회
	public List<Map<String, Object>> getBud(int mno, String pMonth) throws Exception;

	// 3. 예산 설정
	public void setBud(Map<String, Object> insertMap);
	
	
}
