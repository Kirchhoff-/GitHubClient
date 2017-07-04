package com.kirchhoff.example.githubclient.ui.general

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View

import com.kirchhoff.example.githubclient.R

/**
 * @author Kirchhoff-
 */

class LoadingDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
                .setView(View.inflate(activity, R.layout.d_loading, null))
                .create()
    }


}
