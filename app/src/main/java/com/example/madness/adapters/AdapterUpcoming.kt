package com.example.madness.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.madness.R
import com.example.madness.data.DataVaxx
import java.util.Locale


class AdapterUpcoming(private val upcomingList: List<DataVaxx>):
    RecyclerView.Adapter<AdapterUpcoming.UpcomingViewHolder>(), Filterable
{    private var filteredList: List<DataVaxx> = upcomingList


    class UpcomingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name : TextView = itemView.findViewById(R.id.name)
        var dateNDTV : TextView = itemView.findViewById(R.id.dateND)
        var dateADMTV : TextView = itemView.findViewById(R.id.dateADM)
        var descTV : TextView = itemView.findViewById(R.id.desc)
        var doneBTN : TextView = itemView.findViewById(R.id.Done)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.stable)
        var expendableLayout : RelativeLayout = itemView.findViewById(R.id.Expandable)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_vaxx,parent,false)
        return UpcomingViewHolder(view)}


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val item: DataVaxx = filteredList[position]
        holder.name.text = item.name

        holder.doneBTN.setOnClickListener {
            //TODO: Save to database
        }

        holder.dateNDTV.text = item.nextDose.toString()
        holder.dateADMTV.text = item.lastDate.toString()
        holder.descTV.text = item.desc

        val isExpandable: Boolean = upcomingList[position].expandable
        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val version = upcomingList[position]
            version.expandable = !item.expandable
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                filteredList = if (charString.isEmpty()) {
                    upcomingList
                } else {
                    upcomingList.filter { item ->
                        item.name.lowercase(Locale.getDefault()).contains(
                            charString.lowercase(
                                Locale.getDefault()
                            )
                        )
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                filteredList = filterResults?.values as? List<DataVaxx> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}