package com.mhemdan.popularactors.utils.extensions

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Ahmed Ghazy on 12/19/18.
 * https://github.com/afghazy
 * ahmedfathyghazy@gmail.com
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction?) {
    beginTransaction().func()?.commit()
}