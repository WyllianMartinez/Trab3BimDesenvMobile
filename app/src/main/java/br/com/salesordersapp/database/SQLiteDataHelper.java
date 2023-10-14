package br.com.salesordersapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOME TEXT, " +
                "CPF TEXT, " +
                "DTNASCIMENTO TEXT" +
                ")");





        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LOGRADOURO TEXT, " +
                "NUMERO TEXT, " +
                "BAIRRO TEXT, " +
                "CIDADE TEXT, " +
                "UF TEXT, " +
                "ID_CLIENTE INTEGER, " +  // Adicionando a coluna ID_CLIENTE para vincular o ID do cliente
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID)" +  // Vinculando o ID_CLIENTE à tabela CLIENTE
                ")");



        sqLiteDatabase.execSQL("CREATE TABLE PRODUTO (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DESCRICAO TEXT, " +
                "VLR_UNITARIO REAL, " +
                "UND_MEDIDA TEXT" +
                ")");


        sqLiteDatabase.execSQL("CREATE TABLE PRODUTO_VENDA (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ID_PEDIDO INTEGER, " +
                "ID_PRODUTO INTEGER, " +
                "QUANTIDADE INTEGER, " +
                "VLR_UNITARIO REAL, " +
                "FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID), " +
                "FOREIGN KEY (ID_PRODUTO) REFERENCES PRODUTO(ID)" +  // Corrigir a referência para ID_PRODUTO
                ")");


        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ID_CLIENTE INTEGER, " +
                "ID_PRODUTO INTEGER, " + // Alteração na coluna PRODUTOS para armazenar o ID do produto
                "VLR_TOTAL REAL, " +
                "COND_PGTO TEXT, " +
                "QTD_PARCELAS INTEGER, " +
                "VLR_PARCELAS TEXT, " +
                "ID_ENDERECO_ENTREGA INTEGER, " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID), " +
                "FOREIGN KEY (ID_PRODUTO) REFERENCES PRODUTO(ID)" + // Adição da referência estrangeira para ID_PRODUTO
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
