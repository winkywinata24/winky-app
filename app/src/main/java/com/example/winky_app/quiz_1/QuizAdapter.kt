package com.example.winky_app.quiz_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.winky_app.R
import com.example.winky_app.basic_listview.ListModel

class QuizAdapter (
    context: Context,
    private val menuList : List<QuizModel>
) : ArrayAdapter<QuizModel>(context, 0, menuList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.list_quiz, parent, false)

        val menuItem = getItem(position)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textName: TextView = view.findViewById(R.id.textName)

        menuItem?.let {
            imageView.setImageResource(it.imageResId)
            textName.text = it.name
        }

        return view
    }
}