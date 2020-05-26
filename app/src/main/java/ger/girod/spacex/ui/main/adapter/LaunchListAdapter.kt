package ger.girod.spacex.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ger.girod.spacex.R
import ger.girod.spacex.ui.models.*
import kotlinx.android.synthetic.main.header_row.view.*
import kotlinx.android.synthetic.main.launch_row.view.*

class LaunchListAdapter(private val onRowClick: OnRowClick) :
    RecyclerView.Adapter<BaseViewHolder<*>>(), Filterable {

    private var launchList: ArrayList<ListItem> = ArrayList()
    private var filterList: ArrayList<ListItem> = ArrayList()

    fun setList(launchList: ArrayList<ListItem>) {
        this.launchList.clear()
        this.launchList.addAll(launchList)
        filterList.addAll(launchList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        return when (viewType) {
            HEADER_TYPE -> {
                var view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_row, parent, false)
                HeaderHolder(view)
            }
            GENERAL_TYPE -> {
                var view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.launch_row, parent, false)
                LaunchHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemCount(): Int {
        return launchList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        val item = launchList[position]
        when (holder) {
            is HeaderHolder -> holder.bind(item as HeaderListItem)
            is LaunchHolder -> holder.bind(item as LaunchListItem)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return launchList[position].getType()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

    inner class HeaderHolder(itemView: View) : BaseViewHolder<HeaderListItem>(itemView) {

        override fun bind(item: HeaderListItem) {
            itemView.header.text = item.getHeader()
        }

    }

    inner class LaunchHolder(itemView: View) : BaseViewHolder<LaunchListItem>(itemView) {

        override fun bind(item: LaunchListItem) {
            itemView.image.load(item.getLaunch().links.missionPatchSmall)
            itemView.mission_name.text = item.getLaunch().missionName
            itemView.mission_detail.text = item.getLaunch().details
            itemView.mission_status.text = item.getLaunch().getSuccessString()

            when (item.getLaunch().launchSuccess) {
                true -> itemView.mission_status.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.green
                    )
                )
                false -> itemView.mission_status.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.red
                    )
                )
            }

            itemView.setOnClickListener {
                onRowClick.onRowClicked(
                    item.getLaunch().flightNumber,
                    item.getLaunch().rocket.rocketId
                )
            }
        }

    }

    interface OnRowClick {
        fun onRowClicked(flightNumber: Int, rocketId: String)
    }
}