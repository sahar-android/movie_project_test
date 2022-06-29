package ir.simyapp.batmanmoviesapp.ui.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.simyapp.batmanmoviesapp.R
import ir.simyapp.batmanmoviesapp.data.Api
import ir.simyapp.batmanmoviesapp.models.Movie
import ir.simyapp.batmanmoviesapp.ui.details.DetailMovieActivity

/**
 * @author Sahar Simyari
 */
class MovieListAdapter(private val context: Context,private val movieList:ArrayList<Movie>)
    :RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {


    class MovieViewHolder(private val context: Context, itemView: View):RecyclerView.ViewHolder(itemView){
      fun bind(movie:Movie){
         itemView.setOnClickListener {
             val intent=Intent(context,DetailMovieActivity::class.java)
             intent.putExtra(DetailMovieActivity.MOVIE_ID,movie.imdbID)
             context.startActivity(intent)
         }

          itemView.findViewById<TextView>(R.id.tvTitle).text=movie.title

          Glide.with(context).load(movie.poster)
              .apply(RequestOptions().override(400,400)).centerInside().placeholder(R.drawable.batman)
              .into( itemView.findViewById<ImageView>(R.id.ivPoster))

      }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return MovieViewHolder(context,view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int=movieList.size


    fun updateData(newList:List<Movie>){
        movieList.clear()
        val oldTonewList=newList.sortedBy {
            movie -> movie.year
        }

        movieList.addAll(oldTonewList)
        notifyDataSetChanged()
    }
}