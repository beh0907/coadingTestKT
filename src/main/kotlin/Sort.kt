import kotlin.system.measureTimeMillis

fun main() = with(System.`in`.bufferedReader()) {

    val randoms = (1..1_000).toList().shuffled()
    val selectionSortTime = measureTimeMillis {
        selectionSort(randoms)
    }
    println("selectionSortTime: $selectionSortTime")

    val insertionSortTime = measureTimeMillis {
        insertionSort(randoms)
    }
    println("insertionSortTime: $insertionSortTime")

    val bubbleSortTime = measureTimeMillis {
        bubbleSort(randoms)
    }
    println("bubbleSortTime: $bubbleSortTime")

    val mergeSortTime = measureTimeMillis {
        mergeSort(randoms)
    }
    println("mergeSortTime: $mergeSortTime")

    val quickSortTime = measureTimeMillis {
        quickSort(randoms)
    }
    println("quickSortTime: $quickSortTime")

    val dualQuickSortTime = measureTimeMillis {
        dualQuickSort(randoms)
    }
    println("dualQuickSortTime: $dualQuickSortTime")

    val defaultSortTime = measureTimeMillis {
        randoms.sorted()
    }
    println("defaultSortTime: $defaultSortTime")
    println(randoms)
}

fun selectionSort(list: List<Int>): List<Int> {
    val result = list.toMutableList()
    for (i in result.indices) {
        var minIndex = i
        for (j in i + 1 until result.size) {
            if (result[j] < result[minIndex]) {
                minIndex = j
            }
        }
        // Swap
        if (i != minIndex) {
            val temp = result[i]
            result[i] = result[minIndex]
            result[minIndex] = temp
        }
    }
    return result
}

fun insertionSort(list: List<Int>): List<Int> {
    val result = list.toMutableList()
    for (i in 1 until result.size) {
        val key = result[i]
        var j = i - 1
        while (j >= 0 && result[j] > key) {
            result[j + 1] = result[j]
            j--
        }
        result[j + 1] = key
    }
    return result
}

fun bubbleSort(list: List<Int>): List<Int> {
    val result = list.toMutableList()
    for (i in 0 until result.size - 1) {
        for (j in 0 until result.size - 1 - i) {
            if (result[j] > result[j + 1]) {
                // Swap
                val temp = result[j]
                result[j] = result[j + 1]
                result[j + 1] = temp
            }
        }
    }
    return result
}

fun mergeSort(list: List<Int>): List<Int> {
    if (list.size <= 1) return list

    val middle = list.size / 2
    val left = mergeSort(list.subList(0, middle))
    val right = mergeSort(list.subList(middle, list.size))

    return merge(left, right)
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    var i = 0
    var j = 0

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            result.add(left[i])
            i++
        } else {
            result.add(right[j])
            j++
        }
    }
    result.addAll(left.subList(i, left.size))
    result.addAll(right.subList(j, right.size))

    return result
}

fun quickSort(array: List<Int>): List<Int> {
    if (array.size <= 1) return array

    val pivot = array.first()
    val left = array.filter { it < pivot }
    val middle = array.filter { it == pivot }
    val right = array.filter { it > pivot }

    return quickSort(left) + middle + quickSort(right)
}

fun dualQuickSort(array: List<Int>): List<Int> {
    if (array.size <= 1) return array

    val pivot1 = array.first()
    val pivot2 = array.last()

    val (leftPivot, rightPivot) = if (pivot1 > pivot2) pivot2 to pivot1 else pivot1 to pivot2
    val left = mutableListOf<Int>()
    val middle = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    for (i in 1 until array.size - 1) {
        val temp = array[i]
        when {
            temp < leftPivot -> left.add(temp)
            temp > rightPivot -> right.add(temp)
            else -> middle.add(temp)
        }
    }

    return dualQuickSort(left) + leftPivot + dualQuickSort(middle) + rightPivot + dualQuickSort(right)
}
