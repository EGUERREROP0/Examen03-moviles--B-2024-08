package com.example.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TeacherAdapter(var teacherList: List<Teacher> = emptyList() ):
    RecyclerView.Adapter<TeacherViewHolder>(){

    fun updateTeacherList(teacherList: List<Teacher>){
        this.teacherList = teacherList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeacherViewHolder(inflater.inflate(R.layout.item_teacher, parent, false))
    }

    override fun getItemCount(): Int = teacherList.size

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teacherList[position])
    }
}
