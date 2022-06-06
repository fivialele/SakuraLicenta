package com.example.sakura.toDo.luis

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sakura.R
import com.example.sakura.databinding.FragmentLuisBinding
import com.example.sakura.databinding.FragmentOpenBinding
import com.example.sakura.toDo.home.HomeFragment

class LuisFragment: Fragment() {
    private var _binding: FragmentLuisBinding? =  null
    private val binding get() = _binding!!
    private lateinit var menuButton: ImageView
    private var url = "<iframe src='https://webchat.botframework.com/embed/sakura-try-bot?s=Lcow5p0uTXA.RSggAZZ6iIpoZtmvOTQNFJ3CXeHrBa-3jFKEz1oD8Wc'  style='min-width: 400px; width: 100%; min-height: 500px;'></iframe>"
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuisBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = binding.webViewChatbot
        webView.settings.javaScriptEnabled = true;
        webView.webViewClient = WebViewClient()
        webView.loadData(url, "text/html", null)

        menuButton = binding.menuButton
        menuButton.setOnClickListener{
            inflateHomeLayout()
        }
    }

    private fun inflateHomeLayout()
    {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.commit()
    }

//    companion object {
//        lateinit var user:String
//        const val botUser = "bot"
//    }
}