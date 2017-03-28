package com.example.souza.provateste;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;

public class ListarActivity extends AppCompatActivity {
    ListView listView;
    ClienteAdapter clienteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listView = (ListView) findViewById(R.id.list_clientes);
        listView.setEmptyView(findViewById(android.R.id.empty));

        clienteAdapter = new ClienteAdapter(this, Util.listar());

        final int PADDING = 6;
        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.BLUE);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.cabecalho);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);
        listView.addHeaderView(txtHeader);
        final TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(
                R.plurals.texto_quantidade,
                clienteAdapter.getCount(),
                clienteAdapter.getCount()));
        txtFooter.setBackgroundColor(Color.RED);
        txtFooter.setGravity(Gravity.RIGHT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        listView.addFooterView(txtFooter);
        listView.setAdapter(clienteAdapter);
    }


}
