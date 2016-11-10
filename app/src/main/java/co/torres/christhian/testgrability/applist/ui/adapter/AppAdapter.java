package co.torres.christhian.testgrability.applist.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.models.AppInfo;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    Context context;

    private List<AppInfo> items;
    private OnItemClickListener onItemClickListener;


    public AppAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onClick(ViewHolder holder, int idApp);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = items.get(position);
        // Asignación UI
        holder.txvAppNombre.setText(appInfo.getName());
        Glide.with(context)
                .load(appInfo.getUrlImageCero())
                .into(holder.imgApp);
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    public void swapData(List<AppInfo> appInfo) {
        if (appInfo != null) {
            items = appInfo;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txvAppNombre;
        public ImageView imgApp;

        public ViewHolder(View itemView) {
            super(itemView);
            txvAppNombre = (TextView) itemView.findViewById(R.id.txvAppNombre);
            imgApp = (ImageView) itemView.findViewById(R.id.imgApp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(this, obtenerIdApp(getAdapterPosition()));
        }
    }

    private int obtenerIdApp(int adapterPosition) {
        if (items != null) {
            AppInfo appInfo = items.get(adapterPosition);
            if (appInfo != null) {
                return appInfo.getId();
            }
        }

        return 0;
    }

}
