package br.com.supersim.blog.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	
	public static File multipartToFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File convFile = new File(multipartFile.getOriginalFilename());
		multipartFile.transferTo(convFile);
		return convFile;
	}
	
	public static byte[] toByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}

		return os.toByteArray();
	}
	
	public static ResponseEntity<ByteArrayResource> downloadFile(InputStream in) throws IOException{
		
		ByteArrayResource resource = new ByteArrayResource(toByteArray(in));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=")
				.contentLength(resource.contentLength())
		        .contentType(MediaType.APPLICATION_OCTET_STREAM)
		        .body(resource);
	}

}
