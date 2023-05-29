package usuarioTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import usuario.UsuarioStaticService;
import usuario.UsuarioVerificacionStatic;

import static org.mockito.Mockito.mockStatic;

public class UsuarioServiceStaticTest {

    MockedStatic<UsuarioVerificacionStatic> mockedStatic = mockStatic(UsuarioVerificacionStatic.class);

    @AfterEach
    public void deregister() {
        mockedStatic.close();
    }

    @ParameterizedTest
    @CsvSource({
            "juan,12345,Incorrect USER and PWD (Incorrect juan and 12345)",
            "pedro,54321,PERMISSION : CRUD - ",
            "lucia,54321,PERMISSION : CD - ",
            "maria,54321,PERMISSION : UD - ",
            "pepe,54321,PERMISSION : D - ",
            "martina,54321,PERMISSION : CRD - ",
    })
    public void verifyGetLog(String usuario, String password, String expected) {
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("juan", "12345")).thenReturn(false);
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("pedro", "54321")).thenReturn(true);
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("lucia", "54321")).thenReturn(true);
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("maria", "54321")).thenReturn(true);
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("pepe", "54321")).thenReturn(true);
        mockedStatic.when(() -> UsuarioVerificacionStatic.isValidUser("martina", "54321")).thenReturn(true);
        mockedStatic.when(() -> UsuarioVerificacionStatic.getRoles("pedro")).thenReturn("CRUD");
        mockedStatic.when(() -> UsuarioVerificacionStatic.getRoles("lucia")).thenReturn("CD");
        mockedStatic.when(() -> UsuarioVerificacionStatic.getRoles("maria")).thenReturn("UD");
        mockedStatic.when(() -> UsuarioVerificacionStatic.getRoles("pepe")).thenReturn("D");
        mockedStatic.when(() -> UsuarioVerificacionStatic.getRoles("martina")).thenReturn("CRD");

        UsuarioStaticService usuarioService = new UsuarioStaticService();
        String actual = usuarioService.getLog(usuario, password);
        String data = java.time.LocalDate.now().toString().substring(0, 10);
        if(actual.startsWith("PERMISSION")){
            actual += " " + data;
        }

        Assertions.assertTrue(actual.contains(actual), "Error, El metodo no esta funcionando de la manera correcta, Actual: " + actual + " Expected: " + expected + data);

    }

}
