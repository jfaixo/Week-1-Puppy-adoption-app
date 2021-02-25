package com.example.androiddevchallenge.repositories

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.Puppy

class PuppyRepository {

    private val puppies = hashMapOf(
        0 to Puppy(0, "Daniel", R.drawable.daniel),
        1 to Puppy(1, "Bernie", R.drawable.bernie),
        2 to Puppy(2, "Big Brother", R.drawable.bigbrother),
        3 to Puppy(3, "Birdy", R.drawable.birdy),
        4 to Puppy(4, "Buck", R.drawable.buck),
        5 to Puppy(5, "Bugs", R.drawable.bugs),
        6 to Puppy(6, "Doug", R.drawable.doug),
        7 to Puppy(7, "Mary", R.drawable.mary),
        8 to Puppy(8, "RocknRolla", R.drawable.rocknrolla),
        9 to Puppy(9, "Rocky", R.drawable.rocky),
        10 to Puppy(10, "The Rock", R.drawable.therock),
    )

    fun getAll() = this.puppies.values.toList()

    fun get(id: Int) = puppies[id]!!
}