package org.weicong.common.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author weicong
 * @date 2019年6月13日
 * @version 1.0
 */
public abstract class FileUtil {

	/**
	 * 单文件上传
	 * 
	 * @param file    文件
	 * @param saveDir 保存绝对路径，如：/opt/test/
	 * @return 文件名
	 * @throws Exception
	 */
	public static String upload(MultipartFile file, String saveDir) throws Exception {
		if (null == file || StringUtil.isBlank(saveDir)) {
			throw new NullPointerException(new StringBuilder("文件上传参数为空！file = ").append(file).append(", saveDir = ")
					.append(saveDir).toString());
		}
		makeDirIfNotExist(saveDir);
		String[] strings = file.getOriginalFilename().split("\\.");
		String fileName = DateUtil.getTimestamp();
		String path = new StringBuilder(saveDir).append(fileName).append(".").append(strings[1]).toString();
		File newFile = new File(path);
		file.transferTo(newFile);
		return fileName;
	}

	/**
	 * 单文件下载
	 * 
	 * @param response 客户端返回对象
	 * @param fileDir  文件所在目录路径 /opt/test/
	 * @param fileName 文件全名 test.txt
	 * @param isDel    用户下载后是否删除
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, String fileDir, String fileName, boolean isDel)
			throws Exception {
		if (null == response || StringUtil.isBlank(fileDir) || StringUtil.isBlank(fileName)) {
			throw new NullPointerException(new StringBuilder("文件下载参数为空！fileDir = ").append(fileDir)
					.append(", fileName = ").append(fileName).toString());
		}
		String filePath = fileDir + fileName;
		Path path = Paths.get(filePath);
		if (!Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			throw new FileNotFoundException("该文件不存在！filePath = " + filePath);
		}
		InputStream in = null;
		OutputStream out = null;
		ReadableByteChannel inputChannel = null;
		WritableByteChannel outputChannel = null;
		ByteBuffer buffer = null;

		try {
			in = new FileInputStream(filePath);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition",
					"attchement;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
			out = response.getOutputStream();
			inputChannel = Channels.newChannel(in);
			outputChannel = Channels.newChannel(out);
			buffer = ByteBuffer.allocateDirect(1024);
			while (inputChannel.read(buffer) != -1) {
				buffer.flip();
				outputChannel.write(buffer);
				buffer.clear();
			}
		} catch (Exception e) {
			throw new Exception("文件下载IO异常！e = " + e.getMessage());
		} finally {
			try {
				outputChannel.close();
				inputChannel.close();
				out.close();
				in.close();
				if (isDel) {
					Files.delete(path);
				}
			} catch (IOException e) {
				throw new IOException("关闭IO流异常！e = " + e.getMessage());
			}
		}
	}

	/**
	 * 如果不存在目录则创建目录
	 * 
	 * @param dir 目录地址
	 * @throws IOException
	 */
	private static void makeDirIfNotExist(String dir) throws IOException {
		Path path = Paths.get(dir);
		if (!Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				throw new IOException("创建目录失败！e = " + e.getMessage());
			}
		}
	}
}
