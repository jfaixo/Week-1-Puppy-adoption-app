/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
