package com.demo.movieapp.common.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.demo.movieapp.R
import com.demo.movieapp.common.network.RetrofitException
import com.demo.movieapp.databinding.DialogPopupErrorBinding

object DialogUtils {

    fun showGenericErrorPopup(
        context: Context,
        retryListener: () -> Unit,
        cancelListener: () -> Unit?,
        isCancelable: Boolean = false,
        exception: RetrofitException? = null,
        message: String? = null
    ): AlertDialog {
        val layout: DialogPopupErrorBinding =
            DialogPopupErrorBinding.inflate(LayoutInflater.from(context))
        val alertDialog = AlertDialog.Builder(context).apply {
            setView(layout.root)
            setCancelable(false)
        }.create()

        alertDialog.apply {
            exception?.let {
                layout.ivError.setImageResource(it.imageResourceId)
                layout.tvError.text = context.getString(it.titleMessageResourceId)
                layout.tvMessageError.text = it.message
            }
            if (exception?.kind == RetrofitException.Kind.NETWORK)
                layout.tvMessageError.text = context.getString(R.string.no_internet_connection_error)
            else
                layout.tvMessageError.text = exception?.message ?: message ?: ""

            layout.btnErrorRetry.setOnClickListener {
                retryListener.invoke()
                dismiss()
            }


            if (isCancelable) {
                layout.apply {
                    btnErrorCancel.setOnClickListener {
                        dismiss()
                        cancelListener.invoke()
                    }
                    btnErrorCancel.visibility = View.VISIBLE
                }
            } else {
                layout.btnErrorCancel.visibility = View.GONE
            }
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }.show()

        return alertDialog

    }

}