package ger.girod.spacex.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import ger.girod.spacex.R
import ger.girod.spacex.ui.detail.DetailActivity
import ger.girod.spacex.ui.main.adapter.LaunchListAdapter
import ger.girod.spacex.ui.main.bottom_sheet.FilterBottomSheetDialogFragment
import ger.girod.spacex.ui.main.bottom_sheet.SortBottomSheetDialogFragment
import ger.girod.spacex.ui.models.ListItem
import ger.girod.spacex.ui.utils.ScreenState
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class MainFragment : Fragment(), LaunchListAdapter.OnRowClick,
    SortBottomSheetDialogFragment.Listener, FilterBottomSheetDialogFragment.Listener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var sortBottomSheetDialogFragment: BottomSheetDialogFragment
    private lateinit var filterBottomSheetDialogFragment: BottomSheetDialogFragment
    private val mainViewModel: MainViewModel by viewModel()
    private val listAdapter: LaunchListAdapter by lazy {
        LaunchListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        mainViewModel.getLaunches()

        observeLiveData()

        fab.setOnClickListener {
            showFilterBottomSheet()
        }

        sort_icon.setOnClickListener {
            showSortBottomSheet()
        }

        remove_filter.setOnClickListener {
            mainViewModel.removeFilter()
        }
    }

    private fun showSortBottomSheet() {

        sortBottomSheetDialogFragment = SortBottomSheetDialogFragment(this)
        sortBottomSheetDialogFragment.show(parentFragmentManager, sortBottomSheetDialogFragment.tag)

    }

    private fun showFilterBottomSheet() {
        filterBottomSheetDialogFragment = FilterBottomSheetDialogFragment(this)
        filterBottomSheetDialogFragment.show(
            parentFragmentManager,
            filterBottomSheetDialogFragment.tag
        )
    }

    private fun initList() {

        list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        list.layoutManager = layoutManager
        list.adapter = listAdapter

    }

    private fun observeLiveData() {

        mainViewModel.launchData.observe(viewLifecycleOwner, Observer {
            listAdapter.setList(it as ArrayList<ListItem>)
        })

        mainViewModel.errorData.observe(viewLifecycleOwner, Observer {
            showErrorMessage(it)
        })

        mainViewModel.screenStateData.observe(viewLifecycleOwner, Observer {
            when (it) {
                ScreenState.LoadingFinish -> progress_bar.visibility = View.GONE
                ScreenState.Loading -> progress_bar.visibility = View.VISIBLE
                else -> IllegalArgumentException("")
            }
        })

        mainViewModel.removeFilterdata.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> remove_filter.visibility = View.VISIBLE
                false -> remove_filter.visibility = View.GONE
            }
        })
    }

    private fun showErrorMessage(errorMessage: String) {
        Snackbar.make(main, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onRowClicked(flightNumber: Int, rocketId: String) {
        startActivity(DetailActivity.getIntent(activity!!, flightNumber, rocketId))
    }

    override fun onSortByDate() {
        dismissSortBottomSheet()
        mainViewModel.sortByLaunchDate()
    }

    override fun onSortByMissionName() {
        dismissSortBottomSheet()
        mainViewModel.sortByMissionName()
    }

    override fun onSortByDateDesc() {
        dismissSortBottomSheet()
        mainViewModel.sortByDescendingDate()
    }

    override fun onSortByMissionNameDesc() {
        dismissSortBottomSheet()
        mainViewModel.sortByMissionNameDescending()
    }

    override fun onFilterByLaunchSuccess() {
        dismissFilterBottomSheet()
        mainViewModel.filterByLaunchSuccess()
    }

    private fun dismissFilterBottomSheet() {
        filterBottomSheetDialogFragment.dismiss()
    }

    private fun dismissSortBottomSheet() {
        sortBottomSheetDialogFragment.dismiss()
    }

}
