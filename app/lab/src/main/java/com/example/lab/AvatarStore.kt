package com.example.lab

object AvatarStore {
    val avatar = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_tag_faces_24
    )
    fun random () = avatar.random()
}