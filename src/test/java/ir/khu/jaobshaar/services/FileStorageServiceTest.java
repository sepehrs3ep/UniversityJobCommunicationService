package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.utils.FileStorageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class FileStorageServiceTest {

	private final FileStorageService fileStorageService;

	public FileStorageServiceTest() {
		this.fileStorageService = Mockito.mock(FileStorageService.class);
	}

	@Test
	public void storeFile() {
		Random random = new Random();
		MultipartFile file = new MultipartFile() {
			@Override
			public String getName() {
				return "file" + random.nextInt();
			}

			@Override
			public String getOriginalFilename() {
				return "file";
			}

			@Override
			public String getContentType() {
				return "image/png";
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public long getSize() {
				return 100000;
			}

			@Override
			public byte[] getBytes() throws IOException {
				return new byte[0];
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return null;
			}

			@Override
			public void transferTo(File file) throws IOException, IllegalStateException {

			}
		};
		String fileName = fileStorageService.storeFile(file);
		Assert.assertNull(fileName);
	}

	@Test
	public void replaceFile() {
		Random random = new Random();
		MultipartFile file = new MultipartFile() {
			@Override
			public String getName() {
				return "file" + random.nextInt();
			}

			@Override
			public String getOriginalFilename() {
				return "file";
			}

			@Override
			public String getContentType() {
				return "image/png";
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public long getSize() {
				return 100000;
			}

			@Override
			public byte[] getBytes() throws IOException {
				return new byte[0];
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return null;
			}

			@Override
			public void transferTo(File file) throws IOException, IllegalStateException {

			}
		};
		String filename = fileStorageService.replaceFile(file, "file" + random.nextInt());
		Assert.assertNull(filename);
	}

	@Test
	public void loadFileAsResource() {
		Random random = new Random();
		Resource resource = fileStorageService.loadFileAsResource("file" + random.nextInt());
		Assert.assertNull(resource);
	}

	@Test
	public void deleteFile() {
		Random random = new Random();
		String fileName ="file" + random.nextInt();
		fileStorageService.deleteFile(fileName);
		Resource resource =fileStorageService.loadFileAsResource(fileName);
		Assert.assertNull(resource);
	}
}
