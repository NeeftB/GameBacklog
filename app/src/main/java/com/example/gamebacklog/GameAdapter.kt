package com.example.gamebacklog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.game_item, parent, false
        ))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvGameTitle.text = context.getString(R.string.game_title_text,game.title)
            itemView.tvPlatform.text = context.getString(R.string.platform_text,game.platform)
            itemView.tvDate.text = context.getString(R.string.date_text, dateConverter(game.date))
        }
    }

    private fun dateConverter(date: Date): String{
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.US)

        return format.format(date).toString()
    }

}
