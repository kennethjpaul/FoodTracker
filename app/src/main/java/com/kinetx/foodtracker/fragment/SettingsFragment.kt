package com.kinetx.foodtracker.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.kinetx.foodtracker.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}