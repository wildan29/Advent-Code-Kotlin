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
        name = "Day02", folder = "ack2025"
    )

    // part 1
    fun part1(input: List<String>): Long {
        val result: MutableList<Long> = mutableListOf()
        input.forEach { id ->
            id.split(",").forEach { ranges ->
                val rangeItem = ranges.split('-')
                val first = rangeItem[0].toLong()
                val last = rangeItem[1].toLong()
                for (i in first..last) {
                    if (isValidId(i.toString())) {
                        result.add(i)
                    }
                }
            }
        }
        return result.sum()
    }

    part1(input).println()

    // part 2
    fun part2(input: List<String>): Long {
        val result: MutableList<Long> = mutableListOf()
        input.forEach { id ->
            id.split(",").forEach { ranges ->
                val rangeItem = ranges.split('-')
                val first = rangeItem[0].toLong()
                val last = rangeItem[1].toLong()
                for (i in first..last) {
                    if (isValidId1(i.toString())) {
                        result.add(i)
                    }
                }
            }
        }
        return result.sum()
    }

    part2(input).println()
}

fun isValidId(value: String): Boolean {
    if (value.length % 2 == 0) {
        val size = value.length / 2
        val firstSeq = value.take(size)
        val lastSeq = value.substring(size)
        return firstSeq == lastSeq
    }
    return false
}

fun isValidId1(value: String): Boolean {
    val maxLoops = value.length / 2
    for (i in 1..maxLoops) {
        if (value.length % i != 0) continue
        val seq = value.take(i)
        var boolTemp = true
        for (j in 0 until value.length / i) {
            val fIndex = i * j
            val lIndex = ((j + 1) * i) - 1
            val valueTemp = value.substring(fIndex..lIndex)
            if (seq != valueTemp) {
                boolTemp = false
                break
            }
        }
        if (boolTemp) {
            return true
        }
    }
    return false
}