package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.NoticeDto;
import mapper.ShoppingMapper;
import utils.FileUtils;

@Service
public class NoticeService {
	
	@Autowired
	ShoppingMapper mp;
	
	public boolean addNotice(NoticeDto dto) {
		dto.setNotice_count(0);
		if(dto.getNotice_use() == null) {
			dto.setNotice_use("N");
		}
		return mp.notice_insert(dto) > 0 ;
	}
	public boolean addNoticeFile(NoticeDto dto,HttpServletRequest req) throws IOException  {
		if(dto.getNfile().getSize() > 0) {
			dto = new FileUtils().file_url(dto, req);
			return mp.notice_file_insert(dto) > 0;
		}else {
			return true;
		}
	}
	public int getNoticeIdx() {
		return mp.notice_select_idx();
	}
	public List<NoticeDto> getNotice(int page, int size){
		return mp.notice_select(page, size);
	}
	public List<NoticeDto> getNoticeFile(int page, int size){
		return mp.notice_file_select(page, size);
	}
	public int getNoticeCk() {
		return mp.notice_ck();
	}
	public NoticeDto getNoticeOne(int nidx) {
		return mp.notice_select_one(nidx);
	}
	public boolean countNoticeView(int nidx) {
		return mp.notice_count(nidx) > 0;
	}
	public boolean deleteNotice(List<String> nidx) {
		return mp.notice_delete(nidx) > 0;
	}
	public boolean deleteNoticeFile(List<String> nidx) {
		System.out.println(nidx);
		System.out.println("파일삭제");
		return mp.notice_file_delete(nidx) > 0;
	}
}
