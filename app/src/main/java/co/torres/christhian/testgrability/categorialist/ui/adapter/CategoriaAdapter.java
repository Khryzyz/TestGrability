package co.torres.christhian.testgrability.categorialist.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.torres.christhian.testgrability.R;
import co.torres.christhian.testgrability.models.CategoryApp;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {


    private List<CategoryApp> items;
    private OnItemClickListener onItemClickListener;

    public CategoriaAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onClick(ViewHolder holder, int idCategoria);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoria_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CategoryApp categoryApp = items.get(position);
        // Asignación UI
        holder.txvCategoriaNombre.setText(categoryApp.getDescription());

    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    public void swapData(List<CategoryApp> categoryAppList) {
        if (categoryAppList != null) {
            items = categoryAppList;
            notifyDataSetChanged();
        }
    }

    public List<CategoryApp> getCategory() {
        return items;
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txvCategoriaNombre;

        /**
         * Contructor de la clase
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            txvCategoriaNombre = (TextView) itemView.findViewById(R.id.txvCategoriaNombre);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(this, obtenerIdCategoria(getAdapterPosition()));
        }
    }

    private int obtenerIdCategoria(int adapterPosition) {
        if (items != null) {
            CategoryApp categoryApp = items.get(adapterPosition);
            if (categoryApp != null) {
                return categoryApp.getId();
            }
        }

        return 0;
    }

}
