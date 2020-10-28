package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.ChatsScreenBinding
import com.kodo.friple.mvvm.common.message.MessageData
import com.kodo.friple.mvvm.common.message.messageAdapter
import com.kodo.friple.mvvm.viewmodel.ChatsViewModel
import kotlinx.android.synthetic.main.chats_screen.*

class ChatsView: Fragment() {

    lateinit var mChatsViewModel: ChatsViewModel
    lateinit var binding: ChatsScreenBinding

    private var messages = mutableListOf<MessageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.chats_screen, container,false)
        mChatsViewModel = ViewModelProvider(this).get(ChatsViewModel::class.java)
        binding.viewModel = mChatsViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        messages.add(MessageData(R.drawable.ic_profile, "Nick", "You: Hi bud!", "5:55 PM"))
        messages.add(MessageData(R.drawable.ic_profile, "Dan", "What's up?", "4:47 PM"))
        messages.add(MessageData(R.drawable.ic_profile, "Kris", "You: I love u babe!", "4:40 PM"))
        messages.add(MessageData(R.drawable.ic_profile, "Vas", "Be in touch", "3:20 PM"))
        messages.add(MessageData(R.drawable.ic_profile, "Richard", "Ok", "2:17 PM"))
        messages.add(MessageData(R.drawable.ic_profile, "Ron", "You: Hahhah", "OCT 22"))
        messages.add(MessageData(R.drawable.ic_profile, "Gerry", "You: I don't think so", "OCT 22"))
        messages.add(MessageData(R.drawable.ic_profile, "Bob", "Good luck", "AUG 17"))
        messages.add(MessageData(R.drawable.ic_profile, "Stan", "Hey", "AUG 11"))

        epoxy_recycler_view.withModels {
            messages.forEach{
                messageAdapter{
                    id(hashCode())
                    image(it.image)
                    name(it.name)
                    message(it.message)
                    time(it.time)
                }
            }
        }
    }

}