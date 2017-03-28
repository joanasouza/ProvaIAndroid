package com.example.souza.provateste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClienteAdapter extends BaseAdapter {
    Context context;
    List<Cliente> clientes;

    public ClienteAdapter(Context context, List<Cliente> Clientes) {
        this.context = context;
        this.clientes = Clientes;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Cliente cliente = clientes.get(position);
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(context)
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
}
