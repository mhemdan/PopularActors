package com.mhemdan.popularactors.util.extension

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import  com.mhemdan.popularactors.utils.extensions.inTransaction


/**
 * Created by Ahmed Ghazy on 12/19/18.
 * https://github.com/afghazy
 * ahmedfathyghazy@gmail.com
 */

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.addFragmentWithBackStack(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment)
        addToBackStack(fragment.javaClass.simpleName)}
}

fun AppCompatActivity.addFragment(fragment: DialogFragment) {
    supportFragmentManager.inTransaction { add(fragment, fragment.javaClass.simpleName) }
}

fun AppCompatActivity.findFragment(fragmentTag: String): Fragment? = supportFragmentManager.findFragmentByTag(fragmentTag)

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.removeFragment(tag: String) {
    supportFragmentManager.inTransaction { supportFragmentManager.findFragmentByTag(tag)?.let { remove(it) } }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { remove(fragment) }
}

fun <T> AppCompatActivity.runOnUi(delay: Long, f: () -> T) {
    Handler().postDelayed({ f() }, delay)
}