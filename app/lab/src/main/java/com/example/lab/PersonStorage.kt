package com.example.lab

import android.content.Context

object PersonStorage {
    fun rad (context: Context) = MutableList(50){
        Person(
            it,
            AvatarStore.random(),
            PersonDataStorage.random(),
            NameStorage.random(),
        )
    }
}