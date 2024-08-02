package utils;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class Md5Utils {
	
	public String md5_making(String upass) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(upass.getBytes());
			for(byte bt : md.digest()) {
				sb.append(String.format("%x", bt));
			}
			
		}catch(Exception e) {
			sb.append("인자값 오류 발생으로 생성이 되지 않음");
		}
		
		return sb.toString();
	}
}
