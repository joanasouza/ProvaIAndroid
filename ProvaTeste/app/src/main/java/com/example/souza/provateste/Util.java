package com.example.souza.provateste;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static List<Cliente> clientes = new ArrayList<>();

    public static List<Cliente> listar(){
        if(clientes.size() == 0){
            clientes.add(new Cliente("Joana","123.456.789-12","123456789","Rua Indefinida",true,'F'));
        }
        return  clientes;
    }
}
