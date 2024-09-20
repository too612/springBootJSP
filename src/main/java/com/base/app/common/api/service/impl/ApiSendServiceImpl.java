package com.base.app.common.api.service.impl;

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
 * @Description : MIS �߼ۿ�
 * @author		: �輺ȣ
 * @since		: 2024.05.27
 * @version		: 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *    ������            ������          ��������
 *  ----------  --------  ---------------------------
 *  2024.05.27   �輺ȣ          ���� ����
 */
@Service("common.api.service.ApiSendService")
public class ApiSendServiceImpl implements ApiSendService{
	
	//private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * API ��û
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@Override
	public String sendApiList(HttpServletRequest param) throws Exception{

		HashMap<String, Object> searchMap = convertMap(param);
		
		// ȣ���ؾ� �ϴ� API �ּ� �ۼ�(�����Ͱ� JSON�������� RETURN ���ִ� URL ������ �ۼ�)
		String serverURL = "http://localhost:8080/";
		String httpType = "http";
		String rUrl = "common/api/ApiReceive/ReceiveApiJSON.do";
		
		Map<String, Object> rSearchMap = searchMap;
		String rJSON = "";
		
		// ȭ������ ��ȸ
		if ("Product/search.do".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			rSearchMap.put("productName", searchMap.get("productName"));
			rSearchMap.put("companyName", searchMap.get("companyName"));
			rSearchMap.put("casNo", searchMap.get("casNo"));
			rSearchMap.put("catNo", searchMap.get("catNo"));
		// �λ����� ��Ű��
		} else if ("USR".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			System.out.println("����");
			rSearchMap.put("SCH_USER_NAME", GeneralUtil.nullToString(String.valueOf(searchMap.get("SCH_USER_NAME"))));
		// �μ����� ��Ű��
		} else if ("DPT".equals(String.valueOf(searchMap.get("SCH_API_TYPE")))) {
			System.out.println("����2");
			rSearchMap.put("SCH_GROUP_NAME", GeneralUtil.nullToString(String.valueOf(searchMap.get("SCH_GROUP_NAME"))));
		}
		else if ("FILE_01".equals(String.valueOf(searchMap.get("PGM_ID")))) {
			rSearchMap.put("PGM_ID", searchMap.get("PGM_ID"));
			
			String filePath = "C:\\Users\\msi\\Desktop\\test.pdf"; // ���� PDF ���� ��η� �������ּ���
			
			Path path = Paths.get(filePath);
			if (Files.notExists(path)) {
				throw new IllegalArgumentException("File is not exists!");
			}
			// try {
			// 	// byte�� ����
			// 	byte[] bytes = Files.readAllBytes(path);
				
			// 	// byte->base64�� ���� �� String ����
			// 	//String s = new String(Base64.encodeBase64(bytes));
			// 	// byte ������ ����
			// 	//rSearchMap.put("FILE_BLOB", s);
				
			// }catch (IOException e) {
			// 	e.printStackTrace();
			// }
		}
		
		rJSON = getHttpCallMethod(serverURL+rUrl, httpType, "POST", rSearchMap); 
		return rJSON;
	}
	
		
	/**
	 * API ȣ��
	 * @param String url : URL
	 * @param String httpType : HTTP Ÿ��(HTTP, HTTPS)
	 * @param String method : ȣ��Ÿ��(GET, POST)
	 * @param Map<String, Object> rSearchMap : �˻�����
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
				conn.setDoOutput(true); //URL ����� �����͸� ��������� ���� ���� ( defualt false )
			} else if ("https".equals(httpType)) {
				conns.setDoInput(true);
				conns.setDoOutput(true); //URL ����� �����͸� ��������� ���� ���� ( defualt false )
			}
			
			try (DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());){
				
				StringBuffer param = new StringBuffer();
				// �˻����ǿ� �ش��ϴ� ������ �����Ѵ�.
				param.append("&searchKey=");	// ���̿�����ũ �Ķ���� ���۽ÿ��� ����
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
	 * httpConnection �����ϱ�
	 * @param String strUrl : URL
	 * @param String method : ȣ��Ÿ��(GET, POST)
	 * @return HttpURLConnection
	 */
	public HttpURLConnection getHttpURLConnection(String strUrl, String method) throws Exception {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(strUrl);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method); //Method ��� ����. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
			conn.setConnectTimeout(5000); //�������� �ð� ����. 5�� �� ����õ�
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * httpsConnection �����ϱ�
	 * @param String strUrl : URL
	 * @param String method : ȣ��Ÿ��(GET, POST)
	 * @return HttpsURLConnection
	 */
	public HttpsURLConnection getHttpsURLConnection(String strUrl, String method) throws Exception {
		URL url;
		HttpsURLConnection conn = null;
		try {
			// SSL API ȣ���Ŀ� ���� SSL ���� ���� �߰�
			// ignoreSSL();						// https�� API��Ž� ������ �߻��� ��쿡 ���Ͽ� �ּ� ���� �� ����
			url = new URL(strUrl);
			
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(method);		//Method ��� ����. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
			conn.setConnectTimeout(5000);		//�������� �ð� ����. 5�� �� ����õ�
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	/**
	 * SSL ���ǿ��� Ȯ��(SSL ������� ����)
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
	 * SSL ���� �׻� ����ϱ�
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
	 * SSL ���� ����� ���� CLASS
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
	 * http�� ȣ��� ����� �ޱ�
	 * @param HttpURLConnection conn : HttpURLConnection
	 * @return String
	 */
	public String getHttpResponse(HttpURLConnection conn) {
		StringBuilder sb = null;

		try {
			if(conn.getResponseCode() == 200) {
				//���������� �����͸� �޾������
				sb = readResopnseData(conn.getInputStream());
			}else{
				//���������� �����͸� ���� ���� ��� 
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
				
				sb = readResopnseData(conn.getErrorStream());
				
				System.out.println("error : " + sb.toString());
				
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect(); //���� ����
		};
		
		if(sb == null) return null;
		
		return sb.toString();
	}
	
	/**
	 * httpS�� ȣ��� ����� �ޱ�
	 * @param HttpSURLConnection conn : HttpURLConnection
	 * @return String
	 */
	public String getHttpsResponse(HttpsURLConnection conns) {
		StringBuilder sb = null;

		try {
			if(conns.getResponseCode() == 200) {
				//���������� �����͸� �޾������
				sb = readResopnseData(conns.getInputStream());
			}else{
				//���������� �����͸� ���� ���� ��� 
				System.out.println(conns.getResponseCode());
				System.out.println(conns.getResponseMessage());
				
				sb = readResopnseData(conns.getErrorStream());
				
				System.out.println("error : " + sb.toString());
				
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conns.disconnect(); //���� ����
		};
		
		if(sb == null) return null;
		
		return sb.toString();
	}
	
	/**
	 * ������ ������ �����ϱ�
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
	 * HttpServletRequest map���� ��ȯ
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
