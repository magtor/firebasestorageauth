package portugles.com.innova.innova;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuario")
public class usermodel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    String nombreusuario ;
    @DatabaseField
    String numerotelefono ;
    @DatabaseField
    String nickuser;
    @DatabaseField
    String password;
    @DatabaseField
    String fechanacimiento ;
    @DatabaseField
    String email ;
    public usermodel(){

    }
    public usermodel(String nombreusuario,String numerotelefono,String nickuser,String password,
                   String fechanacimiento,String email){
        this.nombreusuario = nombreusuario;
        this.numerotelefono = numerotelefono;
        this.nickuser = nickuser;
        this.password = password;
        this.fechanacimiento = fechanacimiento;
        this.email = email;

    }

    public String getNombreusuario(){
        return this.nombreusuario;
    }
    public String getNickuser(){
        return this.nickuser;
    }
    public String getFechanacimiento(){
        return this.fechanacimiento;
    }
    public String getNumerotelefono(){
        return this.numerotelefono;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public void  setNombreusuario(String nombreusuario){
        this.nombreusuario = nombreusuario;
    }
    public void setNumerotelefono(String numerotelefono){
        this.numerotelefono = numerotelefono;
    }
    public void setNickuser(String nickuser){
        this.nickuser = nickuser;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFechaNaci(String fechanacimiento){
        this.fechanacimiento = fechanacimiento;
    }
    public void setEmail(String email){
        this.email = email;
    }





}
