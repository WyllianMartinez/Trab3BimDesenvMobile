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
import br.com.salesordersapp.model.Produto;

public class ProdutoDAO implements GenericDAO<Produto>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[]colunas = {"ID","DESCRICAO","VLR_UNITARIO","UND_MEDIDA"};

    private String tablename = "PRODUTO";
    private Context context;
    private static ProdutoDAO instancia;

    public static ProdutoDAO getInstance(Context context){
        if(instancia == null)
            return instancia = new ProdutoDAO(context);
        else
            return instancia;
    }

    public ProdutoDAO(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context,"SALESORDER",null,1);
        bd = openHelper.getWritableDatabase();
    }

    public long insert(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLR_UNITARIO", obj.getVlrUnitario());
            valores.put("UND_MEDIDA", obj.getUndMedida());

            return bd.insert(tablename, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "ProdutoDAO.insert(): "+ex.getMessage());
        }
        return -1;
    }

    public long update(Produto obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLR_UNITARIO", obj.getVlrUnitario());
            valores.put("UND_MEDIDA", obj.getUndMedida());

            String[] identificador = {String.valueOf(obj.getId())};

            return bd.update(tablename, valores, "ID = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ProdutoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }

    public long delete(Produto obj) {
        try {
            String[]identificador = {String.valueOf(obj.getId())};
            return  bd.delete(tablename, "ID = ?",identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "ProdutoDAO.delete(): "+ex.getMessage());

        }
        return -1;
    }

    public ArrayList<Produto> getAll() {
        ArrayList<Produto> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tablename, colunas,
                    null, null,
                    null, null, "ID");  // ou "DESCRICAO" ou outra coluna apropriada para a ordenação

            if(cursor.moveToFirst()){
                do{
                    Produto produto= new Produto();
                    produto.setId(cursor.getInt(0));
                    produto.setDescricao(cursor.getString(1));
                    produto.setVlrUnitario(cursor.getDouble(2));
                    produto.setUndMedida(cursor.getString(3));

                    lista.add(produto);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "ProdutoDAO.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Produto getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tablename, colunas,
                    "ID = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setDescricao(cursor.getString(1));
                produto.setVlrUnitario(cursor.getDouble(2));
                produto.setUndMedida(cursor.getString(3));

                return produto;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "ProdutoDAO.getAll(): "+ex.getMessage());
        }
        return null;
    }
}