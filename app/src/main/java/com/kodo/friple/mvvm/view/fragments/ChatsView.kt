package com.kodo.friple.mvvm.view.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.ChatsScreenBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.SampleApplication
import com.kodo.friple.mvvm.common.message.MessageData
import com.kodo.friple.mvvm.common.message.messageAdapter
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.ChatsViewModel
import kotlinx.android.synthetic.main.chats_screen.*

class ChatsView: Fragment(), BackButtonListener {

    lateinit var mChatsViewModel: ChatsViewModel
    lateinit var binding: ChatsScreenBinding

    private var messages = mutableListOf<MessageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.chats_screen, container,false)

        val viewModelFactory = MyViewModelFactory((parentFragment as RouterProvider).router)
        mChatsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChatsViewModel::class.java)

        binding.viewModel = mChatsViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val uri = mapOf(
            1 to Uri.parse("https://sun9-65.userapi.com/4sDLUQDn7pFnnxek3301ivZ_4bKw55RGK0owVw/xpFsKSRNxfo.jpg"),
            2 to Uri.parse("https://sun1.beeline-kz.userapi.com/c831209/v831209882/1b0bd5/NbNTu45tcG8.jpg"),
            3 to Uri.parse("https://sun9-35.userapi.com/DiX10Reb8nTlutw5fRRYd-4OnII-aJQalv1wUQ/aJl4-S6rQLM.jpg"),
            4 to Uri.parse("https://sun9-2.userapi.com/WII6xdQ7zhjpzAXhaij-HvD6InKijYOiz8etqA/InwuALn9738.jpg"),
            5 to Uri.parse("https://static.hollywoodreporter.com/sites/default/files/2016/04/richard_hendricks_screen_shot.jpg"),
            6 to Uri.parse("https://sun9-52.userapi.com/EgJjWbjMrP54UdP9U89ZfHQci0Gaf6BEiNqlvA/p74M1BbgAho.jpg"),
            7 to Uri.parse("https://soci.cms.arts.ubc.ca/wp-content/uploads/sites/3/2017/08/cropped-Gerry-Veenstra.jpg"),
            8 to Uri.parse("https://ionehiphopwired.files.wordpress.com/2019/10/15700332151172.jpg?quality=85&strip=all"),
            9 to Uri.parse("https://defendernetwork.com/wp-content/uploads/2020/06/HFD.jpg")
        )

        messages.add(MessageData(uri[1], "Nick",true, "Bud! Where you were?? Ar...  ", "57m",true))
        messages.add(MessageData(uri[2], "Dan",false,"What's up?", "2h",false))
        messages.add(MessageData(uri[3], "Kris",true,"Sup?", "5h", false))
        messages.add(MessageData(uri[4], "Vas",false,"Be in touch, please. Okay?", "5h",false))
        messages.add(MessageData(uri[5], "Richard",false,"Ok", "1d",true))
        messages.add(MessageData(uri[6], "Ron",true, "Hahhah", "1d",false))
        messages.add(MessageData(uri[7], "Gerry",true, "I don't think so", "2d",true))
        messages.add(MessageData(uri[8], "Bob",false,"I'm busy. Have a nice day, man!", "2d",true))
        messages.add(MessageData(uri[9], "Stan",false,"Hey", "2d",true))

        epoxy_recycler_view.withModels {
            messages.forEach{
                messageAdapter{
                    id(hashCode())
                    imageUri(it.imageUri)
                    name(it.name)
                    whoSent(it.whoSent)
                    message(it.message)
                    time(it.time)
                    online(it.online)
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        mChatsViewModel.onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): ChatsView {
            return ChatsView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }
}