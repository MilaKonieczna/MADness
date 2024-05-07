package com.example.madness.notifications

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.madness.R
import java.text.SimpleDateFormat
import java.util.*

class Notifications : DialogFragment() {

    private lateinit var hour: TextView
    private lateinit var day: TextView
    private var listener: NotificationsListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        day = view.findViewById(R.id.date)
        hour = view.findViewById(R.id.time)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

        day.text = dateFormat.format(calendar.time)
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 0)
        hour.text = timeFormat.format(calendar.time)

        day.setOnClickListener {
            showDatePicker()
        }

        hour.setOnClickListener {
            showTimePicker()
        }

        view.findViewById<TextView>(R.id.save).setOnClickListener {
            onSaveButtonClicked()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            val dateFormat = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)

            day.text = formattedDate

            val selectedTime = hour.text.toString()
            listener?.onDateTimeSelected(formattedDate, selectedTime)
            Toast.makeText(requireContext(), formattedDate, Toast.LENGTH_SHORT).show()
        },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 0)

        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            hour.text = selectedTime
            val selectedDate = day.text.toString()
            listener?.onDateTimeSelected(selectedDate, selectedTime)
        },
            hourOfDay,
            minute,
            true
        )
        timePickerDialog.show()
    }

    private fun onSaveButtonClicked() {
        val selectedDate = day.text.toString()
        val selectedTime = hour.text.toString()
        listener?.onDateTimeSelected(selectedDate, selectedTime)
        dismiss() // Dismiss the dialog after saving data
    }

    interface NotificationsListener {
        fun onDateTimeSelected(selectedDate: String, selectedTime: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }
}
