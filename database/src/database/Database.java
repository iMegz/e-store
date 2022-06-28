/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author omara
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/market";
    static final String USER = "root";
    static final String PASS = "root";
    static boolean e = false;
    static Database d;

    private Database(){         
    }
   
    static public Database create(){
        if(!e){          
            d = new Database();
            e = true;
            return d;
       }
        return d;
   }
      
    synchronized void addUser(int id,String usrname, String pword, String Fname, String Lname, String email){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){            
            PreparedStatement psCheckUserExists = null;
            PreparedStatement psCheckEmailExists = null;
            PreparedStatement psCheckidExists = null;
            ResultSet userSet = null;
            ResultSet emailSet = null;
            ResultSet idSet = null;
            psCheckUserExists = conn.prepareStatement("SELECT * FROM customer WHERE userName = ?");
            psCheckUserExists.setString(1, usrname);
            userSet = psCheckUserExists.executeQuery();
           
            psCheckEmailExists = conn.prepareStatement("SELECT * FROM customer WHERE email = ?");
            psCheckEmailExists.setString(1, email);
            emailSet = psCheckEmailExists.executeQuery();
           
            psCheckidExists = conn.prepareStatement("SELECT * FROM customer WHERE customer_id = ?");
            psCheckidExists.setString(1,"" + id);
            idSet = psCheckidExists.executeQuery();
           
            if(userSet.isBeforeFirst() || emailSet.isBeforeFirst() || idSet.isBeforeFirst()){               
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "already exists");
            }
           
            else{              
                String sqlInsert = "insert into customer values ("+id+",\""+Fname+"\",\""+Lname+"\",\""+usrname+"\",\""+email+"\","+pword+",0)";
                int countInserted = stmt.executeUpdate(sqlInsert);
                sqlInsert = "insert into cart values ("+id+","+id+")";
                countInserted = stmt.executeUpdate(sqlInsert);                  
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Added");
            }
        } catch (SQLException e) {
          e.printStackTrace();
       }    
    }

    synchronized void addAdmin(int id,String usrname, String pword, String Fname, String Lname, String email){
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
            PreparedStatement psCheckUserExists = null;
            PreparedStatement psCheckEmailExists = null;
            PreparedStatement psCheckidExists = null;
            ResultSet userSet = null;
            ResultSet emailSet = null;
            ResultSet idSet = null;
            psCheckUserExists = conn.prepareStatement("SELECT * FROM admin WHERE userName = ?");
            psCheckUserExists.setString(1, usrname);
            userSet = psCheckUserExists.executeQuery();
           
            psCheckEmailExists = conn.prepareStatement("SELECT * FROM admin WHERE email = ?");
            psCheckEmailExists.setString(1, email);
            emailSet = psCheckEmailExists.executeQuery();
           
            psCheckidExists = conn.prepareStatement("SELECT * FROM admin WHERE idadmin = ?");
            psCheckidExists.setString(1,"" + id);
            idSet = psCheckidExists.executeQuery();
           
            if(userSet.isBeforeFirst() || emailSet.isBeforeFirst() || idSet.isBeforeFirst()){               
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "already exists");
            }
            else{  
                String sqlInsert = "insert into admin values ("+id+",\""+Fname+"\",\""+Lname+"\",\""+usrname+"\",\""+email+"\","+pword+")";
                int countInserted = stmt.executeUpdate(sqlInsert);
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Added");
            }            

       } catch (SQLException e) {
          e.printStackTrace();
       }    

    }

    synchronized void addProduct(int product_id,String pname, int category_id, int price, int stock, String status){
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
            PreparedStatement psCheckidExists = null;
            ResultSet idSet = null;
            psCheckidExists = conn.prepareStatement("SELECT * FROM product WHERE product_id = ?");
            psCheckidExists.setString(1,""+ product_id);
            idSet = psCheckidExists.executeQuery();
           
            if(idSet.isBeforeFirst()){               
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "already exists");
            }
            else{  
                String sqlInsert = "insert into product values ("+product_id+",\""+pname+"\",\""+category_id+"\",\""+price+"\",\""+stock+"\",\""+status+"\")";
                int countInserted = stmt.executeUpdate(sqlInsert);
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Added");
            }

       } catch (SQLException e) {
          e.printStackTrace();
       }    
    }
    
    synchronized void addCategory(int category_id,String cname,String status){
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
            PreparedStatement psChecknameExists = null;
            PreparedStatement psCheckidExists = null;
            ResultSet idSet = null;
            ResultSet nameSet = null;
            psCheckidExists = conn.prepareStatement("SELECT * FROM category WHERE idcategory = ?");
            psCheckidExists.setString(1,""+ category_id);
            idSet = psCheckidExists.executeQuery();
           
            psChecknameExists = conn.prepareStatement("SELECT * FROM category WHERE Cname = ?");
            psChecknameExists.setString(1, cname);
            nameSet = psChecknameExists.executeQuery();
           
            if(nameSet.isBeforeFirst() || idSet.isBeforeFirst()){               
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "already exists");
            }
            else{  
                String sqlInsert = "insert into category values ("+category_id+",\""+cname+"\",\""+status+"\")";
                int countInserted = stmt.executeUpdate(sqlInsert);
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Added");

            }
       } catch (SQLException e) {
          e.printStackTrace();
       }    

    }  
    
    synchronized void addOrder(int order_id,int product_id,int customer_id, String date, int quantity){
       try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
            PreparedStatement psCheckOrderidExists = null;
            ResultSet orderidSet = null;
            String strSelect = "select stock from product where product_id ="+product_id +";";
            ResultSet r = stmt.executeQuery(strSelect);
            int stock = 0;
            while(r.next()){
                stock = r.getInt("stock");
            }
            if(quantity > stock){
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Not enough in stock");
            }
            else{
                String sqlInsert = "update product set stock = stock - "+quantity+" where product_id = " + product_id +";";
                int countInserted = stmt.executeUpdate(sqlInsert);
            }              
            psCheckOrderidExists = conn.prepareStatement("SELECT * FROM _order WHERE order_id = ?");
            psCheckOrderidExists.setString(1, ""+ order_id);
            orderidSet = psCheckOrderidExists.executeQuery();
           int total_amount = 0;
            if(!orderidSet.isBeforeFirst()){               
                String sqlInsert = "insert into _order values ("+order_id+","+customer_id+",\""+date+"\","+0+")";
                int countInserted = stmt.executeUpdate(sqlInsert);                
            }
            String sqlInsert = "insert into order_product values ("+product_id+","+order_id+","+quantity+")";
            int countInserted = stmt.executeUpdate(sqlInsert);
            sqlInsert = "select price from product where product_id = "+product_id+";";
            r = stmt.executeQuery(sqlInsert);
            int price = 0;
            while(r.next()){
                price = r.getInt("price");               
                System.out.println(price);
            }
            total_amount = price * quantity;
            sqlInsert = "update _order set total_amount = total_amount + "+total_amount+" where order_id = " + order_id +";";
            countInserted = stmt.executeUpdate(sqlInsert);            
            JFrame parent = new JFrame();
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JOptionPane.showMessageDialog(parent, "Added");
            
       } catch (SQLException e) {
          e.printStackTrace();
       }    
    }

    synchronized int count_users(){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select count(*) as Ccount from customer";
                  ResultSet r = stmt.executeQuery(strSelect);
                  r.next();
                  return r.getInt("Ccount");
            } catch (SQLException e) {
               e.printStackTrace();
            }    
              return 0;
    }

    synchronized int count_products(){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select count(*) as Ccount from product";
                  ResultSet r = stmt.executeQuery(strSelect);
                  r.next();
                  return r.getInt("Ccount");
            } catch (SQLException e) {
               e.printStackTrace();
            }    
              return 0;
    }

    synchronized int count_categories(){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select count(*) as Ccount from category";
                  ResultSet r = stmt.executeQuery(strSelect);
                  r.next();
                  return r.getInt("Ccount");
            } catch (SQLException e) {
               e.printStackTrace();
            }    
              return 0;
    }

    synchronized String get_user_firstName(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select Fname from customer where customer_id = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("Fname");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized String get_user_lastName(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select Lname from customer where customer_id = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("Lname");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }   

    synchronized int get_user_balance(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();){
                String strSelect = "select balance from customer where customer_id = "+id;
                ResultSet r = stmt.executeQuery(strSelect);
                while(r.next()){
                    return r.getInt("balance");
                }
            } catch (SQLException e) {
               e.printStackTrace();
               return 0;
            }    
            return 0;
    }
    
    synchronized String get_user_password(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();){
                String strSelect = "select pword from customer where customer_id = "+id;
                ResultSet r = stmt.executeQuery(strSelect);
                while(r.next()){
                    return r.getString("pword");
                }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized String get_user_name(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                String strSelect = "select userName from customer where customer_id = "+id;
                ResultSet r = stmt.executeQuery(strSelect);
                while(r.next()){
                    return r.getString("userName");
                }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized String get_user_email(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select email from customer where customer_id = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("email");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized int[] get_orderID(int customer_id){
      int a[] = {};
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                String str = "select count(order_id) as Corder from _order group by cust_id having cust_id = "+customer_id;
                ResultSet rs = stmt.executeQuery(str);
                rs.next();
                int n = rs.getInt("Corder");
                a = new int[n];
                int i =0;
                  String strSelect = "select order_id from _order where cust_id = "+customer_id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      a[i++] = r.getInt("order_id");
                  }
                  return a;
            } catch (SQLException e) {
               e.printStackTrace();
               return a;
            }    
    }

    synchronized String get_orderDate(int order_id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select ordered_at from _order where order_id = "+order_id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("ordered_at");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized int get_orderTotalAmount(int order_id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select total_amount from _order where order_id = "+order_id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getInt("total_amount");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return -1;
            }    
            return -1;
    }

    synchronized String get_product_name(int product_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();){
              String strSelect = "select pname from product where product_id ="+product_id;
              ResultSet r = stmt.executeQuery(strSelect);
              while(r.next()){
                  return r.getString("pname");
              }
        } catch (SQLException e) {
           e.printStackTrace();
           return "doesn't exist";
        }    
        return "doesn't exist";
    }  

    synchronized String get_product_price(int product_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();){
              String strSelect = "select price from product where product_id ="+product_id;
              ResultSet r = stmt.executeQuery(strSelect);
              while(r.next()){
                  return r.getString("price");
              }
        } catch (SQLException e) {
           e.printStackTrace();
           return "doesn't exist";
        }    
        return "doesn't exist";
    }
    
    synchronized String get_product_quantity(int product_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();){
              String strSelect = "select stock from product where product_id ="+product_id;
              ResultSet r = stmt.executeQuery(strSelect);
              while(r.next()){
                  return r.getString("quantity");
              }
        } catch (SQLException e) {
           e.printStackTrace();
           return "doesn't exist";
        }    
        return "doesn't exist";
    }
    
    synchronized String get_product_status(int product_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();){
              String strSelect = "select status from product where product_id ="+product_id;
              ResultSet r = stmt.executeQuery(strSelect);
              while(r.next()){
                  return r.getString("status");
              }
        } catch (SQLException e) {
           e.printStackTrace();
           return "doesn't exist";
        }    
        return "doesn't exist";
    }
    
    synchronized String get_order_product_quantity(int order_id,int product_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();){
              String strSelect = "select quantity from order_product where pro_id ="+product_id+"and o_id = "+order_id+";";
              ResultSet r = stmt.executeQuery(strSelect);
              while(r.next()){
                  return r.getString("quantity");
              }
        } catch (SQLException e) {
           e.printStackTrace();
           return "doesn't exist";
        }    
        return "doesn't exist";
    }  
    
    synchronized void increase_balance(int customer_id, int amount){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){               
            String sqlInsert = "update customer set balance = balance + "+amount+" where customer_id = " + customer_id +";";
            int countInserted = stmt.executeUpdate(sqlInsert);
            JFrame parent = new JFrame();
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JOptionPane.showMessageDialog(parent, "Added to balance");       
        } catch (SQLException e) {
          e.printStackTrace();
        }          
    }
    
    synchronized void decrease_balance(int customer_id, int amount){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
            String strSelect = "select balance from customer where customer_id ="+customer_id +";";
            ResultSet r = stmt.executeQuery(strSelect);
            int b = 0;
            while(r.next()){
                b = r.getInt("balance");
            }
            if(amount > b){
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Not enough in balance");
            }
            else{
                String sqlInsert = "update customer set balance = balance - "+amount+" where customer_id = " + customer_id +";";
                int countInserted = stmt.executeUpdate(sqlInsert);
                JFrame parent = new JFrame();
                parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(parent, "Added to balance");

            }           
        } catch (SQLException e) {
          e.printStackTrace();
       }          
    }

    synchronized void increase_stock(int product_id, int amount){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){               
            String sqlInsert = "update product set stock = stock + "+amount+" where product_id = " + product_id +";";
            int countInserted = stmt.executeUpdate(sqlInsert);
            JFrame parent = new JFrame();
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JOptionPane.showMessageDialog(parent, "Added to balance");       
        } catch (SQLException e) {
          e.printStackTrace();
        }          
    }
    
    synchronized void clear_cart(int cart_id){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
         String sqlInsert = "delete from cart_product where c_id = " +cart_id +";";
         int countInserted = stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
          e.printStackTrace();
       }          
    }

    synchronized String get_admin_firstName(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select Fname from admin where idadmin = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("Fname");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized String get_admin_lastName(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select Lname from admin where idadmin = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("Lname");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }   

    synchronized String get_admin_username(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select userName from admin where idadmin = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("userName");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }

    synchronized String get_admin_email(int id){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String strSelect = "select email from admin where idadmin = "+id;
                  ResultSet r = stmt.executeQuery(strSelect);
                  while(r.next()){
                      return r.getString("email");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
               return "doesn't exist";
            }    
            return "doesn't exist";
    }
    
    synchronized void change_password(int id, String password){
                try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                    PreparedStatement psCheckpasswordExists = null;
                    ResultSet passwordSet = null;
                    psCheckpasswordExists = conn.prepareStatement("SELECT * FROM customer WHERE pword = ?");
                    psCheckpasswordExists.setString(1, password);
                    passwordSet = psCheckpasswordExists.executeQuery();
                      
                    if(passwordSet.isBeforeFirst()){               
                        JFrame parent = new JFrame();
                        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JOptionPane.showMessageDialog(parent, "already exists");
                    }
                    else{
                        String sqlInsert = "update customer set pword = \""+password+"\" where customer_id = " + id +";";
                        int countInserted = stmt.executeUpdate(sqlInsert);
                        JFrame parent = new JFrame();
                        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JOptionPane.showMessageDialog(parent, "Updated");

                    }

                } catch (SQLException e) {
                  e.printStackTrace();
               }          

    }
    
    synchronized void change_userName(int id, String userName){
                try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                        PreparedStatement psCheckpasswordExists = null;
                        ResultSet passwordSet = null;
                        psCheckpasswordExists = conn.prepareStatement("SELECT * FROM customer WHERE userName = ?");
                        psCheckpasswordExists.setString(1, userName);
                        passwordSet = psCheckpasswordExists.executeQuery();

                        if(passwordSet.isBeforeFirst()){               
                            JFrame parent = new JFrame();
                            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JOptionPane.showMessageDialog(parent, "already exists");
                        }
                        else{
                            String sqlInsert = "update customer set userName = \""+userName+"\" where customer_id = " + id +";";
                            int countInserted = stmt.executeUpdate(sqlInsert);
                            JFrame parent = new JFrame();
                            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JOptionPane.showMessageDialog(parent, "Updated");

                        }
                } catch (SQLException e) {
                  e.printStackTrace();
               }          

    }
    
    synchronized void change_FirstName(int id, String Fname){
                try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
         String sqlInsert = "update customer set Fname = \""+Fname+"\" where customer_id = " + id +";";
         int countInserted = stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
          e.printStackTrace();
       }          

    }
    
    synchronized void change_LastName(int id, String Lname){
                try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
         String sqlInsert = "update customer set Lname = \""+Lname+"\" where customer_id = " + id +";";
         int countInserted = stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
          e.printStackTrace();
       }          

    }
    
    synchronized void delete_user(int id){
                    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
               Statement stmt = conn.createStatement();){
                  String sqlDelete = "delete from customer where customer_id = "+id;
                  int countDeleted = stmt.executeUpdate(sqlDelete);
                  if(countDeleted == 0){
                    JFrame parent = new JFrame();
                    parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JOptionPane.showMessageDialog(parent, "Doesn't exist");
                  }
                  else{
                    JFrame parent = new JFrame();
                    parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JOptionPane.showMessageDialog(parent, "Deleted");
                  }
            } catch (SQLException e) {
               e.printStackTrace();
            }    

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = Database.create();
        //db.addOrder(5, 2, 1,"2022-06-15", 2);
//        db.addUser(3,"Esam123", "12124", "Esam1", "ali", "esam1@gmail.com");
//        db.addAdmin(2,"mohamed12", "56789", "mohamed1", "ibrahim", "mohamed@gmail.com");
//        db.addProduct(2,"labtop",1,5000,3,"active");
//        db.addCategory(1,"Sports","active");
        //db.addCategory(2,"electronics","active");
//        db.delete_user(1);
//        System.out.println(db.get_user_balance(2));
//        db.increase_balance(2, 200);
//        System.out.println(db.get_user_balance(2));
        //db.clear_cart(2);
        //System.out.println(db.get_product_price(1));
        //System.out.println(db.count_products());
        //System.out.println(db.count_categories());
    //        int a[] = db.get_orderID(1);
    //        for(int i =0 ; i< a.length; i++){
    //            System.out.println(a[i]);
    //            System.out.println(db.get_orderDate(a[i]));
    //            System.out.println(db.get_orderTotalAmount(a[i]));
    //            
    //        }
    
//    System.out.println(db.get_admin_firstName(2));
//    System.out.println(db.get_admin_lastName(2));
//    System.out.println(db.get_admin_username(2));
//    System.out.println(db.get_admin_email(2));\
//            db.change_userName(1,"ahmed5");
//            System.out.println(db.get_user_name(1));
//            db.change_FirstName(1,"ah");
//            System.out.println(db.get_user_firstName(1));
//            db.change_LastName(1,"Mahmoud");
//            System.out.println(db.get_user_lastName(1));
//            db.change_password(1,"12345");
//            System.out.println(db.get_user_password(1));
//            db.change_userName(1,"ahmed1234");
//            System.out.println(db.get_user_name(1));
    }
    
}
