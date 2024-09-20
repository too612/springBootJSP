package com.base.app.main.service;

import java.util.HashMap;
import java.util.List;

import com.base.app.main.data.dto.MainDTO;

public interface MainService {

	public int maxNum() throws Exception;

	public void insertData(MainDTO dto) throws Exception;

	public int getDataCount(String searchKey, String searchValue) throws Exception;

	public List<MainDTO> getLists(int start, int end,
			String searchKey, String searchValue) throws Exception;

	public MainDTO getReadData(int num) throws Exception;

	public void updateHitCount(int num) throws Exception;

	public void updateData(MainDTO dto) throws Exception;

	public void deleteData(int num) throws Exception;

	public String api(HashMap<String, Object> map) throws Exception;

}
