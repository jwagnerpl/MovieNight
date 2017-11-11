package teamtreehouse.com.movienight;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LinearLayout rvItem;

    Context context;
    String[] items;

    public TvAdapter(Context context, String[] items) {
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
                String title = DisplayMovies.shows.get(position).getShowTitle();
                String description = DisplayMovies.shows.get(position).getShowDescription();
                dialog.setTitle(title);
                dialog.setDescription(description);
                android.app.FragmentManager manager = ((Activity) context).getFragmentManager();
                dialog.show(manager, "hello");
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
