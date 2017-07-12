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
package lordcrekit.JEasy.util;

/**
 * Because {@link Tuple } just wasn't good enough.
 *
 * @param <T>
 *     The type of the first value.
 * @param <G>
 *     The type of the second value.
 * @param <U>
 *     The type of the third value.
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Triple<T, G, U> {

  public T First;
  public G Second;
  public U Third;

  /**
   * Constructs a new, empty Triple.
   */
  public Triple() {
    this.First = null;
    this.Second = null;
    this.Third = null;
  }

  /**
   * Constructs a new Triple with set values.
   *
   * @param first
   *     The initial first value for the Triple.
   * @param second
   *     The initial second value for the Triple.
   * @param third
   *     The initial third value for the Triple.
   */
  public Triple(T first, G second, U third) {
    this.First = first;
    this.Second = second;
    this.Third = third;
  }

  /**
   * Gets the first value in the Triple.
   *
   * @return The first value in the Triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public T getFirst() {
    return this.First;
  }

  /**
   * Sets the first value in the Triple.
   *
   * @param first
   *     The new first value for the Triple.
   * @return A pointer back to this Triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public Triple<T, G, U> setFirst(T first) {
    this.First = first;
    return this;
  }

  /**
   * Gets the second value in the Triple.
   *
   * @return The second value in the triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public G getSecond() {
    return this.Second;
  }

  /**
   * Set the second value in the Triple.
   *
   * @param second
   *     The new Second value for the Triple.
   * @return A pointer back to the Triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public Triple<T, G, U> setSecond(G second) {
    this.Second = second;
    return this;
  }

  /**
   * Get the third value in the Triple.
   *
   * @return The third value in the Triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public U getThird() {
    return this.Third;
  }

  /**
   * Set the third value in the Triple.
   *
   * @param third
   *     The new third value for the Triple.
   * @return A pointer back to the Triple.
   * @deprecated Just use public member
   */
  @Deprecated
  public Triple<T, G, U> setThird(U third) {
    this.Third = third;
    return this;
  }

  @Override
  public String toString() {
    return '<' + First.toString()
        + ',' + Second.toString()
        + ',' + Third.toString() + '>';
  }
}
