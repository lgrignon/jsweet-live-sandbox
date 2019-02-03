package org.jsweet.webapi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	public static File tempDir = new File(System.getProperty("java.io.tmpdir"));

	public static final String TRANSLATOR_OUT_DIR_NAME = "translatorOutDir";

	public static List<File> decompress(File zipFile, File tempDir)
			throws FileNotFoundException, ArchiveException, IOException {

		logger.info("decompressing " + zipFile);

		List<File> archiveContents = new ArrayList<File>();

		final InputStream is = new FileInputStream(zipFile);
		ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, is);

		ZipArchiveEntry entry = (ZipArchiveEntry) ais.getNextEntry();
		while (entry != null) {
			logger.info("zip entry: " + entry);
			File outputFile = new File(tempDir, entry.getName());
			if (!entry.isDirectory()) {
				FileUtils.copyInputStreamToFile(ais, outputFile);
				archiveContents.add(outputFile);
				logger.info("extracted: " + outputFile);
			}
			entry = (ZipArchiveEntry) ais.getNextEntry();
		}

		ais.close();
		is.close();

		logger.info("end decompressing " + zipFile);
		return archiveContents;
	}

	public static File compress(File sourceDir, File zipFile) throws IOException, ArchiveException {

		logger.info("compressing " + sourceDir + " to " + zipFile);

		OutputStream archiveStream = new FileOutputStream(zipFile);
		ArchiveOutputStream archive = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,
				archiveStream);

		Collection<File> fileList = FileUtils.listFiles(sourceDir, null, true);
		logger.info("file list: " + fileList);

		for (File file : fileList) {

			String entryName = getEntryName(sourceDir, file);

			ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
			archive.putArchiveEntry(entry);

			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

			IOUtils.copy(input, archive);
			input.close();
			archive.closeArchiveEntry();
		}

		archive.finish();
		archiveStream.close();
		return zipFile;
	}

	private static String getEntryName(File source, File file) throws IOException {
		// int index = source.getAbsolutePath().length() + 1;
		// String path = file.getCanonicalPath();

		return file.getAbsolutePath().substring(
				file.getAbsolutePath().lastIndexOf(TRANSLATOR_OUT_DIR_NAME) + TRANSLATOR_OUT_DIR_NAME.length() + 1);
	}

}
