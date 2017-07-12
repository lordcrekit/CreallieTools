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
package lordcrekit.JEasy.util.crypto.key;

/**
 * Generates IDs/keys based on given criteria (in the form of KeySections). Use
 * it to generate structured random IDs.
 * <p>
 * You might use it to generate inproc zmq addresses, for example.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class KeyGenerator {

  /**
   * Generates IDs of the structure defined by passed KeySections. This is not
   * guaranteed to be unique (especially if you use no random KeySections!).
   *
   * @param sections
   *     All the KeySections that the new ID will be made from.
   * @return The generated ID.
   * @see KeySection
   * @see RandomSection
   * @see StringSection
   * @see TimestampSection
   */
  public static final String generateID(KeySection... sections) {
    StringBuilder strb = new StringBuilder();
    for (KeySection i : sections)
      i.generate(strb);

    return strb.toString();
  }
}
