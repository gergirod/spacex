package ger.girod.spacex.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import ger.girod.spacex.R
import ger.girod.spacex.domain.models.launch.LaunchModel
import ger.girod.spacex.domain.models.rocket.RocketOneModel
import ger.girod.spacex.ui.utils.ScreenState
import ger.girod.spacex.util.IntentUtils
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.progress_bar
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException


class DetailFragment : Fragment() {

    companion object {
        fun newInstance(flightNumber: Int, rocketId: String?): DetailFragment {
            val args: Bundle = Bundle().apply {
                putInt(FLIGHT_NUMBER, flightNumber)
                putString(ROCKET_ID, rocketId)
            }
            return DetailFragment().apply {
                arguments = args
            }
        }
    }

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        observeLiveData()
        detailViewModel.getLaunchDetails(
            arguments?.getInt(FLIGHT_NUMBER), arguments?.getString(
                ROCKET_ID
            )
        )
    }

    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun observeLiveData() {

        detailViewModel.screenData.observe(viewLifecycleOwner, Observer {
            when (it) {
                ScreenState.Loading -> progress_bar.visibility = View.VISIBLE
                ScreenState.LoadingFinish -> progress_bar.visibility = View.GONE
                else -> IllegalArgumentException("")
            }
        })

        detailViewModel.detailData.observe(viewLifecycleOwner, Observer {
            it.launchModel?.let {
                populateLaunchData(it)
            }
            it.rocketOneModel?.let {
                populateRocketData(it)
            }
        })

        detailViewModel.errorData.observe(viewLifecycleOwner, Observer {
        })

    }

    private fun populateRocketData(rocketOneModel: RocketOneModel) {
        rocket_card.visibility = View.VISIBLE
        rocket_title.visibility = View.VISIBLE
        country.text = String.format(resources.getString(R.string.country), rocketOneModel.country)
        company.text = String.format(resources.getString(R.string.company), rocketOneModel.company)
        rocket_details.text =
            String.format(resources.getString(R.string.description), rocketOneModel.description)
        more.setOnClickListener {
            goToWikipediaPage(rocketOneModel.wikipedia)
        }
    }

    private fun goToWikipediaPage(wikiLink: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(wikiLink)
        }

        if (IntentUtils.isIntenteSafe(activity, intent)) {
            startActivity(intent)
        } else {
            showErrorMessage(getString(R.string.not_available))
        }
        startActivity(intent)
    }

    private fun populateLaunchData(launchModel: LaunchModel) {
        launch_card.visibility = View.VISIBLE
        image.load(launchModel.links.missionPatch)
        mission_name.text = launchModel.missionName
        date.text =
            String.format(resources.getString(R.string.launch_date), launchModel.getStringDate())
        from.text = String.format(
            resources.getString(R.string.from_site),
            launchModel.launchSite.siteNameLong
        )
        details.text = String.format(resources.getString(R.string.details), launchModel.details)
        status.text = launchModel.getSuccessString()

        when (launchModel.launchSuccess) {
            true -> status.setTextColor(ContextCompat.getColorStateList(context!!, R.color.green))
            false -> status.setTextColor(ContextCompat.getColorStateList(context!!, R.color.red))
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        Snackbar.make(main, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.finish()
        }
        return true
    }
}