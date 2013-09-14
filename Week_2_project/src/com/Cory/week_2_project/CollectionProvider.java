package com.Cory.week_2_project;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class CollectionProvider extends ContentProvider{

	FileManager m_file;
    String fileName = "returned_json.txt";
	
	public static final String AUTHORITY = "com.Cory.week_2_project.collectionprovider";
	
	public static class itunesData implements BaseColumns{
		
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/items");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.Cory.week_2_project.item";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.Cory.week_2_project.item";
		
		// define colums
		public static final String ARTIST_COLUMN = "artist";
		public static final String GENRE_COLUMN = "genre";
		public static final String URL_COLUMN = "url";
		
		public static final String[] PROJECTION = {"_Id, ARTIST_COLUMN, GENRE_COLUMN, URL_COLUMN"};
		
		private itunesData() {};
	}
	
	public static final int ITEMS = 1;
	public static final int ITEMS_ID = 2;
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		uriMatcher.addURI(AUTHORITY, "items/", ITEMS);
		uriMatcher.addURI(AUTHORITY, "items/#", ITEMS_ID);
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		
		switch(uriMatcher.match(uri)){
		case ITEMS:
			return itunesData.CONTENT_TYPE;
			
		
		case ITEMS_ID:
			return itunesData.CONTENT_ITEM_TYPE;
		
		}
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		
		MatrixCursor result = new MatrixCursor(itunesData.PROJECTION);
		
		String JSONStorage = m_file.readStringFile(getContext(), fileName);
		
		JSONObject job = null;
		JSONArray recordArray = null;
		
		
		switch(uriMatcher.match(uri)){
		case ITEMS:
			
			
		
		case ITEMS_ID:
			
			
		}
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
