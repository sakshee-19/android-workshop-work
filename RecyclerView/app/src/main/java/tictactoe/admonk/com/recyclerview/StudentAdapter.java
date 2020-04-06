package tictactoe.admonk.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by win on 1/8/2017.
 */

public class StudentAdapter extends
                    RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    Context context;
    ArrayList<String> names =new ArrayList<>();
    ArrayList<String> ids=new ArrayList<>();

    public StudentAdapter(Context context, ArrayList<String> names, ArrayList<String> ids) {
        this.context = context;
        this.names = names;
        this.ids = ids;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studet,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvName.setText(names.get(position));
        holder.tvId.setText(ids.get(position));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvName;
        TextView tvId;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView) itemView.findViewById(R.id.tvName);

            tvId=(TextView) itemView.findViewById(R.id.tvId);
        }
    }
}
