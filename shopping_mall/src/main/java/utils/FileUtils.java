package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import dto.NoticeDto;
import dto.ProductsDto;

public class FileUtils {

	public void deleteProductFiles(List<ProductsDto> dto,HttpServletRequest req) {
		for(ProductsDto a : dto) {
			String fu = a.getMfile_url();
			int ind = fu.lastIndexOf("/");
			String url = req.getServletContext().getRealPath("/upload/")+fu.substring(ind+1);
			File file1 = new File(url);
			File file2 = new File(a.getFile1_url());
			File file3 = new File(a.getFile2_url());
			file1.delete();
			file2.delete();
			file3.delete();
		}
	}
	
	public void deleteNoticeFile(List<NoticeDto> dto,HttpServletRequest req) {
		for(NoticeDto a : dto) {
			String fu = a.getNfile_url();
			if(!fu.equals("")) {
				int ind = fu.lastIndexOf("/");
				String url = req.getServletContext().getRealPath("/upload/")+fu.substring(ind+1);
				File file = new File(url);
				file.delete();
			}
		}
	}
	
	public ProductsDto file_url(ProductsDto dto,HttpServletRequest req) throws IOException {
		MultipartFile mfile = dto.getPfile();
		MultipartFile file1 = dto.getPadd_file1();
		MultipartFile file2 = dto.getPadd_file2();
		
		String url = req.getServletContext().getRealPath("/upload/");
		
		String name1 = mfile.getOriginalFilename();
		int indSub = name1.lastIndexOf(".");
		String mfile_renm = this.re() + name1.substring(indSub);
		String mfile_url = url + mfile_renm;
		File save1 = new File(mfile_url);
		mfile.transferTo(save1);
		dto.setMfile_name(name1);
		dto.setMfile_url("http://localhost:8080/upload/"+mfile_renm);
		
		if (file1.getSize() > 0) {
			String name2 = file1.getOriginalFilename();
			indSub = name2.lastIndexOf(".");
			String file1_renm = this.re() + name2.substring(indSub);
			String file1_url = url + file1_renm;
			File save2 = new File(file1_url);
			file1.transferTo(save2);
			dto.setFile1_name(name2);
			dto.setFile1_url("http://localhost:8080/upload/"+file1_renm);
		}
		
		if (file2.getSize() > 0) {
			String name3 = file2.getOriginalFilename();
			indSub = name3.lastIndexOf(".");
			String file2_renm = this.re() + name3.substring(indSub);
			String file2_url = url + file2_renm;
			File save3 = new File(file2_url);
			file2.transferTo(save3);
			dto.setFile2_name(name3);
			dto.setFile2_url("http://localhost:8080/upload/"+file2_renm);
		}
		return dto;
	}
	
	public NoticeDto file_url(NoticeDto dto,HttpServletRequest req) throws IOException {
		MultipartFile nfile = dto.getNfile();
		
		String url = req.getServletContext().getRealPath("/upload/");
		
		if(nfile.getSize() > 0) {
			String name = nfile.getOriginalFilename();
			int indSub = name.lastIndexOf(".");
			String nfile_renm = this.re() + name.substring(indSub);
			String nfile_url = url + nfile_renm;
			File save = new File(nfile_url);
			nfile.transferTo(save);
			dto.setNfile_name(name);
			dto.setNfile_url("http://localhost:8080/upload/"+nfile_renm);
		}
		return dto;
	}
	
	public String re() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHms");
		int rn = (int)Math.ceil(Math.random()*3000);
		return sdf.format(today)+rn;
	}
}
