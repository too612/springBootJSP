package com.base.app.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.base.app.main.dto.MainDTO;

@Mapper // Mapper로 등록 시킨다.
public interface MainMapper {

	public int maxNum() throws Exception;

	public void insertData(MainDTO dto) throws Exception;

	public int getDataCount(String searchKey, String searchValue) throws Exception;

	public List<MainDTO> getLists(int start, int end,
			String searchKey, String searchValue) throws Exception;

	public MainDTO getReadData(int num) throws Exception;

	public void updateHitCount(int num) throws Exception;

	public void updateData(MainDTO dto) throws Exception;

	public void deleteData(int num) throws Exception;

}
