package com.example.sakura.toDo.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sakura.R
import com.example.sakura.databinding.FragmentOpenBinding
import com.example.sakura.toDo.calendar.CalendarFragment
import com.example.sakura.toDo.luis.LuisFragment

class HomeFragment: Fragment(){
    private var _binding: FragmentOpenBinding? =  null
    private val binding get() = _binding!!
    private lateinit var luisButton: TextView
    private lateinit var faceReaderButton: TextView
    private lateinit var calendarButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        luisButton = binding.luisTaskTextButton
        faceReaderButton = binding.frTaskTextButton
        calendarButton = binding.calendarTextButton
        luisButton.setOnClickListener{
            inflateLUISLayout()
        }
        faceReaderButton.setOnClickListener{
            inflateFaceReaderLayout()
        }
        calendarButton.setOnClickListener{
            inflateCalendarLayout()
        }
    }


    private fun inflateLUISLayout()
    {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, LuisFragment())
        transaction.commit()
    }

    private fun inflateFaceReaderLayout()
    {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://stream.facereader-online.com/experiment?ExperimentID=584a11bfca264282a521b1aecb142344"))
            startActivity(browserIntent)
        } catch (e:Exception){
            Log.d("Camera", "No application can handle this request." + " Please install a webbrowser")
        }
        /* val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, FaceReaderFragment())
        transaction.commit()*/
    }

    private fun inflateCalendarLayout()
    {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, CalendarFragment())
        transaction.commit()
    }
}
