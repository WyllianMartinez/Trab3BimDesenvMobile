package br.com.salesordersapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import br.com.salesordersapp.database.SQLiteDataHelper;
import br.com.salesordersapp.model.Cliente;

public class ClienteDAO implements GenericDAO<Cliente>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[]colunas = {"ID", "NOME", "CPF", "DTNASCIMENTO"};
    private String tableName = "CLIENTE";
    private Context context;
    private static ClienteDAO instancia;

    public static ClienteDAO getInstancia(Context context){
        if(instancia == null)
            return instancia = new ClienteDAO(context);
        else
            return instancia;
    }

    private ClienteDAO(Context context) {
        this.context = context;
        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context,"SALESORDER",null,1);

        //Atribuindo a base de dados a variavel, e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();

    }



    public long insert(Cliente obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("DTNASCIMENTO",obj.getDtNascimento());

            return bd.insert(tableName, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDAO.insert(): "+ex.getMessage());
        }
        return -1;
    }


    public long update(Cliente obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("DTNASCIMENTO",obj.getDtNascimento());

            String[]identificador = {String.valueOf(obj.getId())};

            return bd.update(tableName, valores, "ID = ?", identificador);


        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDAO.update(): "+ex.getMessage());
        }
        return -1;
    }


    @Override
    public long delete(Cliente obj) {
        try {
            String[] identificador = {String.valueOf(obj.getId())};
            return bd.delete(tableName, "ID = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "ClienteDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }



    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "ID");

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();
                    cliente.setId(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setCpf(cursor.getString(2));
                    cliente.setDtNascimento(cursor.getString(3));

                    lista.add(cliente);
                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDAO.getAll(): " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Cliente getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "ID");


            if(cursor.moveToFirst()){
                Cliente aluno = new Cliente();
                aluno.setId(cursor.getInt(0));
                aluno.setCpf(cursor.getString(1));
                aluno.setDtNascimento(cursor.getString(2));

                return aluno;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "AlunoDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}