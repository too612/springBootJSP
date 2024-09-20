package com.base.app.main.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.app.main.data.dto.MainDTO;
import com.base.app.main.data.mapper.MainMapper;
import com.base.app.main.service.MainService;

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

	@Override
	public String api(HashMap<String, Object> map) throws Exception {
		
		String result = "";
		if ("api".equals(map.get("title").toString())) {
			//http://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=10&serviceKey=SFJXw8594jTUvCqXeYftokgCI0Bz8gS3%2FdAYN7gnv0atIE%2BJJwZyqQZczX2z6rSGhGsgHeMO7bfc5Oe6v2MN5Q%3D%3D
			StringBuilder urlBuilder = new StringBuilder("http://api.odcloud.kr/api/15077586/v1/centers"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=SFJXw8594jTUvCqXeYftokgCI0Bz8gS3%2FdAYN7gnv0atIE%2BJJwZyqQZczX2z6rSGhGsgHeMO7bfc5Oe6v2MN5Q%3D%3D"); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*YYYYMMDD*/
			urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 목록 수*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			//System.out.println(sb.toString());

			result = sb.toString();

			// 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
			JSONParser parser = new JSONParser();
			// 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
			JSONObject obj = (JSONObject)parser.parse(result);
/*
			JSONObject responseResult = (JSONObject)obj.get("data");
			JSONObject bodyResult = (JSONObject)responseResult.get("data");
			JSONObject itemsResult = (JSONObject)bodyResult.get("items");
			JSONArray itemResult = (JSONArray) itemsResult.get("item");
//            JSONObject itemResult = (JSONObject)itemsResult.get("item");

			for (Object item : itemResult) {
				System.out.println(item);
			}*/
		}

			
		return result;
	}

	}
