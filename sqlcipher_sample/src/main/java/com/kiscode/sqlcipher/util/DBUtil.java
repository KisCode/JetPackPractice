package com.kiscode.sqlcipher.util;

import android.content.ContentValues;
import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

public class DBUtil {
  private static final String ASSETS_DB_NAME = "dict_encrypted.db";
  
  private static String CUR_DB_NAME = "";
  
  private static String DATABASE_PATH = "/data/data/com.julyz.chengyudict/databases/";
  
  private static String PRE_DB_NAME = "";
  
  private static final String PWD = "zyw745293";
  
  public static boolean deleteFile(String paramString) {
    File file = new File(paramString);
    if (file.isFile() && file.exists()) {
      file.delete();
      return true;
    } 
    return false;
  }
  
  public static void fixHistoryErrors(SQLiteDatabase paramSQLiteDatabase) {
    paramSQLiteDatabase.execSQL("update dict set note=null where id=13821 or note='0'");
  }
  
  public static List<Integer> getFavoredList(SQLiteDatabase paramSQLiteDatabase) {
    ArrayList<Integer> arrayList = new ArrayList();
    Cursor cursor = null;
    try {
      Cursor cursor1 = paramSQLiteDatabase.rawQuery("select id from dict where fav=1", null);
      cursor = cursor1;
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    while (cursor.moveToNext())
      arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id")))); 
    cursor.close();
    paramSQLiteDatabase.close();
    return arrayList;
  }
  
  private static List<Map<String, String>> getNotedItems(SQLiteDatabase paramSQLiteDatabase) {
    ArrayList<HashMap<Object, Object>> arrayList = new ArrayList();
    Cursor cursor = paramSQLiteDatabase.rawQuery("SELECT name,note FROM dict where note is not null and replace(note,' ','')<>'' ", null);
    while (cursor.moveToNext()) {
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      String[] arrayOfString = cursor.getColumnNames();
      for (int i = 0; i < arrayOfString.length; i++)
        hashMap.put(arrayOfString[i], cursor.getString(cursor.getColumnIndex(arrayOfString[i]))); 
      arrayList.add(hashMap);
    } 
    cursor.close();
    paramSQLiteDatabase.close();
    return (List)arrayList;
  }
  
  public static SQLiteDatabase getSQLiteDatabase(Context paramContext) {
    String str = paramContext.getFilesDir().getPath();
    DATABASE_PATH = str.substring(0, str.lastIndexOf("/")) + "/databases/";
    CUR_DB_NAME = "dict_29.db";
//    LogUtils.w(DATABASE_PATH + CUR_DB_NAME);
    return getSQLiteDatabase(DATABASE_PATH + CUR_DB_NAME);
  }
  
  private static SQLiteDatabase getSQLiteDatabase(String paramString) {
    return SQLiteDatabase.openOrCreateDatabase(paramString, "zyw745293", null, new SQLiteDatabaseHook() {
          public void postKey(SQLiteDatabase param1SQLiteDatabase) {
            param1SQLiteDatabase.execSQL("PRAGMA cipher_page_size = 1024");
            param1SQLiteDatabase.execSQL("PRAGMA kdf_iter = 64000");
            param1SQLiteDatabase.execSQL("PRAGMA cipher_hmac_algorithm = HMAC_SHA1");
            param1SQLiteDatabase.execSQL("PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1");
          }
          
          public void preKey(SQLiteDatabase param1SQLiteDatabase) {}
        });
  }
  
  public static void init(Context paramContext) {
    // Byte code:
    //   0: ldc com/julyz/chengyudict/db/DBUtil
    //   2: monitorenter
    //   3: new java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial <init> : ()V
    //   10: ldc 'dict_'
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_0
    //   16: invokestatic getPackageVersionCode : (Landroid/content/Context;)I
    //   19: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   22: ldc '.db'
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: putstatic com/julyz/chengyudict/db/DBUtil.CUR_DB_NAME : Ljava/lang/String;
    //   33: new java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial <init> : ()V
    //   40: getstatic com/julyz/chengyudict/db/DBUtil.DATABASE_PATH : Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: getstatic com/julyz/chengyudict/db/DBUtil.CUR_DB_NAME : Ljava/lang/String;
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual toString : ()Ljava/lang/String;
    //   55: astore #6
    //   57: new java/io/File
    //   60: dup
    //   61: aload #6
    //   63: invokespecial <init> : (Ljava/lang/String;)V
    //   66: invokevirtual exists : ()Z
    //   69: istore_3
    //   70: iload_3
    //   71: ifeq -> 78
    //   74: ldc com/julyz/chengyudict/db/DBUtil
    //   76: monitorexit
    //   77: return
    //   78: new java/io/File
    //   81: dup
    //   82: getstatic com/julyz/chengyudict/db/DBUtil.DATABASE_PATH : Ljava/lang/String;
    //   85: invokespecial <init> : (Ljava/lang/String;)V
    //   88: astore #9
    //   90: aload #9
    //   92: invokevirtual exists : ()Z
    //   95: ifne -> 104
    //   98: aload #9
    //   100: invokevirtual mkdirs : ()Z
    //   103: pop
    //   104: iconst_0
    //   105: istore_1
    //   106: aconst_null
    //   107: astore #8
    //   109: aconst_null
    //   110: astore #4
    //   112: aconst_null
    //   113: astore #5
    //   115: aconst_null
    //   116: astore #7
    //   118: aload_0
    //   119: invokevirtual getAssets : ()Landroid/content/res/AssetManager;
    //   122: ldc 'dict_encrypted.db'
    //   124: invokevirtual open : (Ljava/lang/String;)Ljava/io/InputStream;
    //   127: astore_0
    //   128: aload_0
    //   129: astore #5
    //   131: new java/io/FileOutputStream
    //   134: dup
    //   135: aload #6
    //   137: invokespecial <init> : (Ljava/lang/String;)V
    //   140: astore #6
    //   142: sipush #2048
    //   145: newarray byte
    //   147: astore #4
    //   149: aload_0
    //   150: aload #4
    //   152: invokevirtual read : ([B)I
    //   155: istore_2
    //   156: iload_2
    //   157: ifle -> 172
    //   160: aload #6
    //   162: aload #4
    //   164: iconst_0
    //   165: iload_2
    //   166: invokevirtual write : ([BII)V
    //   169: goto -> 149
    //   172: aload_0
    //   173: astore #4
    //   175: aload #6
    //   177: invokevirtual flush : ()V
    //   180: aload_0
    //   181: astore #4
    //   183: aload #6
    //   185: invokevirtual close : ()V
    //   188: aload_0
    //   189: invokevirtual close : ()V
    //   192: goto -> 325
    //   195: astore #4
    //   197: aload #6
    //   199: astore #5
    //   201: goto -> 540
    //   204: astore #4
    //   206: aload #6
    //   208: astore #7
    //   210: aload #4
    //   212: astore #6
    //   214: goto -> 255
    //   217: astore #4
    //   219: aload #6
    //   221: astore #7
    //   223: aload #4
    //   225: astore #6
    //   227: goto -> 294
    //   230: astore #6
    //   232: goto -> 255
    //   235: astore #6
    //   237: aload #8
    //   239: astore #7
    //   241: goto -> 294
    //   244: astore #4
    //   246: aconst_null
    //   247: astore_0
    //   248: goto -> 540
    //   251: astore #6
    //   253: aconst_null
    //   254: astore_0
    //   255: aload #7
    //   257: astore #4
    //   259: aload_0
    //   260: astore #5
    //   262: aload #6
    //   264: invokevirtual printStackTrace : ()V
    //   267: aload_0
    //   268: astore #4
    //   270: aload #7
    //   272: invokevirtual flush : ()V
    //   275: aload_0
    //   276: astore #4
    //   278: aload #7
    //   280: invokevirtual close : ()V
    //   283: goto -> 188
    //   286: astore #6
    //   288: aconst_null
    //   289: astore_0
    //   290: aload #8
    //   292: astore #7
    //   294: aload #7
    //   296: astore #4
    //   298: aload_0
    //   299: astore #5
    //   301: aload #6
    //   303: invokevirtual printStackTrace : ()V
    //   306: aload_0
    //   307: astore #4
    //   309: aload #7
    //   311: invokevirtual flush : ()V
    //   314: aload_0
    //   315: astore #4
    //   317: aload #7
    //   319: invokevirtual close : ()V
    //   322: goto -> 188
    //   325: aload #9
    //   327: invokevirtual listFiles : ()[Ljava/io/File;
    //   330: astore_0
    //   331: aload_0
    //   332: arraylength
    //   333: istore_2
    //   334: iload_1
    //   335: iload_2
    //   336: if_icmpge -> 390
    //   339: aload_0
    //   340: iload_1
    //   341: aaload
    //   342: astore #4
    //   344: aload #4
    //   346: invokevirtual isFile : ()Z
    //   349: ifeq -> 589
    //   352: aload #4
    //   354: invokevirtual getName : ()Ljava/lang/String;
    //   357: getstatic com/julyz/chengyudict/db/DBUtil.CUR_DB_NAME : Ljava/lang/String;
    //   360: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   363: ifne -> 589
    //   366: aload #4
    //   368: invokevirtual getName : ()Ljava/lang/String;
    //   371: ldc 'dict'
    //   373: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   376: ifeq -> 589
    //   379: aload #4
    //   381: invokevirtual getName : ()Ljava/lang/String;
    //   384: putstatic com/julyz/chengyudict/db/DBUtil.PRE_DB_NAME : Ljava/lang/String;
    //   387: goto -> 390
    //   390: ldc ''
    //   392: getstatic com/julyz/chengyudict/db/DBUtil.PRE_DB_NAME : Ljava/lang/String;
    //   395: invokevirtual equals : (Ljava/lang/Object;)Z
    //   398: istore_3
    //   399: iload_3
    //   400: ifeq -> 407
    //   403: ldc com/julyz/chengyudict/db/DBUtil
    //   405: monitorexit
    //   406: return
    //   407: new java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial <init> : ()V
    //   414: getstatic com/julyz/chengyudict/db/DBUtil.DATABASE_PATH : Ljava/lang/String;
    //   417: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: getstatic com/julyz/chengyudict/db/DBUtil.CUR_DB_NAME : Ljava/lang/String;
    //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual toString : ()Ljava/lang/String;
    //   429: invokestatic getSQLiteDatabase : (Ljava/lang/String;)Lnet/sqlcipher/database/SQLiteDatabase;
    //   432: astore_0
    //   433: new java/lang/StringBuilder
    //   436: dup
    //   437: invokespecial <init> : ()V
    //   440: getstatic com/julyz/chengyudict/db/DBUtil.DATABASE_PATH : Ljava/lang/String;
    //   443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: getstatic com/julyz/chengyudict/db/DBUtil.PRE_DB_NAME : Ljava/lang/String;
    //   449: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: invokevirtual toString : ()Ljava/lang/String;
    //   455: invokestatic getSQLiteDatabase : (Ljava/lang/String;)Lnet/sqlcipher/database/SQLiteDatabase;
    //   458: astore #4
    //   460: aload #4
    //   462: invokestatic getFavoredList : (Lnet/sqlcipher/database/SQLiteDatabase;)Ljava/util/List;
    //   465: aload_0
    //   466: invokestatic migrateFavoredItems : (Ljava/util/List;Lnet/sqlcipher/database/SQLiteDatabase;)V
    //   469: aload #4
    //   471: invokestatic getNotedItems : (Lnet/sqlcipher/database/SQLiteDatabase;)Ljava/util/List;
    //   474: aload_0
    //   475: invokestatic migrateNotedItems : (Ljava/util/List;Lnet/sqlcipher/database/SQLiteDatabase;)V
    //   478: goto -> 488
    //   481: astore #5
    //   483: aload #5
    //   485: invokevirtual printStackTrace : ()V
    //   488: aload #4
    //   490: invokevirtual close : ()V
    //   493: aload_0
    //   494: invokevirtual close : ()V
    //   497: new java/lang/StringBuilder
    //   500: dup
    //   501: invokespecial <init> : ()V
    //   504: getstatic com/julyz/chengyudict/db/DBUtil.DATABASE_PATH : Ljava/lang/String;
    //   507: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: getstatic com/julyz/chengyudict/db/DBUtil.PRE_DB_NAME : Ljava/lang/String;
    //   513: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: invokevirtual toString : ()Ljava/lang/String;
    //   519: invokestatic deleteFile : (Ljava/lang/String;)Z
    //   522: pop
    //   523: ldc com/julyz/chengyudict/db/DBUtil
    //   525: monitorexit
    //   526: return
    //   527: astore #6
    //   529: aload #5
    //   531: astore_0
    //   532: aload #4
    //   534: astore #5
    //   536: aload #6
    //   538: astore #4
    //   540: aload #5
    //   542: invokevirtual flush : ()V
    //   545: aload #5
    //   547: invokevirtual close : ()V
    //   550: aload_0
    //   551: invokevirtual close : ()V
    //   554: aload #4
    //   556: athrow
    //   557: astore_0
    //   558: ldc com/julyz/chengyudict/db/DBUtil
    //   560: monitorexit
    //   561: goto -> 566
    //   564: aload_0
    //   565: athrow
    //   566: goto -> 564
    //   569: astore_0
    //   570: aload #4
    //   572: astore_0
    //   573: goto -> 188
    //   576: astore_0
    //   577: goto -> 325
    //   580: astore #5
    //   582: goto -> 550
    //   585: astore_0
    //   586: goto -> 554
    //   589: iload_1
    //   590: iconst_1
    //   591: iadd
    //   592: istore_1
    //   593: goto -> 334
    // Exception table:
    //   from	to	target	type
    //   3	70	557	finally
    //   78	104	557	finally
    //   118	128	286	java/io/FileNotFoundException
    //   118	128	251	java/io/IOException
    //   118	128	244	finally
    //   131	142	235	java/io/FileNotFoundException
    //   131	142	230	java/io/IOException
    //   131	142	527	finally
    //   142	149	217	java/io/FileNotFoundException
    //   142	149	204	java/io/IOException
    //   142	149	195	finally
    //   149	156	217	java/io/FileNotFoundException
    //   149	156	204	java/io/IOException
    //   149	156	195	finally
    //   160	169	217	java/io/FileNotFoundException
    //   160	169	204	java/io/IOException
    //   160	169	195	finally
    //   175	180	569	java/io/IOException
    //   175	180	557	finally
    //   183	188	569	java/io/IOException
    //   183	188	557	finally
    //   188	192	576	java/io/IOException
    //   188	192	557	finally
    //   262	267	527	finally
    //   270	275	569	java/io/IOException
    //   270	275	557	finally
    //   278	283	569	java/io/IOException
    //   278	283	557	finally
    //   301	306	527	finally
    //   309	314	569	java/io/IOException
    //   309	314	557	finally
    //   317	322	569	java/io/IOException
    //   317	322	557	finally
    //   325	334	557	finally
    //   344	387	557	finally
    //   390	399	557	finally
    //   407	460	557	finally
    //   460	478	481	java/lang/Exception
    //   460	478	557	finally
    //   483	488	557	finally
    //   488	523	557	finally
    //   540	550	580	java/io/IOException
    //   540	550	557	finally
    //   550	554	585	java/io/IOException
    //   550	554	557	finally
    //   554	557	557	finally
  }
  
  private static void migrateFavoredItems(List<Integer> paramList, SQLiteDatabase paramSQLiteDatabase) {
    for (int i = 0; i < paramList.size(); i++) {
      ContentValues contentValues = new ContentValues();
      contentValues.put("fav", Integer.valueOf(1));
      paramSQLiteDatabase.update("dict", contentValues, "id=?", new String[] { (new StringBuilder()).append(paramList.get(i)).append("").toString() });
    } 
    paramSQLiteDatabase.close();
  }
  
  private static void migrateNotedItems(List<Map<String, String>> paramList, SQLiteDatabase paramSQLiteDatabase) {
    for (int i = 0; i < paramList.size(); i++) {
      Map map = paramList.get(i);
      paramSQLiteDatabase.execSQL("update dict set note='" + (String)map.get("note") + "' where name='" + (String)map.get("name") + "'");
    } 
  }
}
