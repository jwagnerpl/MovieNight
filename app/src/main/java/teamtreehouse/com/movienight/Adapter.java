package teamtreehouse.com.movienight;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LinearLayout rvItem;

    Context context;
    String[] items;

    public Adapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.custom_row, parent, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item) holder).textView.setText(items[position]);
        holder.setIsRecyclable(false);

        rvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialogFragment dialog = new AlertDialogFragment();
                String title = DisplayMovies.movies.get(position).getMovieTitle();
                String description = DisplayMovies.movies.get(position).getMovieDescription();
                dialog.setTitle(title);
                dialog.setDescription(description);
                try {
                    android.app.FragmentManager manager = ((Activity) context).getFragmentManager();
                    dialog.show(manager, "hello");
                } catch (ClassCastException e) {

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class Item extends RecyclerView.ViewHolder {
        TextView textView;

        public Item(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
            rvItem = itemView.findViewById(R.id.rvRow);
        }
    }
}
