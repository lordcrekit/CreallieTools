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
package com.github.lordcrekit.JEasy.io.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Additional methods for dealing with files and file structures.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class JEasyFiles {

  /**
   * Copies the given directory to the desired destination. The directories name
   * will be overwritten with the destinations name, but all files inside of the
   * directory will maintain their names and structure.
   *
   * @param directory
   *     The directory you wish to copy.
   * @param destination
   *     The destination you wish to copy to.
   * @throws IOException
   *     If something goes wrong trying to copy the files.
   */
  @Deprecated
  public static void copyDirectory(File directory, File destination) throws IOException {
    if (directory.isDirectory()) {
      Files.createDirectories(destination.toPath());
      for (File i : directory.listFiles())
        copyDirectory(i, new File(destination + File.separator + i.getName()));
    } else
      Files.copy(directory.toPath(), destination.toPath());
  }

  /**
   * Copies the given directory to the desired destination. The directories name
   * will be overwritten with the destinations name, but all files inside of the
   * directory will maintain their names and structure.
   *
   * @param source
   *     The directory to copy.
   * @param destination
   *     The destination to copy the directory to.
   * @throws IOException
   *     If something goes wrong trying to copy the files.
   */
  public static void copyDirectory(Path source, Path destination) throws IOException {
    copyDirectory(source.toFile(), destination.toFile());
  }

  /**
   * Deletes an entire directory and all it's sub folders and files.
   *
   * @param directory
   *     The directory you wish to delete.
   * @throws IOException
   *     If something goes wrong trying to delete the files.
   */
  @Deprecated
  public static void deleteDirectory(File directory) throws IOException {
    if (directory.isDirectory())
      for (File i : directory.listFiles())
        deleteDirectory(i);
    Files.delete(directory.toPath());
  }

  /**
   * Deletes an entire directory and all it's sub folders and files.
   *
   * @param directory
   *     The directory you wish to delete.
   * @throws IOException
   *     If something goes wrong trying to delete the files.
   */
  public static void deleteDirectory(Path directory) throws IOException {
    deleteDirectory(directory.toFile());
  }

  /**
   * @param dir1
   * @param dir2
   * @return
   * @throws java.io.IOException
   */
  public static boolean isDirectoryStructureEqual(Path dir1, Path dir2) throws IOException {
    return dir_to_map(dir1.toString().length(), dir1).equals(dir_to_map(dir2.toString().length(), dir2));
  }


  /**
   * Gets the App Data file pathname for this operating system.
   *
   * @return The pathname to the App Data file for this OS.
   * @throws IOException
   */
  public static String getAppData() throws IOException {
    String OS = (System.getProperty("os.name")).toUpperCase();
    if (OS.contains("WIN"))
      return System.getenv("AppData");
    else if (OS.contains("MAC"))
      return System.getProperty("user.home") + "/Library/Application " + "Support";
    else if (OS.contains("NUX"))
      return System.getProperty("user.home");
    else
      throw new IOException("Unkown operating system!");
  }

  /**
   * Gets the App Data file for this operating system.
   *
   * @return The App Data file for this OS.
   * @throws IOException
   *     If the OS is unknown.
   */
  public static Path getAppDataPath() throws IOException {
    return getAppDataFile().toPath();
  }

  /**
   * Gets the App Data file for this operating system.
   *
   * @return The App Data file for this OS.
   * @throws IOException
   *     If the OS is unknown.
   * @deprecated Use {@link #getAppDataPath()}
   */
  @Deprecated
  public static File getAppDataFile() throws IOException {
    return new File(getAppData());
  }

  /**
   * @param root
   * @param dir
   * @return
   * @throws IOException
   */
  private static Map<String, Boolean> dir_to_map(int root, Path dir) throws IOException {
    Map<String, Boolean> output = new HashMap<>();
    final AtomicReference<IOException> ref = new AtomicReference<>();
    Files.list(dir).forEach((Path p) -> {
      boolean isDir = Files.isDirectory(p);
      if (isDir)
        try {
          output.putAll(dir_to_map(root, p));
        } catch (IOException ex) {
          ref.set(ex);
        }
      output.put(p.toString().substring(root), isDir);
    });
    if (ref.get() != null)
      throw ref.get();
    return output;
  }
}
