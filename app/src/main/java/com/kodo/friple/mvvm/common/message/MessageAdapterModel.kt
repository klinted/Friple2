package com.kodo.friple.mvvm.common.message

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kodo.friple.R
import kotlinx.android.synthetic.main.message_item.view.*

@EpoxyModelClass(layout = R.layout.message_item)
abstract class MessageAdapterModel: EpoxyModelWithHolder<MessageAdapterModel.MessageAdapterHolder>() {

    @EpoxyAttribute
    var image: Int? = null

    @EpoxyAttribute
    var name: String? = null

    @EpoxyAttribute
    var message: String? = null

    @EpoxyAttribute
    var time: String? = null

    override fun bind(holder: MessageAdapterHolder) {
        holder.image.setImageResource(image!!)
        holder.name.text = name
        holder.message.text = message
        holder.time.text = time
    }

    inner class MessageAdapterHolder: EpoxyHolder() {

        lateinit var image: ImageView
        lateinit var name: TextView
        lateinit var message: TextView
        lateinit var time: TextView

        override fun bindView(itemView: View) {
            image = itemView.iv_message
            name = itemView.tv_name
            message = itemView.tv_message
            time = itemView.tv_time
        }

    }

}