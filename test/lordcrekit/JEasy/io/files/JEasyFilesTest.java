/*
 * The MIT License
 *
 * Copyright 2016 lordc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lordcrekit.JEasy.io.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lordc
 */
public class JEasyFilesTest {

	private static final String TEST_DIRECTORY_PREFIX = "JEazy_Test";
	private static final String TEST_INTERNAL_FAILURE_MESSAGE = "internal test failure.";

	public JEasyFilesTest() {
	}

	static Map<String, Path> createTestDirectories( Path root ) throws IOException {
		Map<String, Path> output = new LinkedHashMap<>();
		{
			Path base = Files.createDirectory(Paths.get(root.toString(), "empty"));
			output.put("empty", base);
		}
		{
			Path base = Files.createDirectory(Paths.get(root.toString(), "1 file"));
			Files.createFile(Paths.get(base.toString(), "file.txt"));
			output.put("1 file", base);
		}
		{
			Path base = Files.createDirectory(Paths.get(root.toString(), "4 files"));
			for ( int i = 0; i < 4; ++i )
				Files.createFile(Paths.get(base.toString(), "file" + i + ".txt"));
			output.put("4 files", base);
		}
		{
			String title = "nested dirs only";
			Path base = Files.createDirectory(Paths.get(root.toString(), title));
			Files.createDirectory(Paths.get(base.toString(), "nested"));
			output.put(title, base);
		}
		{
			String title = "nested dirs and files";
			Path base = Files.createDirectory(Paths.get(root.toString(), title));

			for ( int i = 0; i < 4; ++i )
				Files.createFile(Paths.get(base.toString(), "file" + i + ".txt"));
			Path nested = Files.createDirectory(Paths.get(base.toString(), "nested"));

			for ( int i = 0; i < 4; ++i )
				Files.createFile(Paths.get(nested.toString(), "file" + i + ".txt"));
			output.put(title, base);
		}
		return output;
	}

	static void cleanupFiles( Path... paths ) {
		for ( Path p : paths )
			if ( p != null )
				try {
					if ( Files.exists(p) )
						JEasyFiles.deleteDirectory(p);
				} catch ( Exception ex ) {
					Logger.getLogger(JEasyFilesTest.class.getName()).log(Level.WARNING, "Failed to cleanup directory");
				}
	}

	/**
	 * Test of copyDirectory method, of class JEasyFiles.
	 */
	@Test
	public void testCopyDirectory() {
		System.out.println("copyDirectory");
		Path dir = null;
		try {
			dir = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			for ( Entry<String, Path> e : createTestDirectories(dir).entrySet() ) {
				System.out.println("\t" + e.getKey());
				try {
					Path olddir = e.getValue();
					Path newdir = Paths.get(dir.toString(), "_COPIED", olddir.getFileName().toString());
					JEasyFiles.copyDirectory(olddir, newdir);
					assertEquals(true, JEasyFiles.isDirectoryStructureEqual(olddir, newdir));
				} catch ( IOException ex ) {
					fail(ex.getMessage());
				} finally {
				}
			}
		} catch ( IOException ex ) {
			Logger.getLogger(JEasyFilesTest.class.getName()).log(Level.SEVERE, null, ex);
			fail(ex.getMessage());
		} finally {
			cleanupFiles(dir);
		}
	}

	/**
	 * Test of deleteDirectory method, of class JEasyFiles.
	 */
	@Test
	public void testDeleteDirectory() {
		System.out.println("deleteDirectory");
		Path dir = null;
		try {
			dir = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			Map<String, Path> dirs = createTestDirectories(dir);
			for ( Entry<String, Path> e : dirs.entrySet() ) {
				System.out.println("\t" + e.getKey());
				Path p = e.getValue();
				try {
					JEasyFiles.deleteDirectory(p);
					assertEquals(false, Files.exists(p));
				} catch ( IOException ex ) {
					fail(ex.getMessage());
				}
			}
		} catch ( IOException ex ) {
			fail(TEST_INTERNAL_FAILURE_MESSAGE);
		} finally {
			cleanupFiles(dir);
		}
	}

	@Test
	public void testIsFileStructureIdentical() {
		System.out.println("isFileStructureIdentical BASIC");

		Path dir1 = null;
		Path dir2 = null;
		try {
			dir1 = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			Map<String, Path> o1 = createTestDirectories(dir1);

			dir2 = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			Map<String, Path> o2 = createTestDirectories(dir2);

			assertEquals("Error in tests", o1.size(), o2.size());

			for ( String i : o1.keySet() )
				try {
					System.out.println("\t" + i);
					assertEquals(true, JEasyFiles.isDirectoryStructureEqual(o1.get(i), o2.get(i)));
				} catch ( IOException ex ) {
					fail(ex.getMessage());
				}

		} catch ( IOException ex ) {
			fail(TEST_INTERNAL_FAILURE_MESSAGE);
		} finally {
			cleanupFiles(dir1, dir2);
		}
	}

	public void testIsFileStructureIdentical_Custom() {
		System.out.println("isFileStructureIdentical CUSTOM");
		Path dir1 = null;
		Path dir2 = null;
		try {
			dir1 = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			dir2 = Files.createTempDirectory(TEST_DIRECTORY_PREFIX);
			assertEquals(true, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));

			Files.createFile(Paths.get(dir1.toString(), "file.txt"));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir2, dir1));
			Files.createFile(Paths.get(dir2.toString(), "file.txt"));
			assertEquals(true, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));

			Path dir1n = Files.createDirectory(Paths.get(dir1.toString(), "nest"));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir2, dir1));
			Path dir2n = Files.createDirectory(Paths.get(dir2.toString(), "nest"));
			assertEquals(true, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));

			Files.createFile(Paths.get(dir1n.toString(), "file.txt"));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));
			assertEquals(false, JEasyFiles.isDirectoryStructureEqual(dir2, dir1));
			Files.createFile(Paths.get(dir2n.toString(), "file.txt"));
			assertEquals(true, JEasyFiles.isDirectoryStructureEqual(dir1, dir2));

		} catch ( IOException ex ) {
			fail(TEST_INTERNAL_FAILURE_MESSAGE);
		} finally {
			cleanupFiles(dir1, dir2);
		}
	}
}
