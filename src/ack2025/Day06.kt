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
import kotlin.math.max

fun main() {
    // input
    val input = readInput("Day06", "ack2025")

    // part 1
    fun part1(input: List<String>): Long {
        val result: MutableList<Long> = mutableListOf()
        val garbage: MutableList<MutableList<Long>> = mutableListOf()
        val operation: MutableList<String> = mutableListOf()

        // loop
        input.forEach {
            var tempGarbage = ""
            val length = it.length
            val tempGarbageList: MutableList<Long> = mutableListOf()
            for (i in 0 until length) {
                if (it[i] == ' ') {
                    if (tempGarbage.isNotEmpty()) {
                        if ("*+".contains(tempGarbage)) operation.add(tempGarbage)
                        else tempGarbageList.add(tempGarbage.toLong())
                    }
                    tempGarbage = ""
                } else {
                    tempGarbage += it[i]
                }
                if (i + 1 == length) {
                    if (tempGarbage.isNotEmpty()) {
                        if ("*+".contains(tempGarbage)) operation.add(tempGarbage)
                        else tempGarbageList.add(tempGarbage.toLong())
                    }
                }
            }
            if (tempGarbageList.isNotEmpty()) garbage.add(tempGarbageList)
        }

        // sum all
        for (j in 0 until garbage[0].size) {
            var count = if (operation[j] == "*") 1L else 0L
            for (k in 0 until garbage.size) {
                if (operation[j] == "*") count *= garbage[k][j]
                else count += garbage[k][j]
            }
            result.add(count)
        }
        return result.sum()
    }

    part1(input).println()

    // part 2
    fun part2(input: List<String>): Long {
        val result: MutableList<Long> = mutableListOf()
        val garbageTemp: MutableList<Long> = mutableListOf()
        val operation: MutableList<String> = mutableListOf()
        var row = 0
        val column = input.size - 1
        var indexOperation = 0

        for (i in 0 until input.size) {
            if (i == input.size - 1) {
                for (j in input[i]) if (j == ' ') continue else operation.add("$j")
            } else {
                row = max(row, input[i].length)
            }
        }

        for (i in 0..row) {
            var count = ""
            for (j in 0 until column) {
                try {
                    if (input[j][i] == ' ') {
                        continue
                    } else {
                        count += input[j][i]
                    }
                } catch (_: Exception) {

                }
            }
            if (count.isEmpty()) {
                var count = if (operation[indexOperation] == "*") 1L else 0L
                garbageTemp.forEach {
                    if (operation[indexOperation] == "*") count *= it
                    else count += it
                }
                garbageTemp.removeAll(garbageTemp)
                result.add(count)
                indexOperation++
            } else {
                garbageTemp.add(count.toLong())
            }
        }

        return result.sum()
    }

    part2(input).println()
}