/*
 * MIT License
 *
 * Copyright (c) 2025 Mochammad Wildan Alghifari
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ack2025

import println
import readInput

fun main() {
    // input
    val input = readInput(
        name = "Day03", folder = "ack2025"
    )

    // part 1
    fun part1(input: List<String>): Int {
        val result: MutableList<Int> = mutableListOf()
        input.forEach { banks ->
            var fTemp = '0'
            var fTempIndx = 0
            for (i in 0 until banks.length - 1) {
                if (fTemp.code < banks[i].code) {
                    fTemp = banks[i]
                    fTempIndx = i
                }
            }
            var sTemp = '0'
            for (i in fTempIndx + 1 until banks.length) {
                if (sTemp.code < banks[i].code) sTemp = banks[i]
            }
            result.add("$fTemp$sTemp".toInt())
        }
        return result.sum()
    }

    part1(input).println()

    // part 2
    fun part2(input: List<String>): Long {
        var result = 0L
        val k = 12
        input.forEach { banks ->
            var r = banks.length - k
            val stack = ArrayDeque<Char>()
            for (c in banks) {
                while (r > 0 && stack.isNotEmpty() && stack.last() < c) {
                    stack.removeLast()
                    r--
                }
                stack.addLast(c)
            }
            while (r > 0) {
                stack.removeLast()
                r--
            }
            val value = stack.take(k).joinToString("").toLong()
            result += value
        }
        return result
    }

    part2(input).println()
}