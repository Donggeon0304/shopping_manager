package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFile {
	private int idx, pidx;
	private String mfile_name, mfile_url, file1_name, file1_url, file2_name, file2_url;
}