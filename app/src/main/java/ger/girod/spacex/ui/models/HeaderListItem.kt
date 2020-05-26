package ger.girod.spacex.ui.models

data class HeaderListItem(private val header: String) : ListItem() {

    override fun getType(): Int {
        return HEADER_TYPE
    }

    fun getHeader(): String {
        return header
    }
}