package src;

public class profileAction {
    public static void editarNome(Usuario user,String newName){
        user.setName(newName);
    };
    public static void editarUsername(Usuario user,String newUsername){
        user.setUsername(newUsername);
    };
    public static void editarEmail(Usuario user,String newEmail){
        user.setEmail(newEmail);
    };
    public static void editarSenha(Usuario user,String newPasscode){
        user.setPassword(newPasscode);
    };
}
