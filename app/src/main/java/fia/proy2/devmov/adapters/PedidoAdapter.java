package fia.proy2.devmov.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import fia.proy2.devmov.Entities.PedidoEntity;
import fia.proy2.devmov.R;

/**
 * Created by james on 25/05/2017.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {
    private List<PedidoEntity> pedidos;
    private int layout;
    private Activity activity;

    public PedidoAdapter(List<PedidoEntity> pedidos, int layout, Activity activity) {
        this.pedidos = pedidos;
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtPedido;
        public TextView txtFecha;
        public TextView txtDescripcion;
        public TextView txtEstado;
        public ViewHolder(View v){
            super(v);
            txtPedido = (TextView) v.findViewById(R.id.idpedido);
            txtFecha = (TextView) v.findViewById(R.id.fecha);
            txtDescripcion = (TextView) v.findViewById(R.id.nombrePedido);
            txtEstado = (TextView) v.findViewById(R.id.estado);
        }

        public void bind(final PedidoEntity pedido){
            txtPedido.setText("Pedido - "+ (getAdapterPosition()+1));
            txtFecha.setText("Fecha: "+pedido.getFecha());
            txtDescripcion.setText(pedido.getNombre_producto()+" x "+pedido.getCantidad());
            String estado="";
            if(pedido.getEstado()==0){
                estado="Estado: En Camino";
            }else {
                estado="Estado: Entregado";
            }
            txtEstado.setText(estado);
        }

    }

    public interface  OnItemClickListener{
        void onItemClick(PedidoEntity pedido, int position);
    }

}