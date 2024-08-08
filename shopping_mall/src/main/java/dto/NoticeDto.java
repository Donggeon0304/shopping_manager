package dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {
	private int nidx, notice_count;
	private String notice_use, notice_title, notice_writer, 
	notice_text, notice_date;
	
	private MultipartFile nfile;
	
	private int idx;
	private String nfile_name, nfile_url;
}
