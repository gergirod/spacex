package ger.girod.spacex.ui.main.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ger.girod.spacex.R
import kotlinx.android.synthetic.main.sort_bottom_sheet.*

class SortBottomSheetDialogFragment(private val listener: Listener) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sort_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sort_by_date.setOnClickListener {
            listener.onSortByDate()
        }

        sort_by_mission_name.setOnClickListener {
            listener.onSortByMissionName()
        }

        sort_by_mission_name_desc.setOnClickListener {
            listener.onSortByMissionNameDesc()
        }

        sort_by_date_desc.setOnClickListener {
            listener.onSortByDateDesc()
        }
    }

    interface Listener {

        fun onSortByDate()

        fun onSortByDateDesc()

        fun onSortByMissionName()

        fun onSortByMissionNameDesc()

    }

}