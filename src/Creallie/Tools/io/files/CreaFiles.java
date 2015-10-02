/*
 * The MIT License
 *
 * Copyright 2015 William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu).
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
package Creallie.Tools.io.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Additional methods for dealing with files and file structures.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class CreaFiles {

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    /**
     * Copies the given directory to the desired destination. The directories name will be overwritten with the destinations name, but all files inside of the
     * directory will maintain their names and structure.
     *
     * @param directory <code>{@link File }</code>: The directory you wish to copy.
     * @param destination <code>{@link File }</code>: The destination you wish to copy to.
     * @throws IOException If something goes wrong trying to copy the files.
     */
    public static void copyDirectory( File directory, File destination ) throws IOException {
        if ( directory.isDirectory() ) {
            Files.createDirectories(destination.toPath());
            for ( File i : directory.listFiles() )
                copyDirectory(i, new File(destination + File.separator + i.getName()));
        } else
            Files.copy(directory.toPath(), destination.toPath());
    }

    /**
     * Deletes an entire directory and all it's sub folders and files.
     *
     * @param directory <code>{@link File }</code>: The directory you wish to delete.
     * @throws IOException If something goes wrong trying to delete the files.
     */
    public static void deleteDirectory( File directory ) throws IOException {
        if ( directory.isDirectory() )
            for ( File i : directory.listFiles() )
                deleteDirectory(i);
        Files.delete(directory.toPath());
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /**
     * Gets the App Data file for this operating system.
     *
     * @return <code>{@link File }</code>: The App Data file for this OS.
     * @throws IOException If the OS is unknown.
     */
    public static File getAppDataFile() throws IOException {
        String OS = (System.getProperty("os.name")).toUpperCase();
        if ( OS.contains("WIN") )
            return new File(System.getenv("AppData"));
        else if ( OS.contains("MAC") )
            return new File(System.getProperty("user.home") + "/Library/Application " + "Support");
        else if ( OS.contains("NUX") )
            return new File(System.getProperty("user.home"));
        else
            throw new IOException("Unkown operating system!");
    }

    /**
     * Gets the Temp file for this operating system.
     *
     * @return <code>{@link File }</code>: The Temp file for this OS.
     * @throws IOException If the OS is unknown.
     */
    public static File getTempFile() throws IOException {
        String OS = (System.getProperty("os.name")).toUpperCase();
        throw new UnsupportedOperationException("TODO: THIS");
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    /*
     * ================================================ PRIVATE FUNCTIONS ===============================================
     */
}
