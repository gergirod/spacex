package ger.girod.spacex.ui.main.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ger.girod.spacex.R
import kotlinx.android.synthetic.main.filter_bottom_sheet.*

class FilterBottomSheetDialogFragment(private val listener: Listener) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filter_by_success.setOnClickListener {
            listener.onFilterByLaunchSuccess()
        }
    }

    interface Listener {

        fun onFilterByLaunchSuccess()

    }

}