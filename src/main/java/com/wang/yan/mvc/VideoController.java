package com.wang.yan.mvc;

import com.wang.yan.mvc.model.Message;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/videos")
public class VideoController {

	private static final Logger logger = Logger.getLogger(VideoController.class);

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String loadVideos(@PathVariable("id") long id, ModelMap model, HttpServletResponse response) {

		model.addAttribute("message", "Video");
		String path = "/home/ywang/Downloads/video_hq";
		String extension = ".mp4";
		String filePath;
		if (String.valueOf(id) == null || String.valueOf(id).equals("0")) {
			filePath = path + extension;
		} else {
			filePath = path + " (" + String.valueOf(id) + ")" + extension;
		}
		try {
			int fileSize = (int) new File(filePath).length();
			response.setContentLength(fileSize);
			response.setContentType("video/mp4");
			FileInputStream inputStream = new FileInputStream(filePath);
			ServletOutputStream outputStream = response.getOutputStream();
			int value = IOUtils.copy(inputStream, outputStream);
			System.out.println("File Size :: "+fileSize);
			System.out.println("Copied Bytes :: "+value);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (java.io.FileNotFoundException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return "videos";
	}
}