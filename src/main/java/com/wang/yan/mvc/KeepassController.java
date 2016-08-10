package com.wang.yan.mvc;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

@Controller
@RequestMapping("/keepass")
public class KeepassController {
	private static final Logger logger = Logger.getLogger(KeepassController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) throws Exception {
		model.addAttribute("message", "Health Check");

		System.out.println( "Hello World!" );

		DbxRequestConfig config = new DbxRequestConfig("yanwangch_test123/1.0", "en_US");
		DbxClientV2 client = new DbxClientV2(config, "08dbXuYWlPEAAAAAAAAQcPO7tobYMZXNnJjPnDzSo7U6ZZxcx4eZ4HfIlQhBAj5y");

		testDropboxClientConfig(client);

		DbxDownloader<FileMetadata> dbxDownloader = client.files().download("/Yan Wang/YW_Private_Keys_v1.kdbx");
		InputStream inputStream = dbxDownloader.getInputStream();

		OutputStream outputStream = new FileOutputStream(new File("YW_Private_Keys_v1.kdbx"));

		byte[] buffer = new byte[1024];
		int bytesRead;
		//read from is to buffer
		while((bytesRead = inputStream.read(buffer)) !=-1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		//flush OutputStream to write any buffered data to file
		outputStream.flush();
		outputStream.close();

		System.out.print(dbxDownloader.getResult().getSize());
	return "keepass";
	}

	private void testDropboxClientConfig(DbxClientV2 client) throws DbxException {
		// Get current account info
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());

		// Get files and folder metadata from Dropbox root directory
		ListFolderResult result = client.files().listFolder("");
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println(metadata.getPathLower());
				System.out.println(metadata.getPathDisplay());
				System.out.println(metadata.getName());
				System.out.println(metadata.getParentSharedFolderId());
				System.out.println("******************************");
			}

			if (!result.getHasMore()) {
				break;
			}

			result = client.files().listFolderContinue(result.getCursor());
		}
	}
}
