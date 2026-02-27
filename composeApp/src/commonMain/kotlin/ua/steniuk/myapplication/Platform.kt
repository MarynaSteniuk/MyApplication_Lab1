package ua.steniuk.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform