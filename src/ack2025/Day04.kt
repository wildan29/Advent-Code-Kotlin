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
        name = "Day04", folder = "ack2025"
    )

    // part 1
    fun part1(input: List<String>): Int {
        val row = input.size
        val column = input[0].length
        var count = 0
        val rollOfPapers = Array(row) { Array(column) { 0 } }

        // find the position of roll of papers
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (input[i][j] == '@') rollOfPapers[i][j] = 1
            }
        }

        // count 8 adjacent position
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (rollOfPapers[i][j] == 1) {
                    var tempRollOfPaperS = 0
                    for (k in i - 1..i + 1) {
                        for (l in j - 1..j + 1) {
                            if (k == i && l == j) continue
                            if (k == -1 || l == -1) continue
                            if (k == row || l == column) continue
                            if (input[k][l] == '@') tempRollOfPaperS++
                        }
                    }
                    if (tempRollOfPaperS < 4) count++
                }
            }
        }

        return count
    }

    part1(input).println()


    // part 1
    fun part2(input: List<String>): Int {
        val row = input.size
        val column = input[0].length
        val countAll: MutableList<Int> = mutableListOf()
        val rollOfPapers = Array(row) { Array(column) { 0 } }

        // find the position of roll of papers
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (input[i][j] == '@') rollOfPapers[i][j] = 1
            }
        }

        // find as many as possible roll of papers
        while (true) {
            var count = 0
            val rollOfPaperDups = Array(row) { Array(column) { 0 } }

            // count 8 adjacent position
            for (i in 0 until row) {
                for (j in 0 until column) {
                    if (rollOfPapers[i][j] == 1) {
                        var tempRollOfPaperS = 0
                        for (k in i - 1..i + 1) {
                            for (l in j - 1..j + 1) {
                                if (k == i && l == j) continue
                                if (k == -1 || l == -1) continue
                                if (k == row || l == column) continue
                                if (rollOfPapers[k][l] == 1) tempRollOfPaperS++
                            }
                        }
                        if (tempRollOfPaperS < 4) {
                            count++
                            rollOfPaperDups[i][j] = 1
                        }
                    }
                }
            }

            // mark again
            for (i in 0 until row) {
                for (j in 0 until column) {
                    if (rollOfPaperDups[i][j] == 1) {
                        rollOfPapers[i][j] = 0
                    }
                }
            }

            if (count == 0) {
                break
            } else {
                countAll.add(count)
            }
        }

        return countAll.sum()
    }

    part2(input).println()
}