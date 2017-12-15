package yoktavian.dev.prelo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import yoktavian.dev.prelo.R;
import yoktavian.dev.prelo.models.LoveListModel;

/**
 * Created by yoktavian on 12/13/17.
 */

public class AdapterLoveList extends RecyclerView.Adapter<AdapterLoveList.MyViewHolder> {

    private ArrayList<LoveListModel> model;
    private Context mcontext;
    private final OnItemClickListener listener;

    public AdapterLoveList(Context mcontext, ArrayList<LoveListModel> model, OnItemClickListener listener){
        this.mcontext   = mcontext;
        this.model      = model;
        this.listener   = listener;
    }

    @Override
    public AdapterLoveList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.list_lovelist, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterLoveList.MyViewHolder holder, int position) {
        final LoveListModel data = model.get(position);
        holder.click(model.get(position), listener);
        Glide.with(mcontext).load(data.getDisplay_picts().get(0).getAsString())
                            .into(holder.img_photo);
        holder.txt_name.setText(data.getName());
        holder.txt_price.setText("Rp. "+data.getPrice());
    }

    public interface OnItemClickListener {
        void onClick(LoveListModel model);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_name, txt_price;
        public ImageView img_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name  = (TextView) itemView.findViewById(R.id.txt_name);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            img_photo = (ImageView) itemView.findViewById(R.id.img_photo);
        }

        public void click(final LoveListModel model, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(model);
                }
            });
        }
    }
}
