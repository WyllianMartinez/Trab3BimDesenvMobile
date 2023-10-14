package br.com.salesordersapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import br.com.salesordersapp.database.SQLiteDataHelper;
import br.com.salesordersapp.model.Pedido;

public class PedidoDAO {
    private SQLiteDataHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"ID", "ID_CLIENTE", "VLR_TOTAL", "COND_PAGAMENTO", "QTD_PARCELAS", "ID_ENDERECO_ENTREGA"};
    private String tableName = "PEDIDO";
    private Context context;
    private static PedidoDAO instancia;

    public static PedidoDAO getInstance(Context context){
        if(instancia == null)
            return instancia = new PedidoDAO(context);
        else
            return instancia;
    }

    private PedidoDAO(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context,"SALESORDER",null,1);
        bd = openHelper.getWritableDatabase();
    }

    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("ID_CLIENTE", obj.getId_Cliente());
            valores.put("VLR_TOTAL", obj.getVlrTotal());
            valores.put("COND_PAGAMENTO", obj.getCondPgto());
            valores.put("QTD_PARCELAS", obj.getQtdParcelas());
            valores.put("ID_ENDERECO_ENTREGA", obj.getId_EnderecoEntrega());

            // Correção: Chame o método insert do SQLiteDatabase
            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    public long update(Pedido obj) {
        try {

            ContentValues valores = new ContentValues();
            valores.put("ID_CLIENTE", obj.getId_Cliente());
            valores.put("VLR_TOTAL", obj.getVlrTotal());
            valores.put("COND_PAGAMENTO", obj.getCondPgto());
            valores.put("QTD_PARCELAS", obj.getQtdParcelas());
            valores.put("ID_ENDERECO_ENTREGA", obj.getId_EnderecoEntrega());

            String[]identificador = {String.valueOf(obj.getId())};

            return bd.update(tableName, valores, "ID = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoDAO.update(): "+ex.getMessage());

        }
        return -1;
    }

    public long delete(Pedido obj) {
        try {
            String[] identificador = {String.valueOf(obj.getId())};

            // Correção: A condição WHERE deve ser "ID = ?"
            return bd.delete(tableName, "ID = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "PEDIDO");
            if(cursor.moveToFirst()){
                do{
                    Pedido pedido = new Pedido();
                    pedido.setId(cursor.getInt(0));
                    pedido.setId_Cliente(cursor.getInt(1));
                    pedido.setId_EnderecoEntrega(cursor.getInt(2));
                    pedido.setProdutos(cursor.getString(3));
                    pedido.setQtdParcelas(cursor.getInt(4));
                    pedido.setVlrParcelas(cursor.getDouble(5));
                    pedido.setVlrTotal(cursor.getDouble(6));
                    pedido.setCondPgto(cursor.getString(7));


                    lista.add(pedido);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoDAO.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    public Pedido getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "ID = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                Pedido pedido = new Pedido();
                pedido.setId(cursor.getInt(0));
                pedido.setId_Cliente(cursor.getInt(1));
                pedido.setId_EnderecoEntrega(cursor.getInt(2));
                pedido.setProdutos(cursor.getString(3));
                pedido.setQtdParcelas(cursor.getInt(4));
                pedido.setVlrParcelas(cursor.getDouble(5));
                pedido.setVlrTotal(cursor.getDouble(6));
                pedido.setCondPgto(cursor.getString(7));

                return pedido;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoDAO.getAll(): "+ex.getMessage());
        }
        return null;
    }
}