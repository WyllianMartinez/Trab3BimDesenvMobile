package br.com.salesordersapp.dao;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import br.com.salesordersapp.database.SQLiteDataHelper;
import br.com.salesordersapp.model.Endereco;

public class EnderecoDAO implements GenericDAO<Endereco> {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"ID", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF", "ID_CLIENTE"};
    private String tableName = "ENDERECO";
    private Context context;
    private static EnderecoDAO instancia;

    public static EnderecoDAO getInstancia(Context context) {
        if (instancia == null)
            return instancia = new EnderecoDAO(context);
        else
            return instancia;
    }

    private EnderecoDAO(Context context) {
        this.context = context;
        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "SALESORDER", null, 1);

        //Atribuindo a base de dados a variavel, e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();

    }


    public long insert(Endereco obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("LOGRADOURO", obj.getLogradouro());
            valores.put("NUMERO", obj.getNumero());
            valores.put("BAIRRO", obj.getBairro());
            valores.put("CIDADE", obj.getCidade());
            valores.put("UF", obj.getUf());
            valores.put("ID_CLIENTE", obj.getIdCliente());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }


    public long update(Endereco obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("LOGRADOURO", obj.getLogradouro());
            valores.put("NUMERO", obj.getNumero());
            valores.put("BAIRRO", obj.getBairro());
            valores.put("CIDADE", obj.getCidade());
            valores.put("UF", obj.getUf());
            valores.put("ID_CLIENTE", obj.getIdCliente());

            String[] identificador = {String.valueOf(obj.getId())};

            return bd.update(tableName, valores, "ID = ?", identificador);


        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }


    public long delete(Endereco obj) {
        try {
            String[] identificador = {String.valueOf(obj.getId())};
            return bd.delete(tableName, "ID = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    public ArrayList<Endereco> getAll() {
        ArrayList<Endereco> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "LOGRADOURO");  // Corrigido para ordenar por LOGRADOURO

            if (cursor.moveToFirst()) {
                do {
                    Endereco endereco = new Endereco();
                    endereco.setId(cursor.getInt(0));
                    endereco.setLogradouro(cursor.getString(1));
                    endereco.setNumero(cursor.getString(2));
                    endereco.setBairro(cursor.getString(3));
                    endereco.setCidade(cursor.getString(4));
                    endereco.setUf(cursor.getString(5));
                    endereco.setIdCliente(cursor.getInt(6));

                    lista.add(endereco);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDAO.getAll(): " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Endereco getById(int id) {
        try {
            String[] identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "ID = ?", identificador, null,
                    null, null);

            if (cursor.moveToFirst()) {
                Endereco endereco = new Endereco();
                endereco.setId(cursor.getInt(0));
                endereco.setLogradouro(cursor.getString(1));
                endereco.setNumero(cursor.getString(2));
                endereco.setBairro(cursor.getString(3));
                endereco.setCidade(cursor.getString(4));
                endereco.setUf(cursor.getString(5));
                endereco.setIdCliente(cursor.getInt(6));


                return endereco;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDao.getAll(): " + ex.getMessage());
        }
        return null;
    }

    public Endereco getByClienteId(int id) {
        try {
            String[] identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "ID_CLIENTE = ?", identificador, null,
                    null, null);

            if (cursor.moveToFirst()) {
                Endereco endereco = new Endereco();
                endereco.setId(cursor.getInt(0));
                endereco.setLogradouro(cursor.getString(1));
                endereco.setNumero(cursor.getString(2));
                endereco.setBairro(cursor.getString(3));
                endereco.setCidade(cursor.getString(4));
                endereco.setUf(cursor.getString(5));
                endereco.setIdCliente(cursor.getInt(6));

                return endereco;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDao.getByClienteId(): " + ex.getMessage());
        }
        return null;
    }

}