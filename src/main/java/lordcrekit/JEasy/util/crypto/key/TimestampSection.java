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
 * Builds a timestamp into a key section. Always gives the same (specified)
 * length, padded by 0's. Always base 36.
 * <p>
 * Default lengths were set on 2017-07-11T21:49 and should be valid until
 * 5188-04-22.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class TimestampSection implements KeySection {

  public final static int DEFAULT_36_LENGTH = 9;

  private final int length;

  /**
   * Constructs a new TimestampSection that pads to the given length.
   *
   * @param length
   *     The length to pad to Shorter than 8 and it probably won't work (unless
   *     your clock is really slow).
   */
  public TimestampSection(final int length) {
    this.length = length;
  }

  /**
   * Constructs a new TimestampSection that pads to 9 spaces. It will work until
   * the year 5188.
   */
  public TimestampSection() {
    this.length = TimestampSection.DEFAULT_36_LENGTH;
  }

  @Override
  public StringBuilder generate(final StringBuilder strb) {
    final String convert = Long.toString(System.currentTimeMillis(), 36);
    if (convert.length() > length)
      throw new RuntimeException("Length not enough!");
    else
      return strb.append(
          String.format("%1$" + length + "s", convert)
              .replace(' ', '0'));
  }
}
