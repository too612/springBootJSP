package com.base.app.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.app.main.dto.MainDTO;
import com.base.app.main.service.MainService;
import com.base.app.main.mapper.MainMapper;

//MainService 인터페이스를 구현한 클래스
@Service // 객체 생성
public class MainServiceImpl implements MainService {

	@Autowired // MainMapper에 있는 sql문을 MainServiceImpl로 읽어와서 의존성주입하여 객체생성 한것.
	private MainMapper mainMapper; // MainMapper 의존성 주입

	// 경로 : MainController -> MainService(I) -> MainServiceImpl(C) ->
	// MainMapper(I) -> MainMapper.xml

	@Override
	public int maxNum() throws Exception {
		return mainMapper.maxNum();
	}

	@Override
	public void insertData(MainDTO dto) throws Exception {
		mainMapper.insertData(dto);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return mainMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<MainDTO> getLists(int start, int end, String searchKey, String searchValue) throws Exception {
		return mainMapper.getLists(start, end, searchKey, searchValue);
	}

	@Override
	public MainDTO getReadData(int num) throws Exception {
		return mainMapper.getReadData(num);
	}

	@Override
	public void updateHitCount(int num) throws Exception {
		mainMapper.updateHitCount(num);
	}

	@Override
	public void updateData(MainDTO dto) throws Exception {
		mainMapper.updateData(dto);
	}

	@Override
	public void deleteData(int num) throws Exception {
		mainMapper.deleteData(num);
	}

}
