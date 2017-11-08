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
package com.github.lordcrekit.JEasy.util.crypto.key;

import java.util.Random;

/**
 * Generates a random series of characters for the key.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public class RandomSection implements KeySection {

  public final static int DEFAULT_LENGTH = 16;
  public final static String DEFAULT_ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
  public final static String LOWERCASE_ALPHABET = "qwertyuiopasdfghjklzxcvbnm1234567890";

  private final static Random RNG = new Random();

  private final int length;
  private final String alphabet;

  /**
   * Constructs a new RandomSection that generates a key section from the given
   * alphabet of the given length.
   *
   * @param length
   *     The length of the section to generate.
   * @param alphabet
   *     The alphabet to generate the section from.
   */
  public RandomSection(final int length, final String alphabet) {
    this.length = length;
    this.alphabet = alphabet;
  }

  /**
   * Constructs a new RandomSection with the default length and alphabet. The
   * default is 16 long, all uppercase/lowercase ASCII letters + digits.
   */
  public RandomSection() {
    this.length = RandomSection.DEFAULT_LENGTH;
    this.alphabet = RandomSection.DEFAULT_ALPHABET;
  }

  @Override
  public StringBuilder generate(final StringBuilder strb) {
    for (int i = 0; i < length; i++)
      strb.append(alphabet.charAt(RNG.nextInt(alphabet.length())));
    return strb;
  }
}
