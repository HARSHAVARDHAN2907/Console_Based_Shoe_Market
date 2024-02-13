import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetFactory;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
public class Model {
    Connection con;
    public Model() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int PutSignUpDetialsToDb(String email, String name, String phone, String role,String pass, String gender, String dob) {
        
      String q = "Select * from User_Details where user_email=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(q);
         pst.setString(1, email);
         ResultSet res=pst.executeQuery();
         if(res.next()){
            return 9;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      String qr = "Select * from User_Details where user_phone=?";
      try {
         pst=con.prepareStatement(qr);
         pst.setString(1, phone);
         ResultSet res=pst.executeQuery();
         if(res.next()){
            return 10;
         }
      } catch (Exception e) {
      }
      String query = "INSERT INTO User_Details (user_email, user_name, user_phone, user_role,user_pass, user_gender, user_dob) VALUES (?, ?, ?,?, ?, ?, ?)";
        try {
           pst = con.prepareStatement(query);
           pst.setString(1,email);
          pst.setString(2,name);
          pst.setString(3,phone);
          pst.setString(4,role);
          pst.setString(5,pass);
          pst.setString(6,gender);
          pst.setString(7,dob);
          int p=pst.executeUpdate();
        //   System.out.println(p);
          if(p!=0){
            return 1;
         //   System.out.println("Registered Successfully");
          }
        } catch (SQLException e) {
           e.printStackTrace();
        }
          return 0;
      }
      public int CheckLoginDetailsInDB(String email,String pass){
        String query = "Select user_pass from User_Details where user_email=?";
        PreparedStatement pst;
        try{
        pst=con.prepareStatement(query);
        pst.setString(1, email);
        ResultSet res=pst.executeQuery();
        if(res.next()){
           String user_pass=res.getString("user_pass");
           if(user_pass.equals(pass)){
            //   System.out.println("Login Successful");
               String q="SELECT user_role from User_Details where user_email=?";
               try {
                 pst=con.prepareStatement(q);
                 pst.setString(1, email);
                 ResultSet rs=pst.executeQuery();
                 if(rs.next()){
                    if(rs.getString("user_role").equals("User")){
                    return 1;
                    }
                    else if(rs.getString("user_role").equals("Admin")){
                    return 2;
                    }
                 }
               } catch (Exception e) {
               }
           }
           else{
            return 0;
           }
        }
        else{
           return -1;
        }
        }
        catch(Exception e){
           System.out.println(e);
        }
        return 1;
      }
      public int getUserId(String email){
        String query = "Select user_id from User_Details where user_email=?";
        PreparedStatement pst;
        try {
            pst=con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet res=pst.executeQuery();
            if(res.next()){
                return  res.getInt("user_id");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 1;
      }
      public int putShoeDetails(String name,String brand,int size,int price,int count,String gender){
         String q = "Select * from shoes where sname=? and  sbrand=? and ssize=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setString(1, name);
            pst.setString(2, brand);
            pst.setInt(3, size);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
               return 99;
            }
         } catch (Exception e) {
         }
         String  query = "Insert into shoes(sname,sbrand,ssize,scount,sprice,gender) values(?,?,?,?,?,?)";
         try{
            pst=con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, brand);
            pst.setInt(3,size);
            pst.setInt(4,count);
            pst.setInt(5, price);
            pst.setString(6, gender);
            int x=pst.executeUpdate();
            // System.out.println(x);
            if(x==1){
               return x;
            }
            else{
               return 0;
            }
         }
         catch(Exception e){
            System.out.println(e);
            return 0;
         }
      }

      public int deleteById(int id){
         String q="Delete from shoes where sid=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setInt(1, id);
            int x=pst.executeUpdate();
            if(x==1){
               return x;
            }
            else{
               return 99;
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }
      public int deleteByBrand(String brand){
         String q="Delete from shoes where sbrand=?";
         PreparedStatement pst;
         try{
            pst=con.prepareStatement(q);
            pst.setString(1, brand);
            int x=pst.executeUpdate();
            if(x==1){
               return 1;
            }
            else{
               return 99;
            }
         }
         catch(Exception e){

         }
         return 0;
      }

      public int deleteBySize(int size){
         String q="Delete from shoes where ssize=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setInt(1, size);
            int x=pst.executeUpdate(); 
            if(x==1){
               return x;
            }
            else{
               return 99;
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }

      public int viewById(int id){
         String q="Select * from shoes where sid=?";
         PreparedStatement pst;
         try {
         pst=con.prepareStatement(q);
         pst.setInt(1, id);
         ResultSet rs=pst.executeQuery();
         int count=0;
         while(rs.next()){
            count++;
            System.out.println();
            System.out.println("ID: "+rs.getInt(1)+" | Name: "+rs.getString(2)+" | Brand: "+rs.getString(3)+" | Size: "+rs.getInt(4)+" | Pieces Left: "+rs.getInt(5)+" | Price: "+rs.getInt(6)+" | Worn By: "+rs.getString(7));
         }
         if(count>0){
            return 1;
         }
         else{
            return 99;
         }
         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }

      public int viewByBrand(String brand){
         String q="Select * from shoes where sbrand=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setString(1, brand);
            ResultSet rs = pst.executeQuery();
            int count=0;
            while(rs.next()){
               count++;
               System.out.println();
            System.out.println("ID: "+rs.getInt(1)+" | Name: "+rs.getString(2)+" | Brand: "+rs.getString(3)+" | Size: "+rs.getInt(4)+" | Pieces Left: "+rs.getInt(5)+" | Price: "+rs.getInt(6)+" | Worn By: "+rs.getString(7));
         }
         if(count>0){
            return 1;
         }
         else{
            return 99;
         }
         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }

      public int viewBySize(int size){
         String q="Select * from shoes where ssize=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setInt(1, size);
            ResultSet rs = pst.executeQuery();
            int count=0;
            while(rs.next()){
               count++;
               System.out.println();
            System.out.println("ID: "+rs.getInt(1)+" | Name: "+rs.getString(2)+" | Brand: "+rs.getString(3)+" | Size: "+rs.getInt(4)+" | Pieces Left: "+rs.getInt(5)+" | Price: "+rs.getInt(6)+" | Worn By: "+rs.getString(7));
         }
         if(count>0){
            return 1;
         }
         else{
            return 99;
         }
         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }
      

      public int updateIntValue(int id,String field,int val){
         // System.out.println("called");
         String q="Update shoes set "+field+"=? where sid=?";
         PreparedStatement pst;
         try {
            // System.out.println("came inside");
            pst=con.prepareStatement(q);
            pst.setInt(1, val);
            pst.setInt(2, id);
            int x = pst.executeUpdate();
            // System.out.println(x);
            if(x==1){
               return x;
            }
            else{
               return 99;
            }

         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }
      public int updateValue(int id,String field,String val){
         String q="Update shoes set "+field+"=? where sid=?";
         PreparedStatement pst;
         try {
            pst=con.prepareStatement(q);
            pst.setString(1, val);
            pst.setInt(2, id);
            int x = pst.executeUpdate();
            System.out.println(x);
            if(x==1){
               return x;
            }
            else{
               return 99;
            }

         } catch (Exception e) {
            // TODO: handle exception
         }
         return 0;
      }

      public int viewByPrice(int val) {
         String q = "SELECT * FROM shoes WHERE CAST(sprice AS DECIMAL) <= ? ORDER BY CAST(sprice AS DECIMAL)";
         PreparedStatement pst;
         int count = 0;
         
         try {
             pst = con.prepareStatement(q);
             pst.setInt(1, val);
             ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                 count++;
                 System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7));
             }
            //  System.out.println(count);
             if (count == 0) {
                 return 99;
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         
         return count;
     }
      public int viewBySex(String sex) {
         String q = "SELECT * FROM shoes WHERE gender=?";
         PreparedStatement pst;
         int count = 0;
         try {
             pst = con.prepareStatement(q);
             pst.setString(1, sex);
             ResultSet rs = pst.executeQuery();
             while (rs.next()) {
                 count++;
                 System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7));
             }
            //  System.out.println(count);
             if (count == 0) {
                 return 99;
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         
         return count;
     }


     public int addtoCart(int id,int uid){
      String q="Select * from shoes where sid=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(q);
         pst.setInt(1, id);
         ResultSet k=pst.executeQuery();
         if(k.next()){
            String t="Select * from cart where user_id=? and sid=?";
            pst=con.prepareStatement(t);
            pst.setInt(1, uid);
            pst.setInt(2, id);
            ResultSet op=pst.executeQuery();
            if(!op.next()){
            String y="INSERT INTO cart(user_id,sid) values(?,?)";
            pst=con.prepareStatement(y);
            pst.setInt(1, uid);
            pst.setInt(2, id);
            int f=pst.executeUpdate();
            if(f==1){
               String u="Select * from shoes where sid=?";
               pst=con.prepareStatement(u);
               pst.setInt(1, id);
               ResultSet rs=pst.executeQuery();
               while(rs.next()){
                 System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7));
               }
               return 200;
            }
         }
         else{
            return 999;
         }
      }
      else{
         return -1;
      }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
     }

     public int place(int sid,int id){
      String q="Select * from shoes where sid=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(q);
         pst.setInt(1, sid);
         ResultSet k=pst.executeQuery();
         if(k.next()){
            String y="INSERT INTO orders(user_id,sid,odate) values(?,?,CURDATE())";
            pst=con.prepareStatement(y);
            pst.setInt(1, id);
            pst.setInt(2, sid);
            int f=pst.executeUpdate();
            if(f==1){
               String u="Select * from shoes where sid=?";
               pst=con.prepareStatement(u);
               pst.setInt(1, sid);
               ResultSet rs=pst.executeQuery();
               while(rs.next()){
                 System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7));
               }
               return 200;
            }
         }
         else{
            return -1;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
     }

public int viewCart(int id){
   String q="Select * from cart where user_id=?";
   PreparedStatement pst;
   int count=0;
   try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet v=pst.executeQuery();
      System.out.println("Displaying your cart");
      while(v.next()){
         count++;
         String h="Select * from shoes where sid=?";
         pst=con.prepareStatement(h);
         pst.setInt(1, v.getInt(3));
         ResultSet rs=pst.executeQuery();
         while(rs.next()){
            System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7));
         }
      }
   } catch (Exception e) {
      // TODO: handle exception
   }
   return count;
}
public int viewOrders(int id){
   String q="Select * from orders where user_id=?";
   PreparedStatement pst;
   int count=0;
   try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet v=pst.executeQuery();
      System.out.println("Displaying your order history");
      while(v.next()){
         count++;
         String h="Select * from shoes where sid=?";
         pst=con.prepareStatement(h);
         pst.setInt(1, v.getInt(3));
         ResultSet rs=pst.executeQuery();
         while(rs.next()){
            System.out.println();
                 System.out.println("ID: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | Brand: " + rs.getString(3) + " | Size: " + rs.getInt(4) + " | Pieces Left: " + rs.getInt(5) + " | Price: " + rs.getInt(6) + " | Worn By: " + rs.getString(7)+" | Orderd On: "+v.getString(4));
         }
      }
   } catch (Exception e) {
      // TODO: handle exception
   }
   return count;
}

}
