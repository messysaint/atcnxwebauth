/*     */ package com.abrstech.sql;
/*     */ 
/*     */ //import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class searchVehicle
/*     */ {
/*  15 */   boolean dbConnectionOk = false;
/*     */ 
/*  17 */   private Connection conn = null;
/*  18 */   private Statement stmt = null;
/*  19 */   private ResultSet rs = null;
/*     */ 

/*     */ 
/*  23 */   static String SystemDT = new String();
/*     */ 

/*  26 */   private String Rvin = null;
            private String SQLString = null;


/*     */ 
/*     */   public String getVin()
/*     */   {
/*  51 */     return this.Rvin;
/*     */   }
/*     */ 
/*     */ 
/*     */   public searchVehicle()
/*     */   {
/*     */     try
/*     */     {
/*  98 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/*     */       try
/*     */       {
/* 101 */         this.conn = DriverManager.getConnection("jdbc:mysql://db.santamesa.com/abrstech_obd2db?user=ivwebobd2loguser&password=YuioHjklNm78");
/* 102 */         this.stmt = this.conn.createStatement();
/* 103 */         this.dbConnectionOk = true;
/* 104 */         System.out.println("trackUpload: DB connection is OK");
/*     */       }
/*     */       catch (SQLException ex) {
/* 107 */         System.out.println("trackUpload: SQLException: " + ex.getMessage());
/* 108 */         System.out.println("trackUpload: SQLState: " + ex.getSQLState());
/* 109 */         System.out.println("trackUpload: VendorError: " + ex.getErrorCode());
/*     */       }
/*     */     } catch (Exception ex) {
/* 112 */       System.out.println("trackUpload: SQLException: " + ex.getMessage());
/*     */     }
/*     */   }
/*     */ 

/*     */   public boolean isExisting(String vo_email, String vo_question, String vo_answer)
/*     */   {
/* 122 */     boolean rvalue = false;
/* 123 */     String SQLStr = null;
/*     */ 
/* 126 */     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 127 */     Date date = new Date();
/* 128 */     SystemDT = dateFormat.format(date);
/*     */ 
/* 132 */     if (this.dbConnectionOk)
/*     */     {
/*     */       try
/*     */       {
/* 146 */         SQLStr = "SELECT vin from abrstech_obd2db.ref_car_tbl where email=\"" + vo_email + "\" && question=\"" + vo_question + "\" && answer=\"" + vo_answer + "\"";
/* 147 */         System.out.println(SQLStr);
/* 148 */         this.rs = this.stmt.executeQuery(SQLStr);
/* 149 */         this.SQLString  = SQLStr;
/* 150 */         if (this.rs.first()) {
/* 151 */           rvalue = true;
/* 152 */           this.Rvin = this.rs.getString("vin");
/*     */         } else {
					this.Rvin = "";
/* 163 */           rvalue = false;
/*     */         }
/*     */       }
/*     */       catch (SQLException ex)
/*     */       {
/* 168 */         System.out.println("SQL: " + SQLStr);
/* 169 */         System.out.println("SQLException: " + ex.getMessage());
/* 170 */         System.out.println("SQLState: " + ex.getSQLState());
/* 171 */         System.out.println("VendorError: " + ex.getErrorCode());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 176 */     return rvalue;
/*     */   }


/*     */   public void CloseDB()
/*     */   {
/* 246 */     if (this.rs != null) {
/*     */       try {
/* 248 */         this.rs.close(); } catch (SQLException localSQLException) {
/*     */       }
/* 250 */       this.rs = null;
/*     */     }
/* 252 */     if (this.stmt != null) {
/*     */       try {
/* 254 */         this.stmt.close(); } catch (SQLException localSQLException1) {
/*     */       }
/* 256 */       this.stmt = null;
/*     */     }
/* 258 */     System.out.println("DB connection is closed");
/*     */   }
/*     */ }

/* Location:           /home/bmutia/Development/workspace/ivweb2/src/
 * Qualified Name:     com.abrstech.sql.searchVehicle
 * JD-Core Version:    0.6.2
 */