package com.example.compouseuiapplication.data

data class LessonInfo(val iLessonId: Int, val strLessonTitle: String){
    companion object {
        const val LESSON_1_COMPOSABLE = 1
        const val LESSON_2_INLINE = 2
        const val LESSON_3_THEME = 3
        const val LESSON_4_LAZY_COLUMN = 4
        const val LESSON_5_REMEMBER_MUTABLE= 5
        const val LESSON_ADV_REQ_VIEWMODEL= 6
        const val LESSON_6_CUSTOME_BTN = 7
    }
}