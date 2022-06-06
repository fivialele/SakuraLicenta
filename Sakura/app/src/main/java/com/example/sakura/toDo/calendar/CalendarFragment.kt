package com.example.sakura.toDo.calendar

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.sakura.R
import com.example.sakura.databinding.FragmentCalendarBinding
import com.example.sakura.toDo.home.HomeFragment
import android.database.sqlite.SQLiteDatabase
import android.util.Log

import android.widget.CalendarView
import android.widget.TextView


class CalendarFragment: Fragment() {
    private var _binding: FragmentCalendarBinding? =  null
    private val binding get() = _binding!!
    private lateinit var menuButton: ImageView
    private lateinit var refreshButton: ImageView
    private lateinit var dbHandler: MySQLiteDBHandler
    private lateinit var editText: TextView
    private lateinit var calendarView: CalendarView
    private lateinit var selectedDate: String
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private var year1: Int = 0
    private var month1: Int = 0
    private var day1: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuButton = binding.menuButton
        refreshButton = binding.refreshButton
        menuButton.setOnClickListener{
            inflateHomeLayout()
        }

        refreshButton.setOnClickListener{
            /*insertDatabase(view)*/
            readDatabase(view)
        }

        editText = binding.textView
        calendarView = binding.calendarView
        calendarView.setOnDateChangeListener {
                _, i, i1, i2 ->
            year1 = i
            month1 = i1+1
            day1 = i2
            selectedDate = year1.toString()+month1.toString()+day1.toString()
            readDatabase(view)
        }

        try{

            dbHandler = MySQLiteDBHandler(this.context, "CalendarDatabase", null,1)
            sqLiteDatabase = dbHandler.writableDatabase
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)")
        }
        catch (e:Exception ){
            Log.d("SQLite","Issue when creating table")
        }
    }

    private fun insertDatabase(view: View){
        val contentValues = ContentValues()
        contentValues.put("Date",selectedDate)
        contentValues.put("Event", editText.text.toString())
        sqLiteDatabase.insert("EventCalendar", null, contentValues)

    }

    @SuppressLint("Recycle")
    private fun readDatabase(view: View){
        val query = "Select Event from EventCalendar where Date = $selectedDate"
        try{
            val cursor = sqLiteDatabase.rawQuery(query, null)
            cursor.moveToFirst()
            editText.text = cursor.getString(0)
        }
        catch (e: Exception){
            Log.d("SQLite","Issue when reading table")
            editText.text = ""
        }
    }

    private fun inflateHomeLayout()
    {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.commit()
    }
}