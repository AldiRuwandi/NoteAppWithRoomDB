package uz.context.notewithroom.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import uz.context.notewithroom.R
import uz.context.notewithroom.adapter.NoteAdapter
import uz.context.notewithroom.model.Notes
import java.text.SimpleDateFormat
import java.util.*

class NotesTakerActivity : AppCompatActivity() {
    private lateinit var editTitle: EditText
    private lateinit var editNote: EditText
    private lateinit var imageSave: ImageView
    private lateinit var imageBack: ImageView
    private lateinit var notes: Notes
    private lateinit var titleText: TextView
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "NotesTakerActivity"

    private var isOnlNote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_taker)
        loadInterstitalAd()
        admob()

        initViews()

    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {

        editNote = findViewById(R.id.editText_notes)
        editTitle = findViewById(R.id.editText_title)
        imageSave = findViewById(R.id.imageView_save)
        imageBack = findViewById(R.id.back_image)
        titleText = findViewById(R.id.title_text)

        imageBack.setOnClickListener {
            finish()
        }

        notes = Notes()

        try {
            notes = intent.getSerializableExtra("old_note") as Notes
            editTitle.setText(notes.title)
            editNote.setText(notes.note)
            titleText.text = notes.title
            isOnlNote = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        imageSave.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val des = editNote.text.toString().trim()

            if (des.isEmpty()) {
                val snackBar = Snackbar.make(it, "Please enter some notes!", Snackbar.LENGTH_LONG)
                snackBar.show()
                return@setOnClickListener
            }
            val simpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
            val date = Date()

            if (!isOnlNote) {
                notes = Notes()
            }
            notes.title = title
            notes.note = des
            notes.date = simpleDateFormat.format(date)

            val intent = Intent()
            intent.putExtra("note", notes)
            setResult(Activity.RESULT_OK, intent)

            finish()
            showInterstitalAd()
        }
    }

    private fun admob() {

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("TEST_ID_1", "TEST_ID_N"))
                .build()
        )

    }

    private fun loadInterstitalAd() {
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, resources.getString(R.string.interstital_ad), adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    mInterstitialAd = p0
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    mInterstitialAd = null
                }
            })
    }

    private fun showInterstitalAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd!!.setFullScreenContentCallback(object : FullScreenContentCallback() {

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    mInterstitialAd = null
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                    loadInterstitalAd()
                }

            })
            mInterstitialAd!!.show(this)
        }
    }
}