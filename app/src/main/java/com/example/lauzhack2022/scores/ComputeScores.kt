package com.example.lauzhack2022.scores

class ComputeScores {
    companion object {
        private var minEmission = 0;
        private var maxEmission = 40;
        private var emissionsRange: IntRange = minEmission..maxEmission
        private var scoreRange: IntRange = 0..100
        fun Compute(emissions: List<Int>) : Int  {
            return emissions.map { a -> FromEmissionToScore(a) }.average().toInt();
        }

        private fun FromEmissionToScore(emission: Int): Int {
            val ratio = emission.toFloat() / (emissionsRange.endInclusive - emissionsRange.start)
            return (ratio * (scoreRange.endInclusive - scoreRange.start)).toInt()
        }


    }


}