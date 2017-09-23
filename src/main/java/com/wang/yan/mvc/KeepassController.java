package com.wang.yan.mvc;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.wang.yan.mvc.model.SearchKeepass;
import de.slackspace.openkeepass.KeePassDatabase;
import de.slackspace.openkeepass.domain.Entry;
import de.slackspace.openkeepass.domain.KeePassFile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/keepass")
public class KeepassController {
	private static final Logger logger = Logger.getLogger(KeepassController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(ModelMap model) throws Exception {
		model.addAttribute("message", "Health Check");
		model.addAttribute("seacheKeepass", new SearchKeepass());

	return "keepass";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postPage(ModelMap model) throws Exception {
		model.addAttribute("message", "Health Check");

		System.out.println( "Hello World!" );

		DbxRequestConfig config = new DbxRequestConfig("yanwangch_test123/1.0", "en_US");
		DbxClientV2 client = new DbxClientV2(config, "08dbXuYWlPEAAAAAAAAQcPO7tobYMZXNnJjPnDzSo7U6ZZxcx4eZ4HfIlQhBAj5y");

//		testDropboxClientConfig(client);

		DbxDownloader<FileMetadata> dbxDownloader = client.files().download("/Yan Wang/YW_Private_Keys_v1.kdbx");
		InputStream inputStream = dbxDownloader.getInputStream();

		// Open Database
		KeePassFile database = KeePassDatabase.getInstance(inputStream).openDatabase("ouafahwafa79");

		// Retrieve all entries
		List<Entry> entries = database.getEntries();

		for (Entry entry : entries) {
			System.out.println(entry.getPassword());
			System.out.println(entry.getTitle());
			System.out.println("*******************************");
		}
		System.out.println(entries.size());
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
