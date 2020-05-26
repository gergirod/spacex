package ger.girod.spacex.ui.main.adapter

import android.widget.Filter
import ger.girod.spacex.ui.models.HeaderListItem
import ger.girod.spacex.ui.models.LaunchListItem
import ger.girod.spacex.ui.models.ListItem

class MyFilter(
    private var originalList: ArrayList<ListItem>,
    private val adapter: LaunchListAdapter
) : Filter() {
    override fun performFiltering(filterBy: CharSequence?): FilterResults {
        if (filterBy.isNullOrEmpty()) {

        }
        var filteringList: ArrayList<ListItem> = ArrayList()
        for (item in originalList) {
            when (item) {
                is LaunchListItem -> {
                    if (item.getLaunch().launchSuccess) {
                        filteringList.add(item)
                    }
                }
                is HeaderListItem -> {
                    filteringList.add(item)
                }
            }
        }

        val filteredResults = FilterResults()
        filteredResults.values = filteringList

        return filteredResults

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        originalList.clear()
        originalList.addAll(results!!.values as List<ListItem>)
        adapter.notifyDataSetChanged()
    }
}