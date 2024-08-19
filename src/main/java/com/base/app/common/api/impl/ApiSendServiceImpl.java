package com.base.app.common.api.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.base.app.common.api.service.ApiSendService;
import com.base.app.common.util.GeneralUtil;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiSendServiceImpl.java
 * @Description : MIS 발송용
 * @author		: 김성호
 * @since		: 2024.05.27
 * @version		: 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *    수정일            수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2024.05.27   김성호          최초 생성
 */
@Service("common.api.service.ApiSendService")
public class ApiSendServiceImpl implements ApiSendService{
	
	//private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * API 요청
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@Override
	public String sendApiList(HttpServletRequest param) throws Exception{

		HashMap<String, Object> searchMap = convertMap(param);
		
		// 호출해야 하는 API 주소 작성(데이터가 JSON형식으로 RETURN 해주는 URL 정보를 작성)
		String serverURL = "http://localhost:8080/";
		String httpType = "http";
		String rUrl = "common/api/ApiReceive/ReceiveApiJSON.do";
		
		Map<String, Object> rSearchMap = searchMap;
		String rJSON = "";
		
		// 화학정보 조회
		if ("Product/search.do".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			rSearchMap.put("productName", searchMap.get("productName"));
			rSearchMap.put("companyName", searchMap.get("companyName"));
			rSearchMap.put("casNo", searchMap.get("casNo"));
			rSearchMap.put("catNo", searchMap.get("catNo"));
		// 인사정보 스키마
		} else if ("USR".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			System.out.println("진입");
			rSearchMap.put("SCH_USER_NAME", GeneralUtil.nullToString(String.valueOf(searchMap.get("SCH_USER_NAME"))));
		// 부서정보 스키마
		} else if ("DPT".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			System.out.println("진입2");
			rSearchMap.put("SCH_GROUP_NAME", GeneralUtil.nullToString(String.valueOf(searchMap.get("SCH_GROUP_NAME"))));
		}
		else if ("FILE_01".equals(String.valueOf(searchMap.get("PGM_ID")))) {
			rSearchMap.put("PGM_ID", searchMap.get("PGM_ID"));
			
			String filePath = "C:\\Users\\msi\\Desktop\\test.pdf"; // 실제 PDF 파일 경로로 변경해주세요
			
			Path path = Paths.get(filePath);
			if (Files.notExists(path)) {
				throw new IllegalArgumentException("File is not exists!");
			}
			// try {
			// 	// byte로 변경
			// 	byte[] bytes = Files.readAllBytes(path);
				
			// 	// byte->base64로 변경 후 String 저장
			// 	//String s = new String(Base64.encodeBase64(bytes));
			// 	// byte 데이터 세팅
			// 	//rSearchMap.put("FILE_BLOB", s);
				
			// }catch (IOException e) {
			// 	e.printStackTrace();
			// }
		}
		
		rJSON = getHttpCallMethod(serverURL+rUrl, httpType, "POST", rSearchMap); 
		return rJSON;
	}
	
		
	/**
	 * API 호출
	 * @param String url : URL
	 * @param String httpType : HTTP 타입(HTTP, HTTPS)
	 * @param String method : 호출타입(GET, POST)
	 * @param Map<String, Object> rSearchMap : 검색조건
	 * @return String
	 */
	public String getHttpCallMethod(String url, String httpType, String method, Map<String, Object> rSearchMap) throws Exception {
		
		String result = "";
		HttpURLConnection conn = null;
		HttpsURLConnection conns = null;
		
		if ("http".equals(httpType)) {
			conn = getHttpURLConnection(url, method);
		} else if ("https".equals(httpType)) {
			conns = getHttpsURLConnection(url, method);
		}
		

		if("GET".equalsIgnoreCase(method)){
			if ("http".equals(httpType)) {
				result = getHttpResponse(conn);
			} else if ("https".equals(httpType)) {
				result = getHttpsResponse(conns);
			}
			
		}else if("POST".equalsIgnoreCase(method)) {
			
			if ("http".equals(httpType)) {
				conn.setDoInput(true);
				conn.setDoOutput(true); //URL 연결시 데이터를 사용할지에 대한 설정 ( defualt false )
			} else if ("https".equals(httpType)) {
				conns.setDoInput(true);
				conns.setDoOutput(true); //URL 연결시 데이터를 사용할지에 대한 설정 ( defualt false )
			}
			
			try (DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());){
				
				StringBuffer param = new StringBuffer();
				// 검색조건에 해당하는 정보를 세팅한다.
				param.append("&searchKey=");	// 아이엠지테크 파라미터 전송시에는 해제
				JSONObject jsonObject = new JSONObject(rSearchMap);
				param.append(URLEncoder.encode(jsonObject.toString(), "utf-8"));
				
				dataOutputStream.writeBytes(param.toString());
				dataOutputStream.flush();
				
				if ("http".equals(httpType)) {
					result = getHttpResponse(conn);
				} else if ("https".equals(httpType)) {
					result = getHttpsResponse(conns);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	/**
	 * httpConnection 세팅하기
	 * @param String strUrl : URL
	 * @param String method : 호출타입(GET, POST)
	 * @return HttpURLConnection
	 */
	public HttpURLConnection getHttpURLConnection(String strUrl, String method) throws Exception {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(strUrl);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method); //Method 방식 설정. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
			conn.setConnectTimeout(5000); //연결제한 시간 설정. 5초 간 연결시도
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * httpsConnection 세팅하기
	 * @param String strUrl : URL
	 * @param String method : 호출타입(GET, POST)
	 * @return HttpsURLConnection
	 */
	public HttpsURLConnection getHttpsURLConnection(String strUrl, String method) throws Exception {
		URL url;
		HttpsURLConnection conn = null;
		try {
			// SSL API 호출방식에 따른 SSL 무시 로직 추가
			// ignoreSSL();						// https로 API통신시 문제가 발생할 경우에 한하여 주석 해제 후 진행
			url = new URL(strUrl);
			
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(method);		//Method 방식 설정. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
			conn.setConnectTimeout(5000);		//연결제한 시간 설정. 5초 간 연결시도
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	/**
	 * SSL 세션여부 확인(SSL 통과로직 시작)
	 */
	public static void ignoreSSL() throws Exception{
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session)  {
				return true;
			}
		};
		trustAllHttpsCertificates();
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}

	/**
	 * SSL 인증 항상 허용하기
	 */
	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
	
	/**
	 * SSL 인증 허용을 위한 CLASS
	 */
	static class miTM implements TrustManager,X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[]{};
		}
	
		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}
	
		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
	
		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
	}
	
	/**
	 * http로 호출된 결과값 받기
	 * @param HttpURLConnection conn : HttpURLConnection
	 * @return String
	 */
	public String getHttpResponse(HttpURLConnection conn) {
		StringBuilder sb = null;

		try {
			if(conn.getResponseCode() == 200) {
				//정상적으로 데이터를 받았을경우
				sb = readResopnseData(conn.getInputStream());
			}else{
				//정상적으로 데이터를 받지 못한 경우 
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
				
				sb = readResopnseData(conn.getErrorStream());
				
				System.out.println("error : " + sb.toString());
				
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect(); //연결 해제
		};
		
		if(sb == null) return null;
		
		return sb.toString();
	}
	
	/**
	 * httpS로 호출된 결과값 받기
	 * @param HttpSURLConnection conn : HttpURLConnection
	 * @return String
	 */
	public String getHttpsResponse(HttpsURLConnection conns) {
		StringBuilder sb = null;

		try {
			if(conns.getResponseCode() == 200) {
				//정상적으로 데이터를 받았을경우
				sb = readResopnseData(conns.getInputStream());
			}else{
				//정상적으로 데이터를 받지 못한 경우 
				System.out.println(conns.getResponseCode());
				System.out.println(conns.getResponseMessage());
				
				sb = readResopnseData(conns.getErrorStream());
				
				System.out.println("error : " + sb.toString());
				
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conns.disconnect(); //연결 해제
		};
		
		if(sb == null) return null;
		
		return sb.toString();
	}
	
	/**
	 * 응답결과 데이터 세팅하기
	 * @param InputStream in : InputStream
	 * @return StringBuilder
	 */
	public StringBuilder readResopnseData(InputStream in) {
		
		if(in == null ) return null;

		StringBuilder sb = new StringBuilder();

		String line = "";
		
		try (InputStreamReader ir = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(ir)){
			while( (line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb;
	}
	
	/**
	 * HttpServletRequest map으로 변환
	 * @param InputStream in : InputStream
	 * @return hmap
	 */
	public HashMap<String, Object> convertMap(HttpServletRequest request) {
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		String key;
		Enumeration<?> enumList = request.getParameterNames();

		while (enumList.hasMoreElements()) {
			key = (String) enumList.nextElement();
			if (request.getParameterValues(key).length > 1) {
				hmap.put(key, request.getParameterValues(key));
			} else {
				hmap.put(key, request.getParameter(key));
			}
		}
		return hmap;
	}
	
}
