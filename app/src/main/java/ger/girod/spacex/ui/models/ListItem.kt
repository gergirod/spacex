package ger.girod.spacex.ui.models

const val HEADER_TYPE = 0
const val GENERAL_TYPE = 1

abstract class ListItem {
    abstract fun getType(): Int
}