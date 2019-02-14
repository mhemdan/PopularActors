package com.mhemdan.popularactors.utils.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Ahmed Ghazy on 12/19/18.
 * https://github.com/afghazy
 * ahmedfathyghazy@gmail.com
 */

inline fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }