package com.example.madness

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.madness.adapters.AdapterUpcoming
import com.example.madness.data.DataVaxx
import com.example.madness.databinding.ActivityUpcomingBinding
import com.google.android.material.imageview.ShapeableImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import java.time.LocalDate

class Upcoming : AppCompatActivity() {
    //TODO: download from db
    private val upcomingList = ArrayList<DataVaxx>()
    private lateinit var binding: ActivityUpcomingBinding
    private lateinit var adapter: AdapterUpcoming

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpcomingBinding.inflate(layoutInflater)
        val view = binding.root
        initData()
        setContentView(view)

        adapter  = AdapterUpcoming(upcomingList)
        binding.recyclerView.adapter = adapter

        val add = findViewById<ShapeableImageView>(R.id.add)
        add.setOnClickListener {
            val dialog = Add()
            dialog.show(supportFragmentManager, "AddDialogFragment")}

        val back = findViewById<ShapeableImageView>(R.id.back)
        back.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        val editText = binding.search.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.off_white))
        editText.setTextColor(ContextCompat.getColor(this, R.color.white))

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterItems(newText)
                return true
            }
        })

    }

    //TODO: delete testing list
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initData() {
        upcomingList.add(
            DataVaxx(
                "Medication A",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Take with food")
        )

        upcomingList.add(
            DataVaxx(
                "Medication B",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Aenean nunc velit, lacinia sed dolor sed, ultrices viverra nulla. Etiam a venenatis nibh.\n" +
                        "        Morbi laoreet, tortor sed facilisis varius, nibh orci rhoncus nulla, id elementum leo dui\n" +
                        "        non lorem. Nam mollis ipsum quis auctor varius. Quisque elementum eu libero sed commodo. In\n" +
                        "        eros nisl, imperdiet vel imperdiet et, scelerisque a mauris")
        )

        upcomingList.add(
            DataVaxx(
                "Medication C",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Take with food")
        )

        upcomingList.add(
            DataVaxx(
                "Medication D",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Take with food")
        )
        upcomingList.add(
            DataVaxx(
                "Medication E",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Take with food")
        )
        upcomingList.add(
            DataVaxx(
                "Medication F",
                LocalDate.of(2024, 4, 28),
                LocalDate.of(2024, 4, 20),
                "Take with food")
        )
    }
    private fun filterItems(query: String?) {
        adapter.filter.filter(query)
    }
}
