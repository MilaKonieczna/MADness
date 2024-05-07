package com.example.madness

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.madness.notifications.Notifications
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class Add : DialogFragment(), Notifications.NotificationsListener {

    private lateinit var dateDisplay: TextView
    private lateinit var notifyDisplay: TextView
    private lateinit var dropdownMenu: Spinner
    private lateinit var saveButton: Button
    private var isVaccineChosen = false
    private lateinit var name: String
    private lateinit var appDate: String
    private lateinit var notifyDate: String
    private lateinit var simpleFormat: SimpleDateFormat
    private lateinit var saveFormat: SimpleDateFormat
    private lateinit var simpleDateTimeFormat: SimpleDateFormat
    private lateinit var saveDateTimeFormat: SimpleDateFormat

    //TODO: fix the sending between the dialogs
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add, container, false)

        dropdownMenu = view.findViewById(R.id.dropdownMenu)
        val setDate = view.findViewById<LinearLayout>(R.id.setDate)
        val setAlarm = view.findViewById<LinearLayout>(R.id.SetAlarm)
        saveButton = view.findViewById(R.id.button)
        dateDisplay = view.findViewById(R.id.date)
        notifyDisplay = view.findViewById(R.id.add_notif)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_YEAR, 1)
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        simpleFormat = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
        saveFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        simpleDateTimeFormat = SimpleDateFormat("EEE, MMM dd, yyyy hh:mm a", Locale.getDefault())
        saveDateTimeFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())

        val formattedDate = simpleFormat.format(calendar.time)
        dateDisplay.text = formattedDate

        calendar.add(Calendar.DAY_OF_YEAR, -1)
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 0)
        notifyDate = saveDateTimeFormat.format(calendar.time)
        notifyDisplay.text = simpleDateTimeFormat.format(calendar.time)

        saveButton.isEnabled = false

        setDate.setOnClickListener {
            showDatePicker(initialYear, initialMonth, initialDayOfMonth)
        }

        setAlarm.setOnClickListener {
            val dialog = Notifications()
            dialog.show(requireActivity().supportFragmentManager, "NotificationsDialog")
        }

        saveButton.setOnClickListener {
            saveDataToFirestore()
        }

        //TODO: Change the database
        // Download data from the database
//        db.collection("vaccineNames")
//            .get()
//            .addOnSuccessListener { documents ->
//                val options = mutableListOf<String>()
//
//                options.add("Select Vaccine")
//
//                for (document in documents) {
//                    val username = document.getString("name")
//                    username?.let {
//                        options.add(it)
//                    }
//                }
//
//                val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, options)
//                adapter.setDropDownViewResource(R.layout.item_dropdown)
//                dropdownMenu.adapter = adapter
//
//                dropdownMenu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                        isVaccineChosen = position != 0
//                        enableSaveButton()
//                    }
//
//                    override fun onNothingSelected(parent: AdapterView<*>?) {
//                        isVaccineChosen = false
//                        enableSaveButton()
//                    }
//                }
//            }
//            .addOnFailureListener { exception ->
//                // Handle errors
//                println("Error getting documents: $exception")
//            }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    private fun showDatePicker(year: Int, month: Int, dayOfMonth: Int) {
        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val calendar = Calendar.getInstance()
            calendar.set(selectedYear, selectedMonth, selectedDay)

            val formattedDate = simpleFormat.format(calendar.time)
            dateDisplay.text = formattedDate

            appDate = saveFormat.format(calendar.time)

            name = dropdownMenu.selectedItem.toString()

        },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    private fun saveDataToFirestore() {
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email

//        // Save vaccination appointment date
//        val appointmentData = hashMapOf(name to appDate)
//        FirebaseFirestore.getInstance().collection("vaccinations").document(email.toString())
//            .set(appointmentData, SetOptions.merge()) // Use set with merge to update specific fields
//
//        // Save notification date
//        val notificationData = hashMapOf(name to notifyDate)
//        FirebaseFirestore.getInstance().collection("notifications").document(email.toString())
//            .set(notificationData, SetOptions.merge()) // Use set with merge to update specific fields
//            .addOnSuccessListener {
//                Toast.makeText(requireContext(), "Data saved successfully", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { exception ->
//                Toast.makeText(requireContext(), "Failed to save data: ${exception.message}", Toast.LENGTH_SHORT).show()
//            }
    }

    private fun enableSaveButton() {
        if (isVaccineChosen) {
            context?.let { saveButton.setBackgroundColor(ContextCompat.getColor(it, R.color.green)) }
            saveButton.isEnabled = true
        }
    }

    override fun onDateTimeSelected(selectedDate: String, selectedTime: String) {
        val selectedDateTimeString = "$selectedDate $selectedTime"

        val selectedDateTime = simpleDateTimeFormat.parse(selectedDateTimeString)

        val formattedDateTime = selectedDateTime?.let { simpleDateTimeFormat.format(it) }

        notifyDisplay.text = formattedDateTime

        notifyDate = selectedDateTime?.let { saveDateTimeFormat.format(it) }.toString()
    }
}
