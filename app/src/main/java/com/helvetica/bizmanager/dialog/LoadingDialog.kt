package com.helvetica.bizmanager.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import com.helvetica.bizmanager.R

class LoadingDialog(u_activity: Activity) {
    private var activity: Activity
    private lateinit var dialog: AlertDialog

    init {
        activity = u_activity
    }

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }
    fun dismissDialog(){
        dialog.dismiss()
    }

}