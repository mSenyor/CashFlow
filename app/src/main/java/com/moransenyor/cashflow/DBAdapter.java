package com.moransenyor.cashflow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	private static final String TAG = "DBAdapter"; //used for logging database version changes

	public static final int DATABASE_VERSION = 7; // The version number must be incremented each time a change to DB structure occurs.
	// on version 1: id, price, currency, date, product, vendor, category, receipt, behalf, tags, notes

	// DataBase info:
	public static final String DATABASE_NAME = "dbCashMap";

	// Table names
	public static final String TABLE_RECEIPTS = "main_table";
	public static final String TABLE_CURRENCY = "currency_table";
	public static final String TABLE_PRODUCT = "products_table";
	public static final String TABLE_VENDOR = "vendors_table";
	public static final String TABLE_CATEGORY = "category_table";
	public static final String TABLE_BEHALF = "behalf_table";
	public static final String TABLE_TAGS = "tags_table";
	public static final String TABLE_RECEIPT_TAGS = "receipt_tags_table";
	public static final String TABLE_REPORTS = "reports_table";
	public static final String TABLE_REPORT_RECEIPTS = "report_receipts";


	// all tables shared
	public static final String KEY_ROWID = "_id";


	// TABLE_RECEIPT column names
	public static final String KEY_PRICE = "price";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_DATE = "date";
	public static final String KEY_NOTES = "notes";
	public static final String KEY_CURRENCY_ID = "currency_id";
	public static final String KEY_PRODUCT_ID = "product_id";
	public static final String KEY_VENDOR_ID = "vendor_id";
	public static final String KEY_CATEGORY_ID = "category_id";
	public static final String KEY_BEHALF_ID = "behalf_id";

	public static final String[] TABLE_RECEIPT_KEYS = new String[] {KEY_ROWID, KEY_PRICE, KEY_IMAGE, KEY_DATE, KEY_NOTES, KEY_CURRENCY_ID, KEY_PRODUCT_ID,
			KEY_VENDOR_ID, KEY_CATEGORY_ID, KEY_BEHALF_ID};

	// Column Numbers for each Field Name:
	public static final int RECEPIT_COL_ROWID = 0;
	public static final int RECEPIT_COL_PRICE = 1;
	public static final int RECEPIT_COL_IMAGE = 2;
	public static final int RECEPIT_COL_DATE = 3;
	public static final int RECEPIT_COL_NOTES = 4;
	public static final int RECEPIT_COL_CURRENCY_ID = 5;
	public static final int RECEPIT_COL_PRODUCT_ID = 6;
	public static final int RECEPIT_COL_VENDOR_ID = 7;
	public static final int RECEPIT_COL_CATEGORY_ID = 8;
	public static final int RECEPIT_COL_BEHALF = 9;




	// TABLE_CURRENCY column names
	public static final String KEY_CURRENCY_NAME = "currency_name";
	public static final String KEY_CURRENCY_SYMBOL = "currency_symbol";

	public static final String[] TABLE_CURRENCY_KEYS = new String[] {KEY_ROWID, KEY_CURRENCY_NAME,
			KEY_CURRENCY_SYMBOL};

	// Column Numbers for each Field Name:
	public static final int CURRENCY_COL_ROWID = 0;
	public static final int CURRENCY_COL_NAME = 1;
	public static final int CURRENCY_COL_SYMBOL = 2;



	// TABLE_PRODUCT column names
	public static final String KEY_PRODUCT_NAME = "product";

	public static final String[] TABLE_PRODUCT_KEYS = new String[] {KEY_ROWID, KEY_PRODUCT_NAME};

	// Column Numbers for each Field Name:
	public static final int PRODUCT_COL_ROWID = 0;
	public static final int PRODUCT_COL_NAME = 1;



	// TABLE_VENDOR column names
	public static final String KEY_VENDOR_NAME = "vendor";

	public static final String[] TABLE_VENDOR_KEYS = new String[] {KEY_ROWID, KEY_VENDOR_NAME};

	// Column Numbers for each Field Name:
	public static final int VENDOR_COL_ROWID = 0;
	public static final int VENDOR_COL_NAME = 1;



	// TABLE_CATEGORY column names
	public static final String KEY_CATEGORY_NAME = "category";

	public static final String[] TABLE_CATEGORY_KEYS = new String[] {KEY_ROWID, KEY_CATEGORY_NAME};

	// Column Numbers for each Field Name:
	public static final int CATEGORY_COL_ROWID = 0;
	public static final int CATEGORY_COL_NAME = 1;



	// TABLE_BEHALF column names
	public static final String KEY_BEHALF_NAME = "behalf";

	public static final String[] TABLE_BEHALF_KEYS = new String[] {KEY_ROWID, KEY_BEHALF_NAME};

	// Column Numbers for each Field Name:
	public static final int BEHALF_COL_ROWID = 0;
	public static final int BEHALF_COL_NAME = 1;



	// TABLE_TAGS column names
	public static final String KEY_TAG = "tag";

	public static final String[] TABLE_TAG_KEYS = new String[] {KEY_ROWID, KEY_TAG};

	// Column Numbers for each Field Name:
	public static final int TAGS_COL_ROWID = 0;
	public static final int TAGS_COL_TAG = 1;



	// TABLE_RECEIPT_TAGS column names
	public static final String KEY_TAG_ID = "tag_id";
	public static final String KEY_RECEIPT_ID = "receipt_id";

	public static final String[] TABLE_RECEIPT_TAGS_KEYS = new String[] {KEY_ROWID,
			KEY_TAG_ID, KEY_RECEIPT_ID};

	// Column Numbers for each Field Name:
	public static final int RECEIPT_TAGS_COL_ROWID = 0;
	public static final int RECEIPT_TAGS_COL_TAG_ID = 1;
	public static final int RECEIPT_TAGS_COL_RECEIPT_ID = 2;



	// TABLE_REPORTS column names
	public static final String KEY_TITLE = "report_title";
	public static final String KEY_START_DATE = "start_date";
	public static final String KEY_END_DATE = "end_date";

	public static final String[] TABLE_REPORTS_KEYS = new String[] {KEY_ROWID,
			KEY_TITLE, KEY_START_DATE, KEY_END_DATE};

	// Column Numbers for each Field Name:
	public static final int REPORTS_COL_ROWID = 0;
	public static final int REPORTS_COL_TITLE = 1;
	public static final int REPORTS_COL_START_DATE = 2;
	public static final int REPORTS_COL_END_DATE = 3;



	// TABLE_REPORT_RECEIPTS column names
	public static final String KEY_REPORT_ID = "report_id";
	//public static final String KEY_RECEIPT_ID = "receipt_id"; ------------- same as in TABLE_RECEIPT_TAGS

	public static final String[] TABLE_REPORT_RECEIPTS_KEYS = new String[] {KEY_ROWID,
			KEY_REPORT_ID, KEY_RECEIPT_ID};

	// Column Numbers for each Field Name:
	public static final int REPORT_RECEIPTS_COL_ROWID = 0;
	public static final int REPORT_RECEIPTS_COL_REPORT_ID = 1;
	public static final int REPORT_RECEIPTS_COL_RECEIPT_ID = 2;


	//SQL statement to create TABLE_RECEIPTS
	private static final String CREATE_TABLE_RECEIPTS =
			"CREATE TABLE " + TABLE_RECEIPTS +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_PRICE + " TEXT, "
					+ KEY_IMAGE + " TEXT UNIQUE NOT NULL, "
					+ KEY_DATE + " TEXT, "
					+ KEY_NOTES + " TEXT, "
					+ KEY_CURRENCY_ID + " TEXT, "
					+ KEY_PRODUCT_ID + " TEXT, "
					+ KEY_VENDOR_ID + " TEXT, "
					+ KEY_CATEGORY_ID + " TEXT, "
					+ KEY_BEHALF_ID + " TEXT"
					+ ");";

	//SQL statement to create TABLE_CURRENCY
	private static final String CREATE_TABLE_CURRENCY =
			"CREATE TABLE " + TABLE_CURRENCY +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_CURRENCY_NAME + " TEXT UNIQUE NOT NULL, "
					+ KEY_CURRENCY_SYMBOL + " TEXT"
					+ ");";


	//SQL statement to create TABLE_PRODUCT KEY_ROWID, KEY_PRODUCT_NAME
	private static final String CREATE_TABLE_PRODUCT =
			"CREATE TABLE " + TABLE_PRODUCT +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_PRODUCT_NAME + " TEXT UNIQUE NOT NULL"
					+ ");";

	//SQL statement to create TABLE_VENDOR
	private static final String CREATE_TABLE_VENDOR =
			"CREATE TABLE " + TABLE_VENDOR +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_VENDOR_NAME + " TEXT UNIQUE NOT NULL"
					+ ");";

	//SQL statement to create TABLE_CATEGORY
	private static final String CREATE_TABLE_CATEGORY =
			"CREATE TABLE " + TABLE_CATEGORY +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_CATEGORY_NAME + " TEXT UNIQUE NOT NULL"
					+ ");";

	//SQL statement to create TABLE_BEHALF
	private static final String CREATE_TABLE_BEHALF =
			"CREATE TABLE " + TABLE_BEHALF +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_BEHALF_NAME + " TEXT UNIQUE NOT NULL"
					+ ");";

	//SQL statement to create TABLE_TAGS
	private static final String CREATE_TABLE_TAGS =
			"CREATE TABLE " + TABLE_TAGS +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_TAG + " TEXT UNIQUE NOT NULL"
					+ ");";

	//SQL statement to create TABLE_RECEIPT_TAGS KEY_ROWID, KEY_TAG_ID, KEY_RECEIPT_ID
	private static final String CREATE_TABLE_RECEIPT_TAGS =
			"CREATE TABLE " + TABLE_RECEIPT_TAGS +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_TAG_ID + " TEXT NOT NULL, "
					+ KEY_RECEIPT_ID + " TEXT NOT NULL"
					+ ");";

	//SQL statement to create TABLE_REPORTS KEY_ROWID, KEY_TITLE, KEY_START_DATE, KEY_END_DATE
	private static final String CREATE_TABLE_REPORTS =
			"CREATE TABLE " + TABLE_REPORTS +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_TITLE + " TEXT UNIQUE NOT NULL, "
					+ KEY_START_DATE + "TEXT, "
					+ KEY_END_DATE + "TEXT"
					+ ");";

	//SQL statement to create TABLE_REPORT_RECEIPTS
	private static final String CREATE_TABLE_REPORT_RECEIPTS =
			"CREATE TABLE " + TABLE_REPORT_RECEIPTS +
					" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_REPORT_ID + " TEXT NOT NULL, "
					+ KEY_RECEIPT_ID + " TEXT NOT NULL"
					+ ");";



	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;


	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	// Add new currency to TABLE_CURRENCY
	// KEY_ROWID, KEY_CURRENCY_NAME, KEY_CURRENCY_SYMBOL

	public long insertCurrencyRow(String name, String symbol){

		if(CheckIsDataAlreadyInDBorNot(db, TABLE_CURRENCY, KEY_CURRENCY_NAME, name)){
			return GetItemID(db, TABLE_CURRENCY, KEY_CURRENCY_NAME, name);
		}
		else {
			ContentValues table_currency_values = new ContentValues();
			table_currency_values.put(KEY_CURRENCY_NAME, name);
			table_currency_values.put(KEY_CURRENCY_SYMBOL, symbol);
			return db.insert(TABLE_RECEIPTS, null, table_currency_values);
		}

	}

	public long insertProductRow(String name){

		if(CheckIsDataAlreadyInDBorNot(db, TABLE_PRODUCT, KEY_PRODUCT_NAME, name)){
			return GetItemID(db, TABLE_PRODUCT, KEY_PRODUCT_NAME, name);
		}
		else {
			ContentValues table_product_values = new ContentValues();
			table_product_values.put(KEY_PRODUCT_NAME, name);
			return db.insert(TABLE_PRODUCT, null, table_product_values);
		}

	}

	public long insertVendorRow(String name){

		if(CheckIsDataAlreadyInDBorNot(db, TABLE_VENDOR, KEY_VENDOR_NAME, name)){
			return GetItemID(db, TABLE_VENDOR, KEY_VENDOR_NAME, name);
		}
		else {
			ContentValues table_vendor_values = new ContentValues();
			table_vendor_values.put(KEY_VENDOR_NAME, name);
			return db.insert(TABLE_VENDOR, null, table_vendor_values);
		}

	}

	public long insertCategoryRow(String name){

		if(CheckIsDataAlreadyInDBorNot(db, TABLE_CATEGORY, KEY_CATEGORY_NAME, name)){
			return GetItemID(db, TABLE_CATEGORY, KEY_CATEGORY_NAME, name);
		}
		else {
			ContentValues table_category_values = new ContentValues();
			table_category_values.put(KEY_CATEGORY_NAME, name);
			return db.insert(TABLE_CATEGORY, null, table_category_values);
		}

	}

	public long insertBehalfRow(String name){

		if(CheckIsDataAlreadyInDBorNot(db, TABLE_BEHALF, KEY_BEHALF_NAME, name)){
			return GetItemID(db, TABLE_BEHALF, KEY_BEHALF_NAME, name);
		}
		else {
			ContentValues table_behalf_values = new ContentValues();
			table_behalf_values.put(KEY_BEHALF_NAME, name);
			return db.insert(TABLE_BEHALF, null, table_behalf_values);
		}
	}

	public long getTagId(String tag){
		return GetItemID(db, TABLE_TAGS, KEY_TAG, tag);
	};
	// TODO: 07/05/17 - continue handling tags
	
	// Add a new set of values to be inserted into the database.
	public long insertRow(String price, String image,
						  String date, String notes, String currency, String product, String vendor,
						  String category, String behalf) {

		String currency_id = String.valueOf(insertCurrencyRow("dollar", "$"));
		String product_id = String.valueOf(insertProductRow(product));
		String vendor_id = String.valueOf(insertVendorRow(vendor));
		String category_id = String.valueOf(insertCategoryRow(category));
		String behalf_id = String.valueOf(insertBehalfRow(behalf));


		// TODO: 06/05/17 - receipt_table_values.put(KEY_TAG, tags);

		ContentValues table_receipts_values = new ContentValues();
		table_receipts_values.put(KEY_PRICE, price);
		table_receipts_values.put(KEY_IMAGE, image);
		table_receipts_values.put(KEY_DATE, date);
		table_receipts_values.put(KEY_NOTES, notes);
		table_receipts_values.put(KEY_CURRENCY_ID, currency_id);
		table_receipts_values.put(KEY_PRODUCT_ID, product_id);
		table_receipts_values.put(KEY_VENDOR_ID, vendor_id);
		table_receipts_values.put(KEY_CATEGORY_ID, category_id);
		table_receipts_values.put(KEY_BEHALF_ID, behalf_id);


				
		// Insert the data into the database.
		return db.insert(TABLE_RECEIPTS, null, table_receipts_values);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(TABLE_RECEIPTS, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, TABLE_RECEIPTS, TABLE_RECEIPT_KEYS, where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, TABLE_RECEIPTS, TABLE_RECEIPT_KEYS,
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String price, String image, String date, String notes, String currency,
							 String product, String vendor, String category, String behalf) {

		String currency_id = String.valueOf(insertCurrencyRow(currency, ""));
		String product_id = String.valueOf(insertProductRow(product));
		String vendor_id = String.valueOf(insertVendorRow(vendor));
		String category_id = String.valueOf(insertCategoryRow(category));
		String behalf_id = String.valueOf(insertBehalfRow(behalf));

		String where = KEY_ROWID + "=" + rowId;
		ContentValues table_receipts_newValues = new ContentValues();
		table_receipts_newValues.put(KEY_PRICE, price);
		table_receipts_newValues.put(KEY_IMAGE, image);
		table_receipts_newValues.put(KEY_DATE, date);
		table_receipts_newValues.put(KEY_NOTES, notes);
		table_receipts_newValues.put(KEY_CURRENCY_ID, currency_id);
		table_receipts_newValues.put(KEY_PRODUCT_ID, product_id);
		table_receipts_newValues.put(KEY_VENDOR_ID, vendor_id);
		table_receipts_newValues.put(KEY_CATEGORY_ID, category_id);
		table_receipts_newValues.put(KEY_BEHALF_ID, behalf_id);
		// Insert it into the database.
		return db.update(TABLE_RECEIPTS, table_receipts_newValues, where, null) != 0;
	}

	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(CREATE_TABLE_RECEIPTS);
			_db.execSQL(CREATE_TABLE_CURRENCY);
			_db.execSQL(CREATE_TABLE_PRODUCT);
			_db.execSQL(CREATE_TABLE_VENDOR);
			_db.execSQL(CREATE_TABLE_CATEGORY);
			_db.execSQL(CREATE_TABLE_BEHALF);
			_db.execSQL(CREATE_TABLE_TAGS);
			_db.execSQL(CREATE_TABLE_RECEIPT_TAGS);
			_db.execSQL(CREATE_TABLE_REPORTS);
			_db.execSQL(CREATE_TABLE_REPORT_RECEIPTS);



		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPTS);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENCY);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEHALF);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT_TAGS);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
			_db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT_RECEIPTS);
			
			// Recreate new database:
			onCreate(_db);
		}
	}

	// taken from http://stackoverflow.com/questions/20415309/android-sqlite-how-to-check-if-a-record-exists
	public static boolean CheckIsDataAlreadyInDBorNot(SQLiteDatabase sqldb, String TableName,
													  String dbfield, String fieldValue) {

		// TODO: 08/05/17  fix this query
		String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
		Cursor cursor = sqldb.rawQuery(Query, null);
		if(cursor.getCount() <= 0){
			cursor.close();
			return false;
		}
		cursor.close();
		return true;
	}

	public static long GetItemID(SQLiteDatabase sqldb, String TableName, String dbfield,
								 String fieldValue) {

		String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
		Cursor cursor = sqldb.rawQuery(Query, null);
		long out = -1;
		if(cursor.getCount() > 0){
			out = cursor.getLong(0);
		}
		cursor.close();
		return out;
	}
}
