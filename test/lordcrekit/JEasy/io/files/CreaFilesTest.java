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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lordc
 */
public class CreaFilesTest {
	
	public CreaFilesTest() {
	}

	/**
	 * Test of copyDirectory method, of class CreaFiles.
	 */
	@Test
	public void testCopyDirectory() {
		System.out.println("copyDirectory");
		try {
			Files.createTempDirectory("JEazy_Test");
		} catch ( IOException ex ) {
			Logger.getLogger(CreaFilesTest.class.getName()).log(Level.SEVERE, null, ex);
			fail(ex.getMessage());
		}
		CreaFiles.copyDirectory(directory, destination);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deleteDirectory method, of class CreaFiles.
	 */
	@Test
	public void testDeleteDirectory() throws Exception {
		System.out.println("deleteDirectory");
		File directory = null;
		CreaFiles.deleteDirectory(directory);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
