/*    */ package com.abrstech.obd2.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ import java.util.Properties;
/*    */ import javax.mail.Address;
/*    */ import javax.mail.Message;
/*    */ import javax.mail.Message.RecipientType;
/*    */ import javax.mail.MessagingException;
/*    */ import javax.mail.SendFailedException;
/*    */ import javax.mail.Session;
/*    */ import javax.mail.Transport;
/*    */ import javax.mail.internet.InternetAddress;
/*    */ import javax.mail.internet.MimeMessage;
/*    */ 
/*    */ public class SmtpMailer
/*    */   implements Runnable
/*    */ {
/* 14 */   String smtpHost = "127.0.0.1";
/* 15 */   static String from = "NoReply@autotalky.com";
/*    */ 
/*    */   public void sendMessageToAddress(String toAddress, String subject, String content)
/*    */   {
/* 20 */     boolean debug = false;
/*    */ 
/* 23 */     Properties props = new Properties();
/* 24 */     props.put("mail.smtp.host", this.smtpHost);
/*    */ 
/* 26 */     Session session = Session.getDefaultInstance(props, null);
/* 27 */     session.setDebug(debug);
/*    */     try
/*    */     {
/* 30 */       Message msg = new MimeMessage(session);
/*    */ 
/* 32 */       msg.setFrom(new InternetAddress(from));
/* 33 */       InternetAddress[] address = { new InternetAddress(toAddress) };
/* 34 */       msg.setRecipients(Message.RecipientType.TO, address);
/* 35 */       msg.setSubject(subject);
/* 36 */       msg.setSentDate(new Date());
/* 37 */       msg.setText(content);
/* 38 */       Transport.send(msg);
/*    */     } catch (MessagingException mex) {
/* 40 */       System.out.println(mex.toString());
/* 41 */       Exception ex = mex;
/*    */       do {
/* 43 */         if ((ex instanceof SendFailedException)) {
/* 44 */           SendFailedException sfex = (SendFailedException)ex;
/* 45 */           Address[] invalid = sfex.getInvalidAddresses();
/* 46 */           if (invalid != null) {
/* 47 */             System.out.println("    ** Invalid Addresses");
/* 48 */             if (invalid != null) {
/* 49 */               for (int i = 0; i < invalid.length; i++)
/* 50 */                 System.out.println("         " + invalid[i]);
/*    */             }
/*    */           }
/* 53 */           Address[] validUnsent = sfex.getValidUnsentAddresses();
/* 54 */           if (validUnsent != null) {
/* 55 */             System.out.println("    ** ValidUnsent Addresses");
/* 56 */             if (validUnsent != null) {
/* 57 */               for (int i = 0; i < validUnsent.length; i++)
/* 58 */                 System.out.println("         " + validUnsent[i]);
/*    */             }
/*    */           }
/* 61 */           Address[] validSent = sfex.getValidSentAddresses();
/* 62 */           if (validSent != null) {
/* 63 */             System.out.println("    ** ValidSent Addresses");
/* 64 */             if (validSent != null) {
/* 65 */               for (int i = 0; i < validSent.length; i++)
/* 66 */                 System.out.println("         " + validSent[i]);
/*    */             }
/*    */           }
/*    */         }
/* 70 */         System.out.println();
/* 71 */         if ((ex instanceof MessagingException))
/* 72 */           ex = ((MessagingException)ex).getNextException();
/*    */         else
/* 74 */           ex = null; 
/*    */       }
/* 75 */       while (ex != null);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */   }
/*    */ }

/* Location:           /home/bmutia/Development/workspace/ivweb2/src/
 * Qualified Name:     com.abrstech.obd2.util.SmtpMailer
 * JD-Core Version:    0.6.2
 */