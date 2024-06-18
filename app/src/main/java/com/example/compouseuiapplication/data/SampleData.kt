package com.example.compouseuiapplication.data

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    val lessonlist = listOf(LessonInfo(LessonInfo.LESSON_1_COMPOSABLE,"第 1 課：自定義樣式"),
                            LessonInfo(LessonInfo.LESSON_2_INLINE,"第 2 課：版面配置"),
                            LessonInfo(LessonInfo.LESSON_3_THEME,"第 3 課：Surface介紹"),
                            LessonInfo(LessonInfo.LESSON_4_LAZY_COLUMN,"第 4 課：列表展示"),
                            LessonInfo(LessonInfo.LESSON_5_REMEMBER_MUTABLE,"第 5 課：remember和mutableStateOf"),
                            LessonInfo(LessonInfo.LESSON_6_CUSTOME_BTN,"第 6 課：客製化按鈕"),
                            LessonInfo(LessonInfo.LESSON_ADV_REQ_VIEWMODEL,"進階：Compose中的ViewModel"),
                            LessonInfo(LessonInfo.LESSON_ADV_EFFECT,"進階：Effect"))
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}
