import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paheco.firebase_take1.MainActivity
import com.paheco.firebase_take1.R

class FruitAdapter : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    var allfruit = mutableListOf<MainActivity.Fruit>()
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fruitName : TextView

        init {
            fruitName = view.findViewById(R.id.fruitNameTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("pia11debug", "SKAPA RAD")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("pia11debug", "RITA RAD " + position.toString())
        holder.fruitName.text = allfruit[position].name
    }

    override fun getItemCount(): Int {
        return allfruit.size
    }

}