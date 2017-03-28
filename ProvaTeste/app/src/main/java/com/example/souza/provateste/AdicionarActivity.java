package com.example.souza.provateste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class AdicionarActivity extends AppCompatActivity {
    EditText nome;
    EditText cpf;
    EditText endereco;
    EditText telefone;
    RadioButton masc;
    RadioButton fem;
    RadioButton outros;
    Switch ativo;
    boolean respAtivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        nome = (EditText) findViewById(R.id.nome_add);
        cpf = (EditText) findViewById(R.id.cpf_add);
        endereco = (EditText) findViewById(R.id.end_add);
        telefone = (EditText) findViewById(R.id.tel_add);
        masc = (RadioButton) findViewById(R.id.sexo_m_add);
        fem = (RadioButton) findViewById(R.id.sexo_f_add);
        outros = (RadioButton) findViewById(R.id.sexo_o_add);
        ativo = (Switch) findViewById(R.id.ativo_add);

        ativo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                respAtivo = isChecked;
            }
        });

        cpf.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                boolean hasMask =
                        s.toString().indexOf('.') > -1 ||
                                s.toString().indexOf('-') > -1;
                String str = s.toString()
                        .replaceAll("[.]", "")
                        .replaceAll("[-]", "");

                if (after >= before) {
                    if(str.length() > 9){
                        str = str.substring(0, 3) + '.' +
                                str.substring(3, 6) + '.' +
                                str.substring(6,9) + '-'+
                                str.substring(9);
                    }
                    else if (str.length() > 6) {
                        str = str.substring(0, 3) + '.' +
                                str.substring(3, 6) + '.' +
                                str.substring(6);
                    } else if (str.length() > 3) {
                        str = str.substring(0, 3) + '.' +
                                str.substring(3);
                    }
                    isUpdating = true;
                    cpf.setText(str);
                    cpf.setSelection(cpf.getText().length());
                } else {
                    isUpdating = true;
                    cpf.setText(str);
                    cpf.setSelection(Math.max(0, Math.min(hasMask ?
                            start - before : start, str.length() ) ) );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void adicionar(View view){
        Cliente cliente = new Cliente();
        cliente.setNome(nome.getText().toString());
        cliente.setCpf(cpf.getText().toString());
        cliente.setTelefone(telefone.getText().toString());
        cliente.setEndereco(endereco.getText().toString());
        cliente.setSexo(verificarSexo());
        cliente.setAtivo(respAtivo);
        verificarPreencidos(cliente);
    }

    private void verificarPreencidos(Cliente u){
        if(u.getNome().trim().isEmpty() || u.getCpf().trim().isEmpty() || u.getEndereco().trim().isEmpty() || u.getTelefone().trim().isEmpty()){
            Toast.makeText(this,"Ocorreu um erro", Toast.LENGTH_SHORT).show();
        }else{
            Util.listar().add(u);
            Toast.makeText(this,"Adicionado com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    private char verificarSexo(){
        if(masc.isChecked())
            return 'M';
        else if(fem.isChecked())
            return 'F';
        else
            return 'O';
    }

    public void verificarDados() {
        Toast toast = Toast.makeText(AdicionarActivity.this, "Dados adicionados",Toast.LENGTH_LONG);
        toast.show();
    }


}
