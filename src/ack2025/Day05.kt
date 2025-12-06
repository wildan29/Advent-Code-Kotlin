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
        name = "Day05", folder = "ack2025"
    )

    // part 1
    fun part1(input: List<String>): Int {
        val ranges: MutableList<String> = mutableListOf()
        var isLimit = false
        var count = 0
        input.forEach {
            if (it == "") {
                isLimit = true
            } else if (isLimit) {
                for (i in ranges) {
                    val splitData = i.split("-")
                    val fTemp = splitData[0].toLong()
                    val lTemp = splitData[1].toLong()
                    if (it.toLong() in fTemp..lTemp) {
                        count++
                        break
                    }
                }
            } else {
                ranges.add(it)
            }
        }
        return count
    }
    part1(input).println()

    // part 2 (still didn't know, how to optimize?)
    fun part2(input: List<String>):Int {
        val ranges: MutableList<String> = mutableListOf()
        var isLimit = false
        val isFresh: MutableList<Long> = mutableListOf()
        val indexing: MutableSet<Int> = mutableSetOf()
        val finalIngredients: MutableSet<Long> = mutableSetOf()

        // initial data
        input.forEach {
            if (it == "") {
                isLimit = true
            } else if (isLimit) {
                isFresh.add(it.toLong())
            } else {
                ranges.add(it)
            }
        }

        // check if index is fresh
        for (i in isFresh) {
            for (j in 0 until ranges.size) {
                val splitData = ranges[j].split("-")
                val fTemp = splitData[0].toLong()
                val lTemp = splitData[1].toLong()
                if (i in fTemp..lTemp) {
                    indexing.add(j)
                }
            }
        }

        // count all fresh ingredients
        indexing.forEach {
            val splitData = ranges[it].split("-")
            val fTemp = splitData[0].toLong()
            val lTemp = splitData[1].toLong()
            for(i in fTemp..lTemp){
                finalIngredients.add(i)
            }
        }

        return finalIngredients.size
    }

    part2(input).println()
}