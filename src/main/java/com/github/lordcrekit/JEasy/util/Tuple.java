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
package com.github.lordcrekit.JEasy.util;

/**
 * A tuple is a pointer to two different objects.
 *
 * @param <T>
 *     The type of the first value in the Tuple.
 * @param <G>
 *     The type of the second value in the Tuple.
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Tuple<T, G> {

  private T First;
  private G Second;

  /**
   * Constructs a new, empty Tuple.
   */
  public Tuple() {
  }

  /**
   * Constructs a new Tuple with the provided values.
   *
   * @param first
   *     The initial first value for the Tuple.
   * @param second
   *     The initial second value for the Tuple.
   */
  public Tuple(T first, G second) {
    this.First = first;
    this.Second = second;
  }

  /**
   * Gets the first value in the Tuple.
   *
   * @return The first value in the Tuple.
   * @deprecated Just use the public member.
   */
  @Deprecated
  public T getFirst() {
    return this.First;
  }

  /**
   * Sets the first value in the Tuple.
   *
   * @param val
   *     The new first value for the Tuple.
   * @return A pointer back to the Tuple.
   * @deprecated Just use the public member.
   */
  @Deprecated
  public Tuple<T, G> setFirst(T val) {
    this.First = val;
    return this;
  }

  /**
   * Gets the second value in the Tuple.
   *
   * @return The second value in the Tuple.
   * @deprecated Just use the public member.
   */
  @Deprecated
  public G getSecond() {
    return this.Second;
  }

  /**
   * Sets the second value in the Tuple.
   *
   * @param val
   *     The new second value for the Tuple.
   * @return A pointer back to the Tuple.
   * @deprecated Just use the public member.
   */
  @Deprecated
  public Tuple<T, G> setSecond(G val) {
    this.Second = val;
    return this;
  }

  @Override
  public String toString() {
    return '<' + First.toString() + ',' + Second.toString() + '>';
  }
}
