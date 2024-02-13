class DBFiles {
    Model mod=new Model();
    public int PutSignUpDetials(String email, String name, String phone, String role,String pass, String gender, String dob) {
        int g=mod.PutSignUpDetialsToDb(email, name, phone, role, pass, gender, dob);
        return g;
      }
      public int CheckLoginDetails(String email,String pass){
        int g=mod.CheckLoginDetailsInDB(email, pass);
        return g;
    }
    public int getUserId(String email){
        int g=mod.getUserId(email);
        return g;
    }

    public int putShoeDetails(String name,String brand,int size,int price,int count,String gender){
        int k=mod.putShoeDetails(name.toLowerCase(), brand.toLowerCase(), size, price, count,gender.toLowerCase());
        return k;
    }

    public int deletebyId(int id){
        int k=mod.deleteById(id);
        return k;
    }
    public int deleteByBrand(String brand){
        int k=mod.deleteByBrand(brand);
        return k;
    }

    public int deleteBySize(int size){
         int k=mod.deleteBySize(size);
         return k;
    }
    
    public int viewById(int id){
        int k=mod.viewById(id);
        return k;
    }

    public int viewByBrand(String brand){
        int k=mod.viewByBrand(brand);
        return k;
    }

    public int viewBySize(int size){
        int k=mod.viewBySize(size);
        return k;
    }

    public int updateIntValue(int id,String field,int val){
        int k=mod.updateIntValue(id, field, val);
        return k;
    }
    public int updateValue(int id,String field,String val){
        int k=mod.updateValue(id, field, val);
        return k;
    }

    public int viewByPrice(int val){
        int k=mod.viewByPrice(val);
        return k;
    }

    public int viewBySex(String sex){
        int k=mod.viewBySex(sex);
        return k;
    }

    public int addtoCart(int id,int uid){
        int k=mod.addtoCart(id,uid);
        return k;
    }

    public int place(int sid,int id){
        int k=mod.place(sid,id);
        return k;
    }
    public int viewCart(int id){
        int k=mod.viewCart(id);
        return k;
    }

    public int viewOrders(int id){
        int k=mod.viewOrders(id);
        return k;
    }
}


