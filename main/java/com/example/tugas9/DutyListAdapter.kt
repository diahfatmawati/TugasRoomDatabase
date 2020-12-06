package com.example.tugas9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DutyListAdapter internal constructor(
    context: Context) : RecyclerView.Adapter<DutyListAdapter.DutyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var dutys = emptyList<Duty>() // Cache copy of words

    inner class DutyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dutyItemView: TextView = itemView.findViewById(R.id.textView) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DutyViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return DutyViewHolder(itemView) }
    override fun onBindViewHolder(holder: DutyViewHolder, position: Int) {
        val current = dutys[position]
        holder.dutyItemView.text = current.duty }
    internal fun setDutys(dutys: List<Duty>) {
        this.dutys = dutys
        notifyDataSetChanged() }
    override fun getItemCount() = dutys.size
}