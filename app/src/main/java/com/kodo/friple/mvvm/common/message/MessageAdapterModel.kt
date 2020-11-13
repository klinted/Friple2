package com.kodo.friple.mvvm.common.message

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.kodo.friple.R
import com.kodo.friple.mvvm.common.SampleApplication
import kotlinx.android.synthetic.main.message_item.view.*
import javax.inject.Inject


@EpoxyModelClass(layout = R.layout.message_item)
abstract class MessageAdapterModel: EpoxyModelWithHolder<MessageAdapterModel.MessageAdapterHolder>() {

    lateinit var context: Context

    @EpoxyAttribute
    var imageUri: Uri? = null

    @EpoxyAttribute
    var name: String? = null

    @EpoxyAttribute
    var whoSent: Boolean = false

    @EpoxyAttribute
    var message: String? = null

    @EpoxyAttribute
    var time: String? = null

    @EpoxyAttribute
    var online: Boolean = false

    override fun bind(holder: MessageAdapterHolder) {

        context = holder.image.context

        holder.name.text = name
        holder.message.text = message
        holder.time.text = time

        if (whoSent){
            holder.whoSent.text = "You: "
        } else {
            holder.whoSent.text = ""
        }

        val roundingParams = RoundingParams.fromCornersRadius(70f)

        if (online){
        } else {
        }
        holder.image.hierarchy = GenericDraweeHierarchyBuilder(context.resources)
            .setRoundingParams(roundingParams)
            .build()
        holder.image.setImageURI(imageUri!!, context)
    }

    inner class MessageAdapterHolder: EpoxyHolder() {

        lateinit var image: SimpleDraweeView
        lateinit var name: TextView
        lateinit var whoSent: TextView
        lateinit var message: TextView
        lateinit var time: TextView

        override fun bindView(itemView: View) {
            image = itemView.iv_message
            name = itemView.tv_name
            whoSent = itemView.tv_who_sent
            message = itemView.tv_message
            time = itemView.tv_time
        }

    }

}