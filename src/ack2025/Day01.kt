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
        name = "Day01", folder = "ack2025"
    )

    // part 1 count how many 0 number appears in dialStart
    fun part1(input: List<String>): Int {
        var dialStart = 50
        var output = 0
        input.forEach { item ->
            val lr = item[0]
            val num = item.substring(1).toInt()
            dialStart = if (lr == 'L') {
                positiveMod(dialStart - num, 100)
            } else {
                (dialStart + num) % 100
            }
            if (dialStart == 0) output++
        }
        return output
    }
    part1(input).println()

    // part 2 same as part 1 but when everytime across 0, count zeros
    fun part2(input: List<String>): Int {
        var dialStart = 50
        var output = 0
        input.forEach { item ->
            val delta = if(item[0] == 'L') -1 else 1
            repeat(item.drop(1).toInt()){
                dialStart = (dialStart + delta) % 100
                if (dialStart == 0) output++
            }
        }
        return output
    }
    part2(input).println()
}

fun positiveMod(a: Int, b: Int): Int = ((a % b) + b) % b