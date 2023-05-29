package usuario;

public class UsuarioStaticService {
    public UsuarioStaticService() {}

    public String getLog(String usuario, String password) {
        if(UsuarioVerificacionStatic.isValidUser(usuario, password)) {
            String roles = UsuarioVerificacionStatic.getRoles(usuario);
            return "PERMISSION : " + roles + " - "  + java.time.LocalDateTime.now();
        } else {
            return "Incorrect USER and PWD (Incorrect "  + usuario + " and " + password + ")";
        }
    }
}
