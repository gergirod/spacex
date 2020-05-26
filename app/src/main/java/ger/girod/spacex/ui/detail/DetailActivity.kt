package ger.girod.spacex.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ger.girod.spacex.R

const val FLIGHT_NUMBER = "fligh_number"
const val ROCKET_ID = "rocker_id"

class DetailActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context, flightNumber: Int, rocketId: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(FLIGHT_NUMBER, flightNumber)
                putExtra(ROCKET_ID, rocketId)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val flightNumber = intent.getIntExtra(FLIGHT_NUMBER, -1)
            val rocketId = intent.getStringExtra(ROCKET_ID)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(flightNumber, rocketId))
                .commitNow()
        }
    }

}
