package com.example.souza.provateste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends BaseAdapter
        implements Filterable {
    private List<Cliente> listaCompleta;
    private List<Cliente> resultados;
    private Filter meuFiltro;
    Context ctx;
    public AutoCompleteAdapter(
            Context ctx, List<Cliente> textos) {
        this.ctx = ctx;
        this.listaCompleta = textos;
        this.resultados = listaCompleta;
        this.meuFiltro = new MeuFiltro();
    }
    @Override
    public int getCount() {
        return resultados.size();
    }
    @Override
    public Cliente getItem(int position) {
        if (resultados != null
                && resultados.size() > 0
                && position < resultados.size()) {
            return resultados.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Cliente cliente = resultados.get(position);
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(ctx)
                    .inflate(R.layout.activity_cliente_adapter, null);
            holder = new ViewHolder();
            holder.txtCpf = (TextView) convertView.findViewById(R.id.cpf_list);
            holder.txtTelefone = (TextView) convertView.findViewById(R.id.tel_list);
            holder.txtSexo = (TextView) convertView.findViewById(R.id.sexo_list);
            holder.txtNome = (TextView) convertView.findViewById(R.id.nome_list);
            holder.txtAtivo = (TextView) convertView.findViewById(R.id.ativo_list);
            holder.txtEndereco = (TextView) convertView.findViewById(R.id.endereco_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txtCpf.setText(cliente.getCpf());
        holder.txtNome.setText(cliente.getNome());
        holder.txtEndereco.setText(cliente.getEndereco());
        holder.txtTelefone.setText(cliente.getTelefone());
        String sexo;
        if(cliente.getSexo() == 'M')
            sexo = convertView.getResources().getString(R.string.sexo_m_txt);
        else if(cliente.getSexo() == 'F')
            sexo = convertView.getResources().getString(R.string.sexo_f_txt);
        else
            sexo = convertView.getResources().getString(R.string.sexo_o_txt);

        holder.txtSexo.setText(sexo);
        holder.txtAtivo.setText(cliente.isAtivo()?convertView.getResources().getString(R.string.ativo_txt):convertView.getResources().getString(R.string.inativo_txt));
        return convertView;
    }

    static class ViewHolder {
        TextView txtNome;
        TextView txtEndereco;
        TextView txtTelefone;
        TextView txtCpf;
        TextView txtSexo;
        TextView txtAtivo;
    }

    @Override
    public Filter getFilter() {
        return meuFiltro;
    }
    private class MeuFiltro extends Filter {
        @Override
        protected FilterResults performFiltering(
                CharSequence constraint) {
            FilterResults filterResults =
                    new FilterResults();
            ArrayList<Cliente> temp =
                    new ArrayList<Cliente>();
            if (constraint != null) {
                String term = constraint.toString().trim();
                for (Cliente cliente : listaCompleta) {
                    if (cliente.getCpf().indexOf(term) > -1) {
                        temp.add(cliente);
                    }
                }
            }
            filterResults.values = temp;
            filterResults.count = temp.size();
            return filterResults;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(
                CharSequence constraint,
                FilterResults filterResults) {
            resultados = (ArrayList<Cliente>)
                    filterResults.values;
            notifyDataSetChanged();
        }
    }
}
