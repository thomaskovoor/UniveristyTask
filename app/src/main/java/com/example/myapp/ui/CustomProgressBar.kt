package com.example.myapp.ui

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity

class CustomProgressBar(var activity: MainActivity) {
    var dialog: Dialog? = null

    fun  showDialog(){
        dialog = Dialog(activity!!)
        dialog!!.setContentView(com.example.myapp.R.layout.progressdialog)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog!!.show()
        dialog!!.setCancelable(false)
    }

    fun dismissDialog(){
        if(dialog == null){
            dialog = Dialog(activity!!)
        }
        dialog!!.dismiss()
        dialog!!.setCanceledOnTouchOutside(true)
    }

    fun getVisibility(): Boolean {
        return dialog!!.isShowing
    }
}